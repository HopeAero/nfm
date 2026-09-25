// English -> Spanish, for the launcher, the HTML race menus and every string
// the game itself draws.
//
// The game's text is translated where it is DRAWN (graphics.js and
// canvas-graphics.js call tr() in both drawString and stringWidth), not at the
// ~100 call sites that build it: drawcs() centres a string by measuring it
// first, and measuring the English but drawing the Spanish would put every
// line off centre. The HTML side is translated by watching the document
// (translateDocument), so the launcher's many `textContent =` writes need no
// changes either.
//
// The language is read at load and can be switched live with setLang(): the
// document is re-translated from the English each node was first written in,
// and the game's text follows on its next frame because it is translated as it
// is drawn. English is the source; anything without an entry passes through
// unchanged, which is right for car and stage names.
//
// Text baked into the game's images is localized by ui-sprites-es.js at load;
// the English GIFs in images.zip remain unchanged.

import { CAREDITOR_ES, CAREDITOR_ES_PATTERNS } from './i18n-careditor.js';
import { STAGEMAKER_ES, STAGEMAKER_ES_PATTERNS } from './i18n-stagemaker.js';
import { EXT_ES, EXT_ES_PATTERNS } from './i18n-ext.js';

const STORE_KEY = 'nfm.launcher';

export let lang = (() => {
  try { return JSON.parse(localStorage.getItem(STORE_KEY) || '{}').lang === 'es' ? 'es' : 'en'; } catch { return 'en'; }
})();

/**
 * Switch language live. The caller persists the choice (it lives in the
 * launcher's settings). Game text drawn after this comes out in `l`; the
 * document is re-translated now.
 */
export function setLang(l) {
  lang = l === 'es' ? 'es' : 'en';
  if (typeof document !== 'undefined' && document.body) {
    document.documentElement.lang = lang;
    translateNode(document.body);
  }
}

