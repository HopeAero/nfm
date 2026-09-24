// Java -> JS transpiler for the Extended Mode port, driven by javac's own types.
//
// web/TRANSPILE_SPEC.md is the contract; this applies it mechanically instead
// of by hand, because every hand port of this codebase got the float/int rules
// wrong somewhere (the base port needed ~250 fr() fixes after the fact). Each
// expression's type comes from javac attribution of the repaired decompiled
// source, so:
//   float op        -> fr(a op b)          double op -> a op b
//   int + - (wrap)  -> i32(a op b)         int *     -> Math.imul(a, b)
//   int /           -> idiv(a, b)          (int) f   -> trunc(f)
//   (float) d/int   -> fr(x)               int -> float widening -> fr(x)
//   x op= e         -> x = cast(T(x), op_promoted(x, e))   (JLS 15.26.2)
// Overloaded methods become name, name$1, ...; overloaded constructors take a
// leading selector argument, resolved at each call site from javac's symbol.
//
// Run with JDK 17+:
//   X="--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED"
//   javac $X -d /tmp/j2js J2JS.java
//   java  $X -cp /tmp/j2js J2JS <jar-classpath> <out-dir> <Class,Class,...> <all .java sources...>
//
// Anything it cannot translate faithfully is emitted as a /*J2JS:...*/ marker
// and reported, never guessed.

import com.sun.source.tree.*;
import com.sun.source.util.*;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;
import javax.tools.*;
import java.nio.file.*;
import java.util.*;

public class J2JS {
    static Set<String> gameClasses = new HashSet<>();
    static List<String> problems = new ArrayList<>();

    public static void main(String[] a) throws Exception {
        String cp = a[0];
        Path out = Paths.get(a[1]);
        Set<String> emit = new LinkedHashSet<>(Arrays.asList(a[2].split(",")));
        List<String> files = Arrays.asList(a).subList(3, a.length);
        for (String f : files) gameClasses.add(Paths.get(f).getFileName().toString().replace(".java", ""));
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fm = jc.getStandardFileManager(null, null, null);
        JavacTask task = (JavacTask) jc.getTask(null, fm, d -> {}, List.of("-proc:none", "-cp", cp, "-encoding", "UTF-8"),
                null, fm.getJavaFileObjectsFromStrings(files));
        Iterable<? extends CompilationUnitTree> units = task.parse();
        task.analyze();
        Files.createDirectories(out);
        for (CompilationUnitTree u : units) {
            for (Tree t : u.getTypeDecls()) {
                if (!(t instanceof ClassTree c) || !emit.contains(c.getSimpleName().toString())) continue;
                Emitter e = new Emitter(c);
                String js = e.emitClass();
                Files.writeString(out.resolve(c.getSimpleName() + ".js"), js);
                System.out.println("wrote " + c.getSimpleName() + ".js  (" + js.lines().count() + " lines, " + e.markers + " markers)");
            }
        }
        for (String p : problems) System.out.println("  ! " + p);
    }

    // ---------------------------------------------------------------- types

    static char tc(Type t) {
        if (t == null) return 'O';
        switch (t.getKind()) {
            case FLOAT: return 'F';
            case DOUBLE: return 'D';
            case INT: return 'I';
            case SHORT: return 'S';
            case BYTE: return 'B';
            case CHAR: return 'C';
            case LONG: return 'J';
            case BOOLEAN: return 'Z';
            default: return 'O';
        }
    }

    static Type type(Tree t) { return ((JCTree) t).type; }
    static char tc(Tree t) { return tc(type(t)); }
    static boolean isInt(char c) { return c == 'I' || c == 'S' || c == 'B' || c == 'C'; }
    static boolean isNum(char c) { return isInt(c) || c == 'F' || c == 'D' || c == 'J'; }

    /** Java binary numeric promotion. */
    static char promote(char a, char b) {
        if (a == 'D' || b == 'D') return 'D';
        if (a == 'F' || b == 'F') return 'F';
        if (a == 'J' || b == 'J') return 'J';
        return 'I';
    }

    /** Convert a JS value of Java type `from` to Java type `to`. */
    static String conv(String js, char from, char to) {
        if (from == to || to == 'O' || to == 'Z' || from == 'O' || from == 'Z') return js;
        switch (to) {
            case 'F': return (from == 'F') ? js : "fr(" + js + ")";
            case 'D': return js;
            case 'J': return isInt(from) ? js : "Math.trunc(" + js + ")";
            case 'I':
                if (isInt(from)) return js;
                if (from == 'J') return "i32(" + js + ")";
                return "trunc(" + js + ")";
            case 'S': return "((" + (isInt(from) ? js : "trunc(" + js + ")") + ") << 16 >> 16)";
            case 'B': return "((" + (isInt(from) ? js : "trunc(" + js + ")") + ") << 24 >> 24)";
            case 'C': return "((" + (isInt(from) ? js : "trunc(" + js + ")") + ") & 0xffff)";
        }
        return js;
    }

    // -------------------------------------------------------------- emitter

    static class Emitter {
        final ClassTree cls;
        final String name;
        int markers = 0;
        final Map<Symbol, String> methodNames = new HashMap<>();
        final List<MethodTree> ctors = new ArrayList<>();
        final Map<Symbol, Integer> ctorIndex = new HashMap<>();
        final Set<String> imports = new TreeSet<>();
        final Set<String> javaImports = new TreeSet<>();
        final Set<String> jawtImports = new TreeSet<>();
        int tmp = 0;

