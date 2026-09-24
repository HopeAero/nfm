// Float/double audit, Java side. Types every subexpression of the decompiled
// source with javac's own attribution and prints, per numeric statement, where
// Java rounds to float32. web/tools/float-audit.mjs reads this and compares it
// against the port's fr() placement.
//
// Why javac and not a regex: whether `a * b * 1.5` rounds depends on the
// declared types of a and b and on the literal's suffix, which only an
// attributed tree knows. Literal and variable types in procyon's output are
// reliable; compound-assignment casts are not (TRANSPILE_SPEC §2), so the
// report tags those statements for a bytecode check instead of trusting them.
//
// Build and run with JDK 17+ (the installed `java` 8 has no jdk.compiler):
//   JDK="/c/Program Files/Eclipse Adoptium/jdk-21.0.5.11-hotspot/bin"
//   X=--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
//   "$JDK/javac" $X -d /tmp/fa web/tools/FloatAudit.java
//   "$JDK/java" $X -cp /tmp/fa FloatAudit java/Game.jar decompilation/java-src/*.java > /tmp/fa.jsonl
// Then the compound-assignment verdicts from the jar (see compound-verdicts.py),
// and the comparison (on Windows, java wants `cygpath -w` paths):
//   C="Mad ContO Record Control CarDefine xtGraphics"
//   "$JDK/javac" -nowarn -proc:none --release 8 -cp java/Game.jar -d /tmp/recomp //       $(for c in $C; do echo decompilation/java-src/$c.java; done)
//   (cd /tmp/orig && unzip -o java/Game.jar $(for c in $C; do echo $c.class; done))
//   python3 web/tools/compound-verdicts.py /tmp/recomp /tmp/orig $C > /tmp/verdicts.json
//   node web/tools/float-audit.mjs /tmp/fa.jsonl --bytecode=/tmp/verdicts.json   # expect 0 findings
//
// Node ids are pre-order over a canonical tree that both sides build the same
// way: parens, casts and the port's numeric wrappers (fr/trunc/i32/...) are
// transparent; a chain of plain names (`this.m.x`, `Math.abs`) is one leaf.

import com.sun.source.tree.*;
import com.sun.source.util.*;
import com.sun.tools.javac.tree.JCTree;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.*;
import java.math.BigDecimal;
import java.util.*;

public class FloatAudit {
  static CompilationUnitTree unit;
  static SourcePositions pos;
  static CharSequence text;

  public static void main(String[] a) throws Exception {
    JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager fm = jc.getStandardFileManager(null, null, null);
    List<String> files = Arrays.asList(a).subList(1, a.length);
    JavacTask task = (JavacTask) jc.getTask(null, fm, d -> {}, List.of("-proc:none", "-cp", a[0]),
        null, fm.getJavaFileObjectsFromStrings(files));
    Iterable<? extends CompilationUnitTree> units = task.parse();
    task.analyze();
    pos = Trees.instance(task).getSourcePositions();
    StringBuilder out = new StringBuilder();
    for (CompilationUnitTree u : units) {
      unit = u;
      text = u.getSourceFile().getCharContent(true);
      new Scan(out).scan(u, null);
    }
    System.out.print(out);
  }

  static char typeCode(Tree t) {
    TypeMirror ty = ((JCTree) t).type;
    if (ty == null) return 'O';
    TypeKind k = ty.getKind();
    switch (k) {
      case FLOAT: return 'F';
      case DOUBLE: return 'D';
      case INT: case SHORT: case BYTE: case CHAR: return 'I';
      case LONG: return 'J';
      case BOOLEAN: return 'Z';
      default: return 'O';
    }
  }

  static String src(Tree t) {
    long s = pos.getStartPosition(unit, t), e = pos.getEndPosition(unit, t);
    return s < 0 || e < 0 ? "" : text.subSequence((int) s, (int) e).toString();
  }

  static long line(Tree t) {
    return unit.getLineMap().getLineNumber(pos.getStartPosition(unit, t));
  }