const ES = {
  // ---- launcher ----
  'Need for': 'Need for', 'Madness': 'Madness',
  'Free Play': 'Juego libre', 'Unlock everything': 'Todo desbloqueado',
  'HUD on dark skies': 'HUD en cielos oscuros', 'outline': 'contorno',
  'automatic — colours by contrast': 'automático — colores por contraste',
  'boxes — as the original': 'cajas — como el original',
  'on — every stage and car': 'sí — todas las pistas y autos',
  'Developer mode': 'Modo desarrollador', 'on — URL test options work': 'sí — funcionan las opciones de prueba por URL',
  'off — win races to unlock': 'no — gana carreras para desbloquear',
  'Single Player': 'Un jugador', 'Multiplayer': 'Multijugador', 'Car Maker': 'Creador de autos', 'Stage Maker': 'Creador de pistas',
  'Extended Edition': 'Edición Extendida', 'Classic Race': 'Carrera clásica', 'Career Mode': 'Modo carrera',
  'Race': 'Correr', 'Stages': 'Pistas',
  'Back to the normal stage': 'Volver a la pista normal', 'BONUS STAGE!': '¡PISTA BONUS!', 'Scouting': 'Ver rivales', 'hard mode': 'modo difícil', 'scale levels': 'escalar niveles',
  'no levels': 'sin niveles', 'Change car': 'Cambiar auto', 'Menu': 'Menú',
  'xp gain: ENABLED': 'ganar XP: SÍ', 'xp gain: DISABLED': 'ganar XP: NO',
  '+ bonus stat points': '+ puntos de stats extra', 'No bonus stat points': 'Sin puntos de stats extra',
  '◂ ▸ stage · Enter race · Esc change car': '◂ ▸ pista · Enter correr · Esc cambiar auto',
  '◂ ▸ stage · ▴ ▾ stages · Enter race · Esc change car':
    '◂ ▸ pista · ▴ ▾ grupo · Enter correr · Esc cambiar auto', 'Career': 'Carrera',
  'Extended Edition is coming soon': 'La Edición Extendida llegará pronto',
  'Settings': 'Ajustes', 'Main menu': 'Menú principal',
  'press enter to race': 'presiona enter para correr', 'loading…': 'cargando…',
  'failed to load game data': 'no se pudieron cargar los datos del juego',
  'Esc — back': 'Esc — volver', 'Esc — leave': 'Esc — salir',
  'Car': 'Auto', 'Your car': 'Tu auto', 'Stage': 'Pista', 'Race Options': 'Opciones de carrera',
  'Start Race': 'Iniciar carrera', 'Enter to open': 'Enter para abrir', 'Enter to change': 'Enter para cambiar',
  'Cars on track': 'Autos en pista', 'Opponents': 'Rivales', 'Done': 'Listo',
  'same as mine': 'igual que el mío', 'chosen by stage': 'según la pista',
  'Your name': 'Tu nombre', 'your name': 'tu nombre', 'Sound': 'Sonido', 'Music': 'Música',
  'Resolution': 'Resolución', 'Smooth frames': 'Cuadros suaves', 'Replay recording': 'Grabar repetición',
  'Language': 'Idioma',
  'Beginner': 'Principiante', 'Amateur': 'Aficionado', 'Pro': 'Pro', 'Extreme': 'Extremo', 'Bonus': 'Bonus',
  ' · my car': ' · mi auto',
  'off': 'apagado', 'on': 'encendido', 'off — smoother': 'apagado — más fluido',
  'on — display rate': 'encendido — tasa de pantalla', 'off — 18.9 fps': 'apagado — 18.9 fps',
  'public — listed': 'pública — en la lista', 'private — code only': 'privada — solo con código',
  'Games': 'Partidas', 'Host a game': 'Crear partida', 'Join by code': 'Unirse con código',
  'Enter to type a code': 'Enter para escribir un código', 'Room code': 'Código de sala',
  'looking…': 'buscando…', 'no tracker — use a code': 'sin tracker — usa un código',
  'Players': 'Jugadores', 'Chat': 'Chat', 'Say': 'Decir', 'Say something': 'Di algo',
  'press Enter to type…': 'presiona Enter para escribir…', 'lobby': 'sala', 'lobby only': 'solo sala',
  'Waiting for the host…': 'Esperando al anfitrión…', '(unnamed)': '(sin nombre)',
  'could not load this stage': 'no se pudo cargar esta pista',
  'move': 'mover', 'select': 'elegir', 'change': 'cambiar', 'back': 'volver', 'leave': 'salir',
  'open / start': 'abrir / iniciar', 'join / host': 'unirse / crear', 'chat / start': 'chat / iniciar',
  'public–private': 'pública–privada',
  // ---- race menus (race-ui.js) ----
  'Resume Game': 'Continuar', 'Instant Replay': 'Repetición', 'Game Instructions': 'Instrucciones',
  'Quit Game': 'Salir del juego', '– PAUSED –': '– PAUSA –', 'ONLINE MENU': 'MENÚ EN LÍNEA',
  'RACE CONTINUES': 'LA CARRERA SIGUE', 'Pause menu': 'Menú de pausa', 'Online race menu': 'Menú de carrera en línea',
  'INSTANT REPLAY': 'REPETICIÓN', 'Enter or Esc returns to pause': 'Enter o Esc vuelve a la pausa',
  'Skip replay': 'Saltar repetición', 'Skip highlight': 'Saltar destacado',
  'RACE HIGHLIGHT · ENTER OR ESC TO SKIP': 'DESTACADO DE LA CARRERA · ENTER O ESC PARA SALTAR',
  'HIGHLIGHT COMPLETE': 'DESTACADO COMPLETO', 'HIGHLIGHT SKIPPED': 'DESTACADO SALTADO',
  'ENTER OR ESC TO RETURN TO THE LAUNCHER': 'ENTER O ESC PARA VOLVER AL MENÚ',
  'Continue': 'Continuar', 'Replay finished': 'Repetición terminada', 'Replay skipped': 'Repetición saltada',
  'Replay data is not ready yet. Keep racing for a few seconds.': 'La repetición aún no está lista. Sigue corriendo unos segundos.',
  'GAME INSTRUCTIONS': 'INSTRUCCIONES',
  'Drive with the arrow keys. Space uses the handbrake.': 'Maneja con las flechas. Espacio usa el freno de mano.',
  'points the arrow toward cars or the track.': 'apunta la flecha hacia los autos o la pista.',
  'shows the radar map.': 'muestra el mapa de radar.',
  'returns to the pause menu. Press it again to resume.': 'vuelve al menú de pausa. Presiónala otra vez para continuar.',
  'Back to pause menu': 'Volver al menú de pausa',
  'Best Stunt!': '¡Mejor acrobacia!', 'Close Finish!': '¡Final reñido!', 'Close Finish! Almost got it!': '¡Final reñido! ¡Casi!',
  'Stunts!': '¡Acrobacias!', 'Wasted!': '¡Destruido!',
  "You Wasted 'em!": '¡Los destruiste!', 'Close Finish!  Almost got it!': '¡Final reñido!  ¡Casi!',
  'Disconnected!': '¡Desconectado!',
  'Sorry not enough replay data to play available, please try again later.':
    'Aún no hay datos suficientes para la repetición, inténtalo más tarde.',
  'Replay recording is off. Enable it in launcher Settings, then start a new race.':
    'La grabación de repeticiones está apagada. Actívala en Ajustes y empieza una carrera nueva.',
  'Instant Replay is only available in a solo race.': 'La repetición solo está disponible en carreras de un jugador.', 'Replay  >': 'Repetición  >', 'Replay  >>': 'Repetición  >>',
  // ---- the game (xtGraphics) ----
  'Top Speed:': 'Velocidad:', 'Acceleration:': 'Aceleración:', 'Handling:': 'Manejo:',
  'Stunts:': 'Acrobacias:', 'Strength:': 'Fuerza:', 'Endurance:': 'Resistencia:',
  '[ Car Locked ]': '[ Auto bloqueado ]', 'Loading, please wait...': 'Cargando, espera por favor...',
  'Failed to load stage...': 'No se pudo cargar la pista...', 'Final Party Stage  >': 'Pista final de fiesta  >',
  'GAME SAVED': 'JUEGO GUARDADO', 'And:': 'Y:', 'Now get up and dance!': '¡Ahora levántate y baila!',
  "You're Awesome!": '¡Eres increíble!', "You're truly a RADICAL GAMER!": '¡Eres un verdadero JUGADOR RADICAL!',
  'A Game by Radicalplay.com': 'Un juego de Radicalplay.com',
  'Please check your connection!': '¡Revisa tu conexión!',
  'Sorry, You where Disconnected from Game!': '¡Lo sentimos, te desconectaste de la partida!',
  'Press  [ Enter ]  to continue': 'Presiona  [ Enter ]  para continuar',
  'Press [V] to change view.': 'Presiona [V] para cambiar la vista.',
  'Press [V] to change view.  Press [Enter] to exit.': 'Presiona [V] para cambiar la vista.  Presiona [Enter] para salir.',
  'Wrong Way!': '¡Sentido contrario!', 'Checkpoint Missed!': '¡Te saltaste un punto de control!',
  'Bad Landing!': '¡Mal aterrizaje!', 'Power low, perform stunt!': '¡Poca potencia, haz una acrobacia!',
  'Checkpoint!': '¡Punto de control!', 'Car Fixed': 'Auto reparado', 'Power To The MAX': 'Potencia al MÁXIMO',
  'Arrow Unlocked!': '¡Flecha liberada!', ' Arrow now pointing at >  CARS': ' La flecha apunta a >  AUTOS',
  ' Arrow now pointing at >  TRACK': ' La flecha apunta a >  PISTA',
  'You finished first, nice job!': '¡Llegaste primero, buen trabajo!',
  'You Won, all cars have been wasted!': '¡Ganaste, todos los autos fueron destruidos!',
  'Game got disconnected!': '¡La partida se desconectó!',
  'Click any player on the right to follow!': '¡Haz clic en un jugador a la derecha para seguirlo!',
  // stunt fragments (translated where XtGraphics assembles them)
  'Cool': 'Genial', 'Alright': 'Bien', 'Nice': 'Buena', 'Wicked': 'Brutal', 'Amazing': 'Increíble',
  'Super': 'Súper', 'Awesome': 'Asombroso', 'Ripping': 'Tremendo', 'Radical': 'Radical',
  'What the...?': '¿Pero qué...?', "You're a super star!!!!": '¡¡¡¡Eres una súper estrella!!!!',
  'Who are you again...?': '¿Y tú quién eres...?',
  'surf style': 'estilo surf', 'off the lip': 'desde el borde', 'bounce back': 'rebote',
  'Forward loop': 'Giro adelante', 'double Forward': 'doble adelante', 'triple Forward': 'triple adelante',
  'massive Forward looping': 'enorme giro adelante', 'Backloop': 'Giro atrás', 'double Back': 'doble atrás',
  'triple Back': 'triple atrás', 'massive Back looping': 'enorme giro atrás',
  'Tabletop and reversed Tabletop': 'Tabletop y Tabletop invertido', 'Tabletop': 'Tabletop',
  'Hanged ': 'Colgado ', 'Flipside': 'Vuelta lateral', 'Rollspin': 'Giro de lado',
  'double Rollspin': 'doble giro de lado', 'triple Rollspin': 'triple giro de lado',
  'massive Roll spinning': 'enorme giro de lado', ' with ': ' con ', ' by ': ' por ',
};