        Emitter(ClassTree c) { cls = c; name = c.getSimpleName().toString(); }

        String marker(String what, Tree t) {
            markers++;
            problems.add(name + ": " + what + ": " + t.toString().replaceAll("\\s+", " ").substring(0, Math.min(90, t.toString().replaceAll("\\s+", " ").length())));
            return "/*J2JS:" + what + "*/";
        }

        String emitClass() {
            // method naming: overloads get $1, $2 in declaration order
            Map<String, Integer> seen = new HashMap<>();
            for (Tree m : cls.getMembers()) {
                if (!(m instanceof MethodTree mt)) continue;
                Symbol s = (Symbol) TreeInfo.symbolFor((JCTree) mt);
                if (mt.getName().contentEquals("<init>")) {
                    if (mt.getBody() != null && !isDefaultCtor(mt)) { ctorIndex.put(s, ctors.size()); ctors.add(mt); }
                    continue;
                }
                String n = mt.getName().toString();
                int k = seen.merge(n, 1, Integer::sum) - 1;
                methodNames.put(s, fieldClash((Symbol.ClassSymbol) s.owner, k == 0 ? n : n + "$" + k));
            }
            StringBuilder body = new StringBuilder();
            body.append("export class ").append(name);
            String sup = cls.getExtendsClause() == null ? null : cls.getExtendsClause().toString();
            boolean extendsJawt = "Applet".equals(sup) || "Panel".equals(sup);   // createImage etc.; a Thread parent stays dropped
            if (sup != null && (gameClasses.contains(sup) || extendsJawt))
                body.append(" extends ").append(ref(sup));
            body.append(" {\n");
            // constructor: field defaults + initialisers, then dispatch
            body.append("  constructor(").append(ctors.size() > 1 ? "k, ...a" : ctors.isEmpty() ? "" : params(ctors.get(0))).append(") {\n");
            if (extendsJawt) body.append("    super();\n");
            for (Tree m : cls.getMembers()) {
                if (m instanceof VariableTree v && !v.getModifiers().getFlags().contains(Modifier.STATIC)) {
                    String init = v.getInitializer() != null ? expr(v.getInitializer(), tc(type(v))) : dflt(type(v));
                    body.append("    this.").append(v.getName()).append(" = ").append(init).append(";\n");
                }
            }
            if (ctors.size() == 1) body.append(block(ctors.get(0).getBody(), 2, true));
            else if (ctors.size() > 1) {
                body.append("    switch (k) {\n");
                for (int i = 0; i < ctors.size(); i++) body.append("      case ").append(i).append(": this.$ctor").append(i).append("(...a); break;\n");
                body.append("    }\n");
            }
            body.append("  }\n");
            if (ctors.size() > 1) for (int i = 0; i < ctors.size(); i++) {
                body.append("\n  $ctor").append(i).append("(").append(params(ctors.get(i))).append(") {\n");
                body.append(block(ctors.get(i).getBody(), 2, true)).append("  }\n");
            }
            for (Tree m : cls.getMembers()) {
                if (m instanceof MethodTree mt && !mt.getName().contentEquals("<init>") && mt.getBody() != null) {
                    Symbol s = (Symbol) TreeInfo.symbolFor((JCTree) mt);
                    boolean st = mt.getModifiers().getFlags().contains(Modifier.STATIC);
                    retType = mt.getReturnType() == null ? 'O' : tc(type(mt.getReturnType()));
                    // A `while (true) { ... Thread.sleep(t); }` loop cannot block a browser: the
                    // method becomes a generator that yields each sleep (GameSparker.run).
                    generator = mt.getBody().toString().contains("Thread.sleep(");
                    body.append("\n  ").append(st ? "static " : "").append(generator ? "*" : "").append(methodNames.get(s)).append("(").append(params(mt)).append(") {\n");
                    body.append(block(mt.getBody(), 2, false)).append("  }\n");
                    generator = false;
                }
            }
            body.append("}\n");
            // statics after the class
            for (Tree m : cls.getMembers()) {
                if (m instanceof VariableTree v && v.getModifiers().getFlags().contains(Modifier.STATIC)) {
                    String init = v.getInitializer() != null ? expr(v.getInitializer(), tc(type(v))) : dflt(type(v));
                    body.append(name).append(".").append(v.getName()).append(" = ").append(init).append(";\n");
                }
            }
            // import exactly the helpers the body ended up using
            String b = body.toString();
            for (String h : new String[]{"fr", "trunc", "i32", "idiv", "jround", "random", "intArray", "floatArray", "objArray"})
                if (java.util.regex.Pattern.compile("(^|[^\\w.$])" + h + "\\(").matcher(b).find()) javaImports.add(h);
            for (String h : new String[]{"jstr", "charAt"})
                if (java.util.regex.Pattern.compile("(^|[^\\w.$])" + h + "\\(").matcher(b).find()) jawtImports.add(h);
            StringBuilder head = new StringBuilder();
            head.append("// GENERATED by decompilation/extended/j2js/J2JS.java from\n");
            head.append("// decompilation/extended/java-src/").append(name).append(".java -- do not edit by hand;\n");
            head.append("// fix the transpiler or the source and regenerate.\n\n");
            if (!javaImports.isEmpty()) head.append("import { ").append(String.join(", ", javaImports)).append(" } from '../java.js';\n");
            if (!jawtImports.isEmpty()) head.append("import { ").append(String.join(", ", jawtImports)).append(" } from './jawt.js';\n");
            for (String i : imports) if (!i.equals(name)) head.append("import { ").append(i).append(" } from './").append(i).append(".js';\n");
            return head + "\n" + body;
        }