  static class Scan extends TreePathScanner<Void, Void> {
    final StringBuilder out;
    String cls = "", method = "";
    Scan(StringBuilder out) { this.out = out; }

    @Override public Void visitClass(ClassTree c, Void v) {
      String prev = cls;
      if (cls.isEmpty()) cls = c.getSimpleName().toString();
      super.visitClass(c, v);
      cls = prev;
      return null;
    }

    @Override public Void visitMethod(MethodTree m, Void v) {
      String prev = method;
      method = m.getName().contentEquals("<init>") ? "constructor" : m.getName().toString();
      super.visitMethod(m, v);
      method = prev;
      return null;
    }

    @Override public Void visitExpressionStatement(ExpressionStatementTree s, Void v) {
      ExpressionTree e = s.getExpression();
      if (e instanceof AssignmentTree as) emit(s, ser(as.getVariable(), null), as.getExpression(), null, false);
      else if (e instanceof CompoundAssignmentTree ca) emit(s, ser(ca.getVariable(), null), ca.getExpression(), ca, true);
      else if (e instanceof MethodInvocationTree) emit(s, "call", e, null, false);
      return super.visitExpressionStatement(s, v);
    }

    @Override public Void visitVariable(VariableTree vt, Void v) {
      if (vt.getInitializer() != null && !method.isEmpty()) emit(vt, vt.getName().toString(), vt.getInitializer(), null, false);
      return super.visitVariable(vt, v);
    }

    @Override public Void visitReturn(ReturnTree r, Void v) {
      if (r.getExpression() != null) emit(r, "return", r.getExpression(), null, false);
      return super.visitReturn(r, v);
    }

    @Override public Void visitIf(IfTree t, Void v) { emit(t, "if", t.getCondition(), null, false); return super.visitIf(t, v); }
    @Override public Void visitWhileLoop(WhileLoopTree t, Void v) { emit(t, "while", t.getCondition(), null, false); return super.visitWhileLoop(t, v); }

    void emit(Tree stmt, String target, ExpressionTree rhs, CompoundAssignmentTree ca, boolean compound) {
      Ser s = new Ser();
      String key;
      int rhsId = -1;
      if (ca != null) {
        // x op= e is x = (T)(x op e): one synthetic binary node, then x, then e.
        int id = s.next++;
        s.types.append(binaryType(typeCode(ca.getVariable()), typeCode(ca.getExpression()), ca.getKind()));
        s.flags.append('.');
        char lhs = typeCode(ca.getVariable());
        if (lhs == 'F' && s.types.charAt(id) != 'F') s.rounded.add(id);
        if (s.types.charAt(id) == 'F') s.rounded.add(id);
        int li = s.next;
        String l = s.ser(ca.getVariable());
        rhsId = s.next;
        String r = s.ser(ca.getExpression());
        // Same exactness rule as a binary node (see Ser.flags).
        char lf = s.flags.charAt(li), rf = s.flags.charAt(rhsId);
        Tree.Kind k = ca.getKind();
        if ((k == Tree.Kind.MULTIPLY_ASSIGNMENT && (rf == 'P' || rf == 'q'))
            || (k == Tree.Kind.DIVIDE_ASSIGNMENT && (rf == 'P' || rf == 'q'))) s.flags.setCharAt(id, 'x');
        else if ((lf == 'g' || lf == 'P') && (rf == 'g' || rf == 'P') && k != Tree.Kind.DIVIDE_ASSIGNMENT) s.flags.setCharAt(id, 'g');
        key = target + "=(" + l + op(ca.getKind()) + r + ")";
      } else {
        String body = s.ser(rhs);
        key = target + "=" + body;
      }
      if (s.types.indexOf("F") < 0 && s.types.indexOf("D") < 0) return;  // not float/double arithmetic
      out.append("{\"c\":").append(q(cls)).append(",\"m\":").append(q(method))
         .append(",\"l\":").append(line(stmt)).append(",\"k\":").append(q(key))
         .append(",\"r\":").append(s.rounded).append(",\"d\":").append(s.idiv).append(",\"t\":").append(q(s.types.toString())).append(",\"g\":").append(q(s.flags.toString()))
         .append(",\"ca\":").append(compound).append(",\"ri\":").append(rhsId).append(",\"s\":").append(q(src(stmt).replaceAll("\\s+", " ")))
         .append("}\n");
    }