// Strings built around a name or a number. First match wins.
const ES_PATTERNS = [
  [/^(\d+) cars$/, '$1 autos'],
  [/^(\d+) cars · (.*)$/, (m, n, rest) => `${n} autos · ${tr(rest)}`],
  [/^(\d+) laps · (\d+) checkpoints$/, '$1 vueltas · $2 puntos de control'],
  [/^(English|Español) — Enter to apply$/, '$1 — Enter para aplicar'],
  [/^Class (.*?)( · my car)?$/, (m, c, mine) => `Clase ${tr(c)}${mine ? ' · mi auto' : ''}`],
  [/^(\d+) games? · Enter to join$/, (m, n) => `${n} partida${n === '1' ? '' : 's'} · Enter para unirse`],
  [/^Start Race — (\d+) players?$/, (m, n) => `Iniciar carrera — ${n} jugador${n === '1' ? '' : 'es'}`],
  [/^looking for room (.*)…$/, 'buscando la sala $1…'],
  [/^room (.*)$/, 'sala $1'],
  [/^(.*)'s game$/, 'partida de $1'],
  [/^could not open car select: (.*)$/, 'no se pudo abrir la selección de auto: $1'],
  [/^could not start: (.*)$/, 'no se pudo iniciar: $1'],
  [/^([\d.]+) \/ ([\d.]+) seconds$/, '$1 / $2 segundos'],
  [/^(.*) replay$/, (m, t) => `Repetición: ${tr(t)}`],
  // game
  [/^You Won!  At Stage(.*)$/, '¡Ganaste!  En la pista$1'],
  [/^You Lost!  At Stage(.*)$/, '¡Perdiste!  En la pista$1'],
  [/^Finished Watching Game!  At Stage(.*)$/, '¡Terminaste de ver la partida!  En la pista$1'],
  [/^Stage (\d+)  >$/, 'Pista $1  >'],
  [/^Stage (\d+): (.*)$/, 'Pista $1: $2'],
  [/^BONUS STAGE (\d+)$/, 'PISTA BONUS $1'],
  [/^Opponents: level (\d+)$/, 'Rivales: nivel $1'],
  [/^Stage (\d+) is now unlocked!$/, '¡La pista $1 ya está desbloqueada!'],
  [/^(.*) has been unlocked!$/, '¡$1 ha sido desbloqueado!'],
  [/^This car unlocks when stage (\d+) is completed\.\.\.$/, 'Este auto se desbloquea al completar la pista $1...'],
  [/^This stage will be unlocked when stage (\d+) is complete!$/, '¡Esta pista se desbloquea al completar la pista $1!'],
  [/^\[ Stage (\d+) Locked \]$/, '[ Pista $1 bloqueada ]'],
  [/^Woohoooo you finished NFM(\d) !!!$/, '¡¡¡Wujuuu terminaste NFM$1!!!'],
  [/^Power Up (\d+)%$/, 'Potencia +$1%'],
  [/^ Arrow Locked on >  (.*)$/, ' Flecha fijada en >  $1'],
  [/^Now following (.*)!$/, '¡Ahora sigues a $1!'],
  [/^You wasted (.*)!$/, '¡Destruiste a $1!'],
  [/^(.*) has been wasted! \(Disconnected\)$/, '¡$1 fue destruido! (Desconectado)'],
  [/^(.*) has been wasted!$/, '¡$1 fue destruido!'],
  [/^(.*) wasted (.*)!$/, '¡$1 destruyó a $2!'],
  [/^(.*) has wasted all the cars!$/, '¡$1 destruyó todos los autos!'],
  [/^Your clan (.*) has wasted all the cars!$/, '¡Tu clan $1 destruyó todos los autos!'],
  [/^(.*) finished first, race over!$/, '¡$1 llegó primero, fin de la carrera!'],
  [/^Please read the Game Instructions!(.*)$/, '¡Lee las instrucciones del juego!$1'],
  // the key hints: "<kbd>↑</kbd> move · <kbd>Enter</kbd> select" leaves "move ·"
  [/^(.+) ·$/, (m, w) => `${tr(w)} ·`],
];

Object.assign(ES, CAREDITOR_ES);
ES_PATTERNS.push(...CAREDITOR_ES_PATTERNS);
Object.assign(ES, STAGEMAKER_ES);
ES_PATTERNS.push(...STAGEMAKER_ES_PATTERNS);
// Extended's in-race text; its patterns are more specific than the base's ('X has wasted you!'), so first
Object.assign(ES, EXT_ES);
ES_PATTERNS.unshift(...EXT_ES_PATTERNS);

/** Translate one string. Leading/trailing whitespace is kept as it was. */
export function tr(s) {
  if (lang !== 'es' || typeof s !== 'string' || !s) return s;
  if (Object.prototype.hasOwnProperty.call(ES, s)) return ES[s];
  const core = s.trim();
  if (!core) return s;
  const lead = s.slice(0, s.indexOf(core));
  const tail = s.slice(lead.length + core.length);
  if (Object.prototype.hasOwnProperty.call(ES, core)) return lead + ES[core] + tail;
  // HTML text wraps and indents: match on the words, not the layout.
  const flat = core.replace(/\s+/g, ' ');
  if (flat !== core && Object.prototype.hasOwnProperty.call(ES, flat)) return lead + ES[flat] + tail;
  for (const [re, to] of ES_PATTERNS) {
    if (re.test(core)) return lead + core.replace(re, to) + tail;
  }
  // Several messages joined into one text (the car maker's readiness list):
  // translate each line on its own.
  if (core.includes('\n')) return lead + core.split('\n').map(tr).join('\n') + tail;
  return s;
}

const ATTRS = ['placeholder', 'aria-label', 'title', 'alt', 'label'];

// What each node says in English, so it can be re-translated either way. The
// record is {src, out}: if the node still shows `out` it is ours and `src` is
// its English; anything else was written since, and is the new English.
const texts = new WeakMap();                 // Text -> {src, out}
const attrs = new WeakMap();                 // Element -> Map(attr -> {src, out})

function translateNode(node) {
  if (node.nodeType === 3) {
    const r = texts.get(node);
    const src = r && node.nodeValue === r.out ? r.src : node.nodeValue;
    const out = tr(src);
    texts.set(node, { src, out });
    if (out !== node.nodeValue) node.nodeValue = out;
    return;
  }
  if (node.nodeType !== 1 || node.tagName === 'SCRIPT' || node.tagName === 'STYLE') return;
  for (const a of ATTRS) {
    const v = node.getAttribute(a);
    if (v === null) continue;
    let m = attrs.get(node);
    if (!m) attrs.set(node, (m = new Map()));
    const r = m.get(a);
    const src = r && v === r.out ? r.src : v;
    const out = tr(src);
    m.set(a, { src, out });
    if (out !== v) node.setAttribute(a, out);
  }
  for (const c of node.childNodes) translateNode(c);
}

/**
 * Translate the document now and keep translating whatever the page writes
 * into it later. A translated string maps to itself, so the observer's own
 * writes settle immediately.
 */
export function translateDocument(root = document.body) {
  if (!root) return;
  document.documentElement.lang = lang;
  translateNode(root);
  new MutationObserver((muts) => {
    for (const m of muts) {
      if (m.type === 'characterData') translateNode(m.target);
      else if (m.type === 'attributes') translateNode(m.target);
      else for (const n of m.addedNodes) translateNode(n);
    }
  }).observe(root, { subtree: true, childList: true, characterData: true, attributes: true, attributeFilter: ATTRS });
}