        boolean isDefaultCtor(MethodTree mt) {
            return mt.getParameters().isEmpty() && mt.getBody().getStatements().size() == 1
                    && false;   // every constructor is emitted, even an empty one
        }

        String params(MethodTree m) {
            StringBuilder s = new StringBuilder();
            for (VariableTree p : m.getParameters()) { if (s.length() > 0) s.append(", "); s.append(local(p.getName())); }
            return s.toString();
        }

        String dflt(Type t) {
            char c = tc(t);
            if (isNum(c)) return "0";
            if (c == 'Z') return "false";
            return "null";
        }

        // ---------------------------------------------------------- statements

        String ind(int n) { return "  ".repeat(n); }

        String block(BlockTree b, int d, boolean ctor) {
            StringBuilder s = new StringBuilder();
            for (StatementTree st : b.getStatements()) {
                if (ctor && st instanceof ExpressionStatementTree es && es.getExpression() instanceof MethodInvocationTree mi
                        && mi.getMethodSelect().toString().equals("super")) continue;   // Object/Panel super()
                s.append(stmt(st, d));
            }
            return s.toString();
        }

        String stmt(StatementTree st, int d) {
            String I = ind(d);
            if (st instanceof BlockTree b) return I + "{\n" + block(b, d + 1, false) + I + "}\n";
            if (st instanceof VariableTree v) {
                String init = v.getInitializer() != null ? expr(v.getInitializer(), tc(type(v))) : dflt(type(v));
                return I + "let " + local(v.getName()) + " = " + init + ";\n";
            }
            if (st instanceof ExpressionStatementTree es) return I + exprStmt(es.getExpression()) + ";\n";
            if (st instanceof IfTree t) {
                StringBuilder s = new StringBuilder(I + "if (" + cond(t.getCondition()) + ") " + sub(t.getThenStatement(), d));
                if (t.getElseStatement() != null) {
                    s.setLength(s.length() - 1);
                    if (t.getElseStatement() instanceof IfTree) s.append(" else ").append(stmt(t.getElseStatement(), d).substring(I.length()));
                    else s.append(" else ").append(sub(t.getElseStatement(), d));
                }
                return s.toString();
            }
            if (st instanceof ForLoopTree f) {
                String init = String.join(", ", f.getInitializer().stream().map(x -> {
                    if (x instanceof VariableTree v) return "let " + local(v.getName()) + " = " + (v.getInitializer() != null ? expr(v.getInitializer(), tc(type(v))) : dflt(type(v)));
                    return exprStmt(((ExpressionStatementTree) x).getExpression());
                }).toList()).replace(", let ", ", ");
                String upd = String.join(", ", f.getUpdate().stream().map(x -> exprStmt(x.getExpression())).toList());
                return I + "for (" + init + "; " + (f.getCondition() == null ? "" : cond(f.getCondition())) + "; " + upd + ") " + sub(f.getStatement(), d);
            }
            if (st instanceof EnhancedForLoopTree f) return I + "for (const " + local(f.getVariable().getName()) + " of " + expr(f.getExpression(), 'O') + ") " + sub(f.getStatement(), d);
            if (st instanceof WhileLoopTree w) return I + "while (" + cond(w.getCondition()) + ") " + sub(w.getStatement(), d);
            if (st instanceof DoWhileLoopTree w) {
                String b = sub(w.getStatement(), d);
                return I + "do " + b.substring(0, b.length() - 1) + " while (" + cond(w.getCondition()) + ");\n";
            }
            if (st instanceof ReturnTree r) {
                if (r.getExpression() == null) return I + "return;\n";
                return I + "return " + expr(r.getExpression(), retType) + ";\n";
            }
            if (st instanceof BreakTree b) return I + "break" + (b.getLabel() != null ? " " + b.getLabel() : "") + ";\n";
            if (st instanceof ContinueTree c) return I + "continue" + (c.getLabel() != null ? " " + c.getLabel() : "") + ";\n";
            if (st instanceof LabeledStatementTree l) return I + l.getLabel() + ": " + stmt(l.getStatement(), d).substring(I.length());
            if (st instanceof SwitchTree sw) {
                StringBuilder s = new StringBuilder(I + "switch (" + expr(sw.getExpression(), 'O') + ") {\n");
                for (CaseTree c : sw.getCases()) {
                    if (c.getExpressions().isEmpty()) s.append(I).append("  default:\n");
                    for (ExpressionTree e : c.getExpressions()) s.append(I).append("  case ").append(expr(e, 'O')).append(":\n");
                    for (StatementTree x : c.getStatements()) s.append(stmt(x, d + 2));
                }
                return s + I + "}\n";
            }
            if (st instanceof TryTree t) {
                StringBuilder s = new StringBuilder(I + "try {\n" + block(t.getBlock(), d + 1, false) + I + "}");
                if (!t.getCatches().isEmpty()) {
                    CatchTree c = t.getCatches().get(0);
                    s.append(" catch (").append(local(c.getParameter().getName())).append(") {\n").append(block(c.getBlock(), d + 1, false)).append(I).append("}");
                }
                if (t.getFinallyBlock() != null) s.append(" finally {\n").append(block(t.getFinallyBlock(), d + 1, false)).append(I).append("}");
                return s + "\n";
            }
            if (st instanceof ThrowTree t) return I + "throw " + expr(t.getExpression(), 'O') + ";\n";
            if (st instanceof EmptyStatementTree) return I + ";\n";
            if (st instanceof SynchronizedTree s) return I + "/* synchronized */ " + stmt(s.getBlock(), d).substring(I.length());
            return I + marker("stmt " + st.getKind(), st) + "\n";
        }