    String ser(Tree t, Void unused) { return new Ser().ser(t); }
  }

  static char binaryType(char a, char b, Tree.Kind k) {
    if (a == 'D' || b == 'D') return 'D';
    if (a == 'F' || b == 'F') return 'F';
    if (a == 'J' || b == 'J') return 'J';
    return 'I';
  }

  static class Ser {
    int next = 0;
    StringBuilder types = new StringBuilder();
    List<Integer> rounded = new ArrayList<>();
    List<Integer> idiv = new ArrayList<>();

    // Per node: 'g' integral-valued (an int, an integer literal, or + - * of
    // those), 'P' an integral power-of-two literal, 'q' a fractional one, 'x'
    // a float op that is exact anyway (a product or quotient by a power of
    // two). A missing fr() on a 'g' or 'x' node cannot change the value while
    // it stays under 2^24, so the report files those apart.
    StringBuilder flags = new StringBuilder();

    int node(Tree t) {
      char ty = typeCode(t);
      types.append(ty);
      flags.append(ty == 'I' || ty == 'J' ? 'g' : '.');
      return next++;
    }

    String ser(Tree t) {
      while (t instanceof ParenthesizedTree p) t = p.getExpression();
      if (t instanceof TypeCastTree c) {
        // A cast is transparent; a cast TO float of a non-float marks its operand.
        Tree x = c.getExpression();
        while (x instanceof ParenthesizedTree p) x = p.getExpression();
        boolean toFloat = typeCode(c) == 'F';
        int id = next;
        String s = ser(x);
        if (toFloat && types.charAt(id) != 'F') rounded.add(id);
        return s;
      }
      String chain = chain(t);
      if (chain != null) { node(t); return chain; }
      if (t instanceof BinaryTree b) {
        int id = node(t);
        if (types.charAt(id) == 'F') rounded.add(id);
        if (b.getKind() == Tree.Kind.DIVIDE && (types.charAt(id) == 'I' || types.charAt(id) == 'J')) idiv.add(id);
        int li = next;
        String ls = ser(b.getLeftOperand());
        int ri = next;
        String rs = ser(b.getRightOperand());
        char lf = flags.charAt(li), rf = flags.charAt(ri);
        boolean lg = lf == 'g' || lf == 'P', rg = rf == 'g' || rf == 'P';
        Tree.Kind k = b.getKind();
        boolean additive = k == Tree.Kind.PLUS || k == Tree.Kind.MINUS || k == Tree.Kind.MULTIPLY;
        if (flags.charAt(id) != 'g') {
          if (additive && lg && rg) flags.setCharAt(id, 'g');
          else if (k == Tree.Kind.MULTIPLY && (lf == 'P' || lf == 'q' || rf == 'P' || rf == 'q')) flags.setCharAt(id, 'x');
          else if (k == Tree.Kind.DIVIDE && (rf == 'P' || rf == 'q')) flags.setCharAt(id, 'x');
        }
        return "(" + ls + op(b.getKind()) + rs + ")";
      }
      if (t instanceof UnaryTree u) {
        int id = node(t);
        String x = ser(u.getExpression());
        char f = flags.charAt(id + 1);
        // -P is still a power of two, so a product by it is still exact.
        if (u.getKind() == Tree.Kind.UNARY_MINUS && (f == 'g' || f == 'P' || f == 'q')) flags.setCharAt(id, f);
        return uop(u.getKind()) + "(" + x + ")";
      }
      if (t instanceof LiteralTree l) {
        int id = node(t);
        if (l.getValue() instanceof Number n) {
          double d = Math.abs(n.doubleValue());
          boolean pow2 = d > 0 && (Double.doubleToLongBits(d) & 0x000fffffffffffffL) == 0;
          if (pow2) flags.setCharAt(id, d >= 1 ? 'P' : 'q');
          else if (d == Math.rint(d)) flags.setCharAt(id, 'g');
        }
        return lit(l);
      }
      if (t instanceof MethodInvocationTree mi) {
        node(t);
        StringBuilder sb = new StringBuilder(ser(mi.getMethodSelect())).append("(");
        for (int i = 0; i < mi.getArguments().size(); i++) sb.append(i > 0 ? "," : "").append(ser(mi.getArguments().get(i)));
        return sb.append(")").toString();
      }
      if (t instanceof ArrayAccessTree aa) {
        node(t);
        return ser(aa.getExpression()) + "[" + ser(aa.getIndex()) + "]";
      }
      if (t instanceof MemberSelectTree ms) {
        node(t);
        return ser(ms.getExpression()) + "." + ms.getIdentifier();
      }
      if (t instanceof ConditionalExpressionTree ce) {
        node(t);
        return "(" + ser(ce.getCondition()) + "?" + ser(ce.getTrueExpression()) + ":" + ser(ce.getFalseExpression()) + ")";
      }
      node(t);
      return "?";
    }