        char retType = 'O';
        boolean generator = false;   // emitting a method that calls Thread.sleep itself

        String sub(StatementTree s, int d) {
            if (s instanceof BlockTree b) return "{\n" + block(b, d + 1, false) + ind(d) + "}\n";
            return "{\n" + stmt(s, d + 1) + ind(d) + "}\n";
        }

        String cond(ExpressionTree e) { return expr(strip(e), 'Z'); }

        // -------------------------------------------------------- expressions

        /** An expression evaluated for its effect (value discarded). */
        String exprStmt(ExpressionTree e) {
            e = strip(e);
            if (e instanceof UnaryTree u && (u.getKind() == Tree.Kind.POSTFIX_INCREMENT || u.getKind() == Tree.Kind.PREFIX_INCREMENT
                    || u.getKind() == Tree.Kind.POSTFIX_DECREMENT || u.getKind() == Tree.Kind.PREFIX_DECREMENT)) {
                boolean inc = u.getKind() == Tree.Kind.POSTFIX_INCREMENT || u.getKind() == Tree.Kind.PREFIX_INCREMENT;
                return incdec(u.getExpression(), inc);
            }
            return expr(e, 'O');
        }

        String incdec(ExpressionTree target, boolean inc) {
            char t = tc(target);
            String lv = lvalue(target);
            if (isInt(t) && !isTypedArrayElem(target)) return lv + " = " + conv("i32(" + lv + (inc ? " + 1" : " - 1") + ")", 'I', t);
            if (t == 'F') return lv + " = fr(" + lv + (inc ? " + 1" : " - 1") + ")";
            return (inc ? "++" : "--") + lv;
        }

        /** Int arrays are Int32Array: stores wrap on their own. */
        boolean isTypedArrayElem(ExpressionTree t) {
            return strip(t) instanceof ArrayAccessTree && tc(t) == 'I';
        }

        ExpressionTree strip(ExpressionTree e) {
            while (e instanceof ParenthesizedTree p) e = p.getExpression();
            return e;
        }

        /** expr converted to Java type `want` (assignment/argument conversion). */
        String expr(ExpressionTree e, char want) {
            String js = raw(e);
            char have = tc(e);
            if (want != 'O' && want != 'Z' && have != 'O' && have != 'Z' && isNum(have) && isNum(want) && have != want) {
                // widening conversions JS needs to spell out
                if (want == 'F' && (isInt(have) || have == 'J')) return "fr(" + js + ")";
            }
            return js;
        }

        String raw(ExpressionTree e) {
            if (e instanceof ParenthesizedTree p) return "(" + raw(p.getExpression()) + ")";
            if (e instanceof LiteralTree l) return literal(l);
            if (e instanceof IdentifierTree id) return ident(id);
            if (e instanceof MemberSelectTree ms) return member(ms);
            if (e instanceof ArrayAccessTree aa) return raw(aa.getExpression()) + "[" + raw(aa.getIndex()) + "]";
            if (e instanceof MethodInvocationTree mi) return call(mi);
            if (e instanceof NewClassTree nc) return newClass(nc);
            if (e instanceof NewArrayTree na) return newArray(na, type(na));
            if (e instanceof TypeCastTree c) return conv(raw(c.getExpression()), tc(c.getExpression()), tc(c));
            // plain `=`: JS evaluates a[k++] once, as Java does, so side effects are fine here
            if (e instanceof AssignmentTree as) {
                ExpressionTree v = strip(as.getVariable());
                String lv = v instanceof ArrayAccessTree aa ? raw(aa.getExpression()) + "[" + raw(aa.getIndex()) + "]" : lvalue(v);
                return lv + " = " + storeConv(as.getVariable(), as.getExpression());
            }
            if (e instanceof CompoundAssignmentTree ca) return compound(ca);
            if (e instanceof UnaryTree u) return unary(u);
            if (e instanceof BinaryTree b) return binary(b);
            if (e instanceof ConditionalExpressionTree c) {
                char t = tc(c);
                return "(" + cond(c.getCondition()) + " ? " + expr(c.getTrueExpression(), t) + " : " + expr(c.getFalseExpression(), t) + ")";
            }
            if (e instanceof InstanceOfTree io) return raw(io.getExpression()) + " instanceof " + io.getType();
            return marker("expr " + e.getKind(), e);
        }

        String storeConv(ExpressionTree target, ExpressionTree value) {
            return expr(value, tc(target));
        }

        String literal(LiteralTree l) {
            Object v = l.getValue();
            if (v == null) return "null";
            if (v instanceof Character ch) return Integer.toString(ch);
            if (v instanceof String s) return jsString(s);
            if (v instanceof Float f) {
                // The float's exact value as a double: 1.1f is 1.100000023841858,
                // not 1.1 -- `fr(1.1 * x)` and `fr(1.1f * x)` can round differently.
                String s = Double.toString((double) f);
                return s.replace("E", "e");
            }
            if (v instanceof Double d) return Double.toString(d).replace("E", "e");
            if (v instanceof Long lg) return Long.toString(lg);
            return v.toString();
        }

        String jsString(String s) {
            StringBuilder b = new StringBuilder("'");
            for (char c : s.toCharArray()) {
                if (c == '\'' || c == '\\') b.append('\\').append(c);
                else if (c == '\n') b.append("\\n");
                else if (c == '\r') b.append("\\r");
                else if (c < 32 || c > 126) b.append(String.format("\\u%04x", (int) c));
                else b.append(c);
            }
            return b.append("'").toString();
        }

        String ident(IdentifierTree id) {
            Symbol s = (Symbol) TreeInfo.symbol((JCTree) id);
            String n = id.getName().toString();
            if (n.equals("this")) return "this";
            if (s instanceof Symbol.VarSymbol v && v.owner instanceof Symbol.ClassSymbol) {
                if (v.isStatic()) return ref(v.owner.getSimpleName().toString()) + "." + n;
                return "this." + n;
            }
            if (s instanceof Symbol.ClassSymbol c) return ref(c.getSimpleName().toString());
            return local(n);
        }

        // A Java local may be named with a JS reserved word (GameSparker's `in`).
        static final java.util.Set<String> JS_RESERVED = java.util.Set.of("in", "function", "delete", "typeof", "var", "let", "yield", "await", "with", "export", "arguments", "eval");
        static String local(CharSequence n) { String s = n.toString(); return JS_RESERVED.contains(s) ? s + "_" : s; }

        String ref(String cls) {
            if (gameClasses.contains(cls)) imports.add(cls);
            else if (JAWT.contains(cls)) jawtImports.add(cls);
            return cls;
        }

        static final Set<String> JAWT = Set.of("Color", "Font", "AlphaComposite", "BasicStroke", "Polygon", "Cursor",
                "DataInputStream", "ByteArrayInputStream", "BufferedReader", "InputStreamReader", "StringReader",
                "Integer", "Float", "Double", "Long", "Boolean", "Character", "StringBuilder", "System", "Thread",
                "Random", "Date", "File", "FileInputStream", "FileOutputStream", "ZipInputStream", "ZipEntry", "URL", "Toolkit", "Image",
                "Graphics", "Graphics2D", "RenderingHints", "Event", "Applet", "Panel", "Dimension", "MediaTracker",
                "PixelGrabber", "MemoryImageSource");

        String member(MemberSelectTree ms) {
            Symbol s = (Symbol) TreeInfo.symbol((JCTree) ms);
            String q = raw(ms.getExpression());
            if (s instanceof Symbol.ClassSymbol c) return ref(c.getSimpleName().toString());
            if (ms.getIdentifier().contentEquals("length") && ms.getExpression() != null && type(ms.getExpression()) instanceof Type.ArrayType) return q + ".length";
            if (s instanceof Symbol.VarSymbol v && v.isStatic()) return ref(v.owner.getSimpleName().toString()) + "." + ms.getIdentifier();
            return q + "." + ms.getIdentifier();
        }

        String args(MethodInvocationTree mi, Symbol.MethodSymbol ms) {
            List<String> out = new ArrayList<>();
            List<? extends ExpressionTree> as = mi.getArguments();
            for (int i = 0; i < as.size(); i++) {
                char want = ms != null && i < ms.params().size() ? tc(ms.params().get(i).type) : 'O';
                out.add(expr(as.get(i), want));
            }
            return String.join(", ", out);
        }