    /** `this.a.b`, `Math.abs`, `n`: a dotted name with no call or index in it. */
    static String chain(Tree t) {
      if (t instanceof IdentifierTree id) return id.getName().contentEquals("this") ? null : id.getName().toString();
      if (t instanceof MemberSelectTree ms) {
        ExpressionTree e = ms.getExpression();
        if (e instanceof IdentifierTree id && id.getName().contentEquals("this")) return ms.getIdentifier().toString();
        String head = chain(e);
        return head == null ? null : head + "." + ms.getIdentifier();
      }
      return null;
    }

    static String lit(LiteralTree l) {
      Object v = l.getValue();
      if (v instanceof Character c) return Integer.toString(c);
      if (v instanceof Integer || v instanceof Long) return v.toString();
      if (v instanceof Float || v instanceof Double) {
        String s = src(l).replaceAll("[fFdD]$", "");
        double d = Double.parseDouble(s);
        if (d == Math.rint(d) && Math.abs(d) < 1e15) return Long.toString((long) d);
        // procyon prints float literals widened (0.33f as 0.33000001311302185);
        // 6 significant digits is the key both sides agree on.
        return new BigDecimal(s).round(new java.math.MathContext(6)).stripTrailingZeros().toPlainString();
      }
      if (v instanceof Boolean) return v.toString();
      return "str";
    }
  }

  static String op(Tree.Kind k) {
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
      case LESS_THAN: return "<";
      case GREATER_THAN: return ">";
      case LESS_THAN_EQUAL: return "<=";
      case GREATER_THAN_EQUAL: return ">=";
      case EQUAL_TO: return "==";
      case NOT_EQUAL_TO: return "!=";
      case CONDITIONAL_AND: return "&&";
      case CONDITIONAL_OR: return "||";
      default: return "?";
    }
  }

  static String uop(Tree.Kind k) {
    switch (k) {
      case UNARY_MINUS: return "-";
      case UNARY_PLUS: return "+";
      case LOGICAL_COMPLEMENT: return "!";
      case BITWISE_COMPLEMENT: return "~";
      case PREFIX_INCREMENT: case POSTFIX_INCREMENT: return "++";
      case PREFIX_DECREMENT: case POSTFIX_DECREMENT: return "--";
      default: return "?";
    }
  }

  static String q(String s) {
    StringBuilder b = new StringBuilder("\"");
    for (char ch : s.toCharArray()) {
      if (ch == '"' || ch == '\\') b.append('\\').append(ch);
      else if (ch < 32) b.append(' ');
      else b.append(ch);
    }
    return b.append('"').toString();
  }
}