        String call(MethodInvocationTree mi) {
            Symbol.MethodSymbol ms = (Symbol.MethodSymbol) TreeInfo.symbol((JCTree) mi.getMethodSelect());
            String owner = ms == null ? "" : ms.owner.getQualifiedName().toString();
            String mname = ms == null ? mi.getMethodSelect().toString() : ms.getSimpleName().toString();
            String qual = null;
            if (mi.getMethodSelect() instanceof MemberSelectTree sel) qual = raw(sel.getExpression());
            // X.class.getResourceAsStream(name): the name; jawt's Font.createFont takes it (the jar's .ttf)
            if (owner.equals("java.lang.Class") && mname.equals("getResourceAsStream")) return args(mi, ms);
            // the game loop's frame wait: run() is a generator, its driver sleeps
            if (owner.equals("java.lang.Thread") && mname.equals("sleep") && generator) return "(yield " + args(mi, ms) + ")";
            // java.lang.Math
            if (owner.equals("java.lang.Math")) {
                char rt = tc(mi);
                String a = args(mi, ms);
                switch (mname) {
                    case "random": javaImports.add("random"); return "random()";
                    case "abs": return isInt(rt) ? "(Math.abs(" + a + ") | 0)" : rt == 'F' ? "Math.abs(" + a + ")" : "Math.abs(" + a + ")";
                    case "round": javaImports.add("jround"); return rt == 'J' ? "Math.floor(" + a + " + 0.5)" : "jround(" + a + ")";
                    case "max": case "min": return "Math." + mname + "(" + a + ")";
                    case "sqrt": case "sin": case "cos": case "tan": case "atan": case "atan2": case "acos": case "asin": case "pow":
                    case "exp": case "log": case "floor": case "ceil": case "hypot": case "signum": case "cbrt":
                        return "Math." + mname + "(" + a + ")";
                    case "toRadians": return "(" + a + " * Math.PI / 180)";
                    case "toDegrees": return "(" + a + " * 180 / Math.PI)";
                }
                return marker("Math." + mname, mi);
            }
            if (owner.equals("java.lang.System") && mname.equals("gc")) return "void 0";
            // game-class method: overload-renamed, implicit this
            if (ms != null && ms.owner instanceof Symbol.ClassSymbol oc && gameClasses.contains(oc.getSimpleName().toString())) {
                String jsName = nameOf(ms);
                String target = qual != null ? qual : (ms.isStatic() ? ref(oc.getSimpleName().toString()) : "this");
                return target + "." + jsName + "(" + args(mi, ms) + ")";
            }
            if (mname.equals("super") || mname.equals("this")) return marker("explicit ctor call", mi);
            // library call: keep as written; jawt.js shims the API used
            String target = qual != null ? qual : "this";
            if (ms != null && ms.isStatic() && ms.owner instanceof Symbol.ClassSymbol oc) target = ref(oc.getSimpleName().toString());
            if (mname.equals("setColor") && mi.getArguments().size() == 1) {
                ExpressionTree c = strip(mi.getArguments().get(0));
                if (c instanceof NewClassTree nc && nc.getArguments().size() == 3)
                    return target + ".setColor(" + args3(nc) + ")";
                return target + ".setColorOf(" + raw(c) + ")";
            }
            // boxing helpers: valueOf returns the JS number, xxxValue() is identity
            if ((owner.equals("java.lang.Integer") || owner.equals("java.lang.Float") || owner.equals("java.lang.Double"))
                    && (mname.equals("intValue") || mname.equals("floatValue") || mname.equals("doubleValue")) && qual != null)
                return qual;
            // a char passed to String/StringBuilder text methods is a character, not a code
            if (owner.equals("java.lang.StringBuilder") || owner.equals("java.lang.String")) {
                List<String> as = new ArrayList<>();
                for (ExpressionTree x : mi.getArguments())
                    as.add(tc(x) == 'C' || (strip(x) instanceof LiteralTree lt && lt.getValue() instanceof Character) ? "String.fromCharCode(" + raw(x) + ")" : raw(x));
                if (owner.equals("java.lang.StringBuilder")) return target + "." + mname + "(" + String.join(", ", as) + ")";
                switch (mname) {
                    case "indexOf": case "lastIndexOf": case "startsWith": case "endsWith": case "replace":
                        return target + "." + mname + "(" + String.join(", ", as) + ")";
                }
            }
            if (owner.equals("java.lang.String")) {
                switch (mname) {
                    case "charAt": jawtImports.add("charAt"); return "charAt(" + target + ", " + args(mi, ms) + ")";
                    case "equals": return "(" + target + " === " + args(mi, ms) + ")";
                    case "length": return target + ".length";
                    case "equalsIgnoreCase": return "(" + target + ".toLowerCase() === (" + args(mi, ms) + ").toLowerCase())";
                    case "valueOf": jawtImports.add("jstr"); return "jstr(" + args(mi, ms) + ")";
                }
            }
            return target + "." + mname + "(" + args(mi, ms) + ")";
        }

        String args3(NewClassTree nc) {
            List<String> out = new ArrayList<>();
            for (ExpressionTree a : nc.getArguments()) out.add(expr(a, 'I'));
            return String.join(", ", out);
        }

        String nameOf(Symbol.MethodSymbol ms) {
            String n = methodNames.get(ms);
            if (n != null) return n;
            // another game class: same overload rule, in that class's declaration order
            Symbol.ClassSymbol oc = (Symbol.ClassSymbol) ms.owner;
            int k = 0;
            for (Symbol m : oc.getEnclosedElements()) {
                if (m == ms) break;
                if (m instanceof Symbol.MethodSymbol x && x.getSimpleName().equals(ms.getSimpleName()) && x.getKind() == ElementKind.METHOD) k++;
            }
            return fieldClash(oc, k == 0 ? ms.getSimpleName().toString() : ms.getSimpleName() + "$" + k);
        }

        // Java keeps fields and methods apart; in JS an instance field hides the
        // prototype method (xtGraphics has both `Image[][] trackbg` and trackbg()).
        static String fieldClash(Symbol.ClassSymbol oc, String n) {
            for (Symbol m : oc.getEnclosedElements())
                if (m instanceof Symbol.VarSymbol && m.getSimpleName().contentEquals(n)) return n + "$m";
            return n;
        }

        String newClass(NewClassTree nc) {
            // TreeInfo.symbol is null for a JCNewClass; the resolved constructor is a field of it
            Symbol.MethodSymbol ctor = (Symbol.MethodSymbol) ((JCTree.JCNewClass) nc).constructor;
            String cn = nc.getIdentifier().toString();
            if (nc.getClassBody() != null) return marker("anonymous class", nc);
            Symbol.ClassSymbol oc = ctor == null ? null : (Symbol.ClassSymbol) ctor.owner;
            if (oc != null && gameClasses.contains(oc.getSimpleName().toString())) {
                ref(oc.getSimpleName().toString());
                // selector argument when the target class overloads its constructor
                List<Symbol.MethodSymbol> cs = new ArrayList<>();
                for (Symbol m : oc.getEnclosedElements()) if (m instanceof Symbol.MethodSymbol x && x.getKind() == ElementKind.CONSTRUCTOR) cs.add(x);
                String a = args(nc, ctor);
                if (cs.size() > 1) return "new " + oc.getSimpleName() + "(" + cs.indexOf(ctor) + (a.isEmpty() ? "" : ", " + a) + ")";
                return "new " + oc.getSimpleName() + "(" + a + ")";
            }
            ref(cn.replaceAll("<.*", ""));
            return "new " + cn.replaceAll("<.*", "") + "(" + args(nc, ctor) + ")";
        }

        String args(NewClassTree nc, Symbol.MethodSymbol ms) {
            List<String> out = new ArrayList<>();
            List<? extends ExpressionTree> as = nc.getArguments();
            for (int i = 0; i < as.size(); i++) {
                char want = ms != null && i < ms.params().size() ? tc(ms.params().get(i).type) : 'O';
                out.add(expr(as.get(i), want));
            }
            return String.join(", ", out);
        }

        String newArray(NewArrayTree na, Type t) {
            Type elem = ((Type.ArrayType) t).getComponentType();
            if (na.getInitializers() != null) {
                List<String> vs = new ArrayList<>();
                for (ExpressionTree x : na.getInitializers()) vs.add(x instanceof NewArrayTree sub ? newArray(sub, elem) : expr(x, tc(elem)));
                String list = "[" + String.join(", ", vs) + "]";
                return typed(elem, null, list);
            }
            List<? extends ExpressionTree> dims = na.getDimensions();
            return alloc(t, dims, 0);
        }

        String alloc(Type t, List<? extends ExpressionTree> dims, int i) {
            Type elem = ((Type.ArrayType) t).getComponentType();
            String n = raw(dims.get(i));
            if (i == dims.size() - 1) return typed(elem, n, null);
            javaImports.add("objArray");
            return "objArray(" + n + ").map(() => " + alloc(elem, dims, i + 1) + ")";
        }

        /** A 1-D array of `elem`: length n, or from a JS list literal. */
        String typed(Type elem, String n, String list) {
            char c = tc(elem);
            String ta = switch (c) {
                case 'I' -> "Int32Array"; case 'F' -> "Float32Array"; case 'D', 'J' -> "Float64Array";
                case 'S' -> "Int16Array"; case 'B' -> "Int8Array"; case 'C' -> "Uint16Array"; default -> null;
            };
            if (list != null) return ta != null ? ta + ".from(" + list + ")" : list;
            if (ta != null) {
                if (c == 'I') { javaImports.add("intArray"); return "intArray(" + n + ")"; }
                if (c == 'F') { javaImports.add("floatArray"); return "floatArray(" + n + ")"; }
                return "new " + ta + "(" + n + ")";
            }
            if (c == 'Z') return "new Array(" + n + ").fill(false)";
            javaImports.add("objArray");
            return "objArray(" + n + ")";
        }

        String lvalue(ExpressionTree e) {
            e = strip(e);
            if (e instanceof IdentifierTree id) return ident(id);
            if (e instanceof MemberSelectTree ms) return member(ms);
            if (e instanceof ArrayAccessTree aa) {
                String idx = raw(aa.getIndex());
                if (sideEffects(aa.getIndex()) || sideEffects(aa.getExpression())) return marker("lvalue with side effects", e);
                return raw(aa.getExpression()) + "[" + idx + "]";
            }
            return marker("lvalue " + e.getKind(), e);
        }

        boolean sideEffects(ExpressionTree e) {
            final boolean[] found = {false};
            new TreeScanner<Void, Void>() {
                @Override public Void visitMethodInvocation(MethodInvocationTree n, Void v) { found[0] = true; return null; }
                @Override public Void visitAssignment(AssignmentTree n, Void v) { found[0] = true; return null; }
                @Override public Void visitCompoundAssignment(CompoundAssignmentTree n, Void v) { found[0] = true; return null; }
                @Override public Void visitUnary(UnaryTree n, Void v) {
                    Tree.Kind k = n.getKind();
                    if (k == Tree.Kind.PREFIX_INCREMENT || k == Tree.Kind.POSTFIX_INCREMENT || k == Tree.Kind.PREFIX_DECREMENT || k == Tree.Kind.POSTFIX_DECREMENT) found[0] = true;
                    return super.visitUnary(n, v);
                }
            }.scan(e, null);
            return found[0];
        }

        String compound(CompoundAssignmentTree ca) {
            char tx = tc(ca.getVariable()), te = tc(ca.getExpression());
            ExpressionTree tv = strip(ca.getVariable());
            // a[k++] op= e: Java evaluates the index once -- bind it to a parameter
            if (tv instanceof ArrayAccessTree aa && sideEffects(aa.getIndex()) && !sideEffects(aa.getExpression())) {
                String arr = raw(aa.getExpression());
                String op = opOf(ca.getKind());
                char p = promote(tx, te);
                String v = arith(op, arr + "[$i]", tx, ca.getExpression(), p);
                return "(($i) => (" + arr + "[$i] = " + conv(v, p, tx) + "))(" + raw(aa.getIndex()) + ")";
            }
            String lv = lvalue(ca.getVariable());
            String op = opOf(ca.getKind());
            if (tx == 'O' || !isNum(tx)) {                 // String +=
                return lv + " " + op + "= " + raw(ca.getExpression());
            }
            char p = (op.equals("<<") || op.equals(">>") || op.equals(">>>")) ? (tx == 'J' ? 'J' : 'I') : promote(tx, te);
            if ((op.equals("&") || op.equals("|") || op.equals("^")) && tx == 'Z') return lv + " = " + lv + " " + op + " " + raw(ca.getExpression());
            String v = arith(op, lv, tx, ca.getExpression(), p);
            return lv + " = " + conv(v, p, tx);
        }

        /** a op e in type p, a already JS text of Java type ta. */
        String arith(String op, String a, char ta, ExpressionTree eb, char p) {
            String b = raw(eb);
            char tb = tc(eb);
            if (p == 'F') return "fr(" + widenF(a, ta) + " " + op + " " + widenF(parenIfNeeded(b, eb), tb) + ")";
            if (p == 'D' || p == 'J') return a + " " + op + " " + parenIfNeeded(b, eb);
            // int
            switch (op) {
                case "+": case "-": javaImports.add("i32"); return "i32(" + a + " " + op + " " + parenIfNeeded(b, eb) + ")";
                case "*": return "Math.imul(" + a + ", " + b + ")";
                case "/": javaImports.add("idiv"); return "idiv(" + a + ", " + b + ")";
                case "%": return "(" + a + " % " + parenIfNeeded(b, eb) + ")";
                case ">>>": return "((" + a + " >>> " + parenIfNeeded(b, eb) + ") | 0)";
                default: return "(" + a + " " + op + " " + parenIfNeeded(b, eb) + ")";
            }
        }

        String widenF(String js, char t) { return (isInt(t) || t == 'J') && !js.matches("-?\\d+") ? "fr(" + js + ")" : js; }

        String parenIfNeeded(String js, ExpressionTree e) {
            e = strip(e);
            if (e instanceof BinaryTree || e instanceof ConditionalExpressionTree || e instanceof AssignmentTree || e instanceof CompoundAssignmentTree) return "(" + js + ")";
            return js;
        }

        String binary(BinaryTree b) {
            String op = opOf(b.getKind());
            char tl = tc(b.getLeftOperand()), tr = tc(b.getRightOperand()), t = tc(b);
            switch (b.getKind()) {
                case CONDITIONAL_AND: case CONDITIONAL_OR:
                    return paren(b.getLeftOperand()) + " " + op + " " + paren(b.getRightOperand());
                case EQUAL_TO: case NOT_EQUAL_TO:
                    return paren(b.getLeftOperand()) + (op.equals("==") ? " === " : " !== ") + paren(b.getRightOperand());
                case LESS_THAN: case GREATER_THAN: case LESS_THAN_EQUAL: case GREATER_THAN_EQUAL:
                    return paren(b.getLeftOperand()) + " " + op + " " + paren(b.getRightOperand());
                default:
            }
            if (t == 'O') {   // String concatenation
                jawtImports.add("jstr");
                return strPart(b.getLeftOperand()) + " + " + strPart(b.getRightOperand());
            }
            if (t == 'Z') return paren(b.getLeftOperand()) + " " + op + " " + paren(b.getRightOperand());   // boolean & | ^
            char p = t;
            String a = raw(b.getLeftOperand());
            a = parenIfNeeded(a, b.getLeftOperand());
            return arith(op, a, tl, b.getRightOperand(), p);
        }

        /** One side of a Java String +: chars and floats print Java's way. */
        String strPart(ExpressionTree e) {
            char c = tc(e);
            String js = paren(e);
            if (c == 'C') return "String.fromCharCode(" + raw(e) + ")";
            if (c == 'F' || c == 'D') return "jstr(" + raw(e) + ", '" + c + "')";
            return js;
        }

        String paren(ExpressionTree e) { return parenIfNeeded(raw(e), e); }

        String unary(UnaryTree u) {
            char t = tc(u);
            switch (u.getKind()) {
                case UNARY_MINUS:
                    if (isInt(t)) { javaImports.add("i32"); return "i32(-" + paren(u.getExpression()) + ")"; }
                    return "-" + paren(u.getExpression());
                case UNARY_PLUS: return paren(u.getExpression());
                case LOGICAL_COMPLEMENT: return "!" + paren(u.getExpression());
                case BITWISE_COMPLEMENT: return "~" + paren(u.getExpression());
                case PREFIX_INCREMENT: case PREFIX_DECREMENT: case POSTFIX_INCREMENT: case POSTFIX_DECREMENT: {
                    boolean inc = u.getKind() == Tree.Kind.PREFIX_INCREMENT || u.getKind() == Tree.Kind.POSTFIX_INCREMENT;
                    boolean post = u.getKind() == Tree.Kind.POSTFIX_INCREMENT || u.getKind() == Tree.Kind.POSTFIX_DECREMENT;
                    String lv = lvalue(u.getExpression());
                    // ponytail: value-producing ++/-- use JS's own operator; exact
                    // unless the int passes 2^31 (loop counters and indices never do)
                    return post ? lv + (inc ? "++" : "--") : (inc ? "++" : "--") + lv;
                }
                default: return marker("unary " + u.getKind(), u);
            }
        }

        static String opOf(Tree.Kind k) {
            switch (k) {
                case PLUS: case PLUS_ASSIGNMENT: return "+";
                case MINUS: case MINUS_ASSIGNMENT: return "-";
                case MULTIPLY: case MULTIPLY_ASSIGNMENT: return "*";
                case DIVIDE: case DIVIDE_ASSIGNMENT: return "/";
                case REMAINDER: case REMAINDER_ASSIGNMENT: return "%";
                case LEFT_SHIFT: case LEFT_SHIFT_ASSIGNMENT: return "<<";
                case RIGHT_SHIFT: case RIGHT_SHIFT_ASSIGNMENT: return ">>";
                case UNSIGNED_RIGHT_SHIFT: case UNSIGNED_RIGHT_SHIFT_ASSIGNMENT: return ">>>";
                case AND: case AND_ASSIGNMENT: return "&";
                case OR: case OR_ASSIGNMENT: return "|";
                case XOR: case XOR_ASSIGNMENT: return "^";
                case CONDITIONAL_AND: return "&&";
                case CONDITIONAL_OR: return "||";
                case EQUAL_TO: return "==";
                case NOT_EQUAL_TO: return "!=";
                case LESS_THAN: return "<";
                case GREATER_THAN: return ">";
                case LESS_THAN_EQUAL: return "<=";
                case GREATER_THAN_EQUAL: return ">=";
                default: return "?";
            }
        }
    }
}
