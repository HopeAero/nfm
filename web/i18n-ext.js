// Spanish for Extended Mode's in-race text (web/ext/xtGraphics.js): the HUD,
// the messages, the stunt calls, the finish screens. Every drawString and
// stringWidth goes through tr() (graphics.js), so these are the whole strings
// the jar draws; the HUD labels are right-aligned by stringWidth, so a longer
// Spanish label keeps its right edge.
//
// Stunts are built from pieces (xtGraphics.stat): adjective, [surf style],
// [loop], [degrees | spin, joined by " with " / " by "], [" and beyond"], "!".
// They are translated piece by piece in the jar's order, as the base port's
// stunts are (i18n.js).

const ADJ = {
  Decent: 'Decente', Nice: 'Buena', Impressive: 'Impresionante',
  Breathtaking: 'Asombrosa', Excellent: 'Excelente', Quality: 'De calidad',
  Incredible: 'Increíble', 'World class': 'De clase mundial', Legendary: 'Legendaria',
};

// longest first, so "double forward" wins over "forward"
const PIECES = [
  ['tabletop and reversed tabletop', 'tabletop y tabletop invertido'],
  ['massive forward looping', 'enorme giro adelante'], ['massive back looping', 'enorme giro atrás'],
  ['massive roll spinning', 'enorme giro de lado'],
  ['double forward', 'doble adelante'], ['triple forward', 'triple adelante'], ['forward loop', 'giro adelante'],
  ['double back', 'doble atrás'], ['triple back', 'triple atrás'], ['backloop', 'giro atrás'],
  ['double rollspin', 'doble giro de lado'], ['triple rollspin', 'triple giro de lado'], ['rollspin', 'giro de lado'],
  ['flipside', 'vuelta lateral'], ['tabletop', 'tabletop'], ['hanged ', 'colgado '],
  ['surf style', 'estilo surf'], ['off the ramp', 'desde la rampa'], ['radical rebound', 'rebote radical'],
  [' and beyond', ' y más allá'], [' with ', ' con '], [' by ', ' por '],
];

/** An Extended stunt call ("Breathtaking forward loop by 360!"), or null if it is not one. */
export function trStunt(s) {
  const adj = Object.keys(ADJ).sort((a, b) => b.length - a.length).find((a) => s.startsWith(a));
  if (!adj || !s.endsWith('!')) return null;
  let rest = s.slice(adj.length, -1);
  if (rest && !rest.startsWith(' ')) return null;
  let out = '';
  while (rest) {
    const hit = PIECES.find(([en]) => rest.startsWith(en));
    if (hit) { out += hit[1]; rest = rest.slice(hit[0].length); continue; }
    const m = /^( |\d+)/.exec(rest);
    if (!m) return null;           // not the stunt grammar: leave it to the other rules
    out += m[1];
    rest = rest.slice(m[1].length);
  }
  return `¡${ADJ[adj]}${out}!`;
}

export const EXT_ES = {
  // HUD
  // lap: is right-aligned at x=43, so a long word runs off the left edge
  'lap:': 'vta:', st: 'º', nd: 'º', rd: 'º', th: 'º', 'position:': 'puesto:', 'wasted:': 'bajas:', 'wasted': 'bajas',
  Damage: 'Daño', Power: 'Poder', Special: 'Especial',
  // messages
  ' Arrow now pointing at  Cars  <': ' La flecha apunta a  Autos  <',
  ' Arrow now pointing at  Track  <': ' La flecha apunta a  Pista  <',
  'Power to the MAX': 'Potencia al MÁXIMO', 'Car fixed': 'Auto reparado', 'Bad landing!': '¡Mal aterrizaje!',
  "You're wasted! Press  [ Enter ]  to continue...": '¡Te destruyeron! Presiona  [ Enter ]  para continuar...',
  'Respawning at previous checkpoint...': 'Reapareciendo en el punto de control anterior...',
  'Fix your car at the fix hoop!': '¡Repara tu auto en el aro de reparación!',
  'Damage Titan by stunting!': '¡Daña a Titan con acrobacias!',
  'The undead cars are targeting you!': '¡Los autos no muertos van por ti!',
  '> > Press Enter for GAME INSTRUCTIONS! < <': '> > ¡Presiona Enter para ver las INSTRUCCIONES! < <',
  'To learn how to preform STUNTS!': '¡Para aprender a hacer ACROBACIAS!',
  // stunts that are a whole sentence (the top power band)
  'Beautiful, beautiful stunting.': 'Hermosas, hermosas acrobacias.',
  'Absolutely sensational stunting!': '¡Acrobacias absolutamente sensacionales!',
  'Simply magnificent.': 'Simplemente magnífico.',
  // finish screens
  'Your Awesome!': '¡Eres increíble!', 'Woohoooo you finished the game!!!': '¡¡¡Wujuuu terminaste el juego!!!',
  'Congratulations on completing the bonus stage!': '¡Felicidades por completar la pista bonus!',
  'Your reward is:': 'Tu recompensa es:',
  'Congratulations on unlocking the 3 bonus cars!': '¡Felicidades por desbloquear los 3 autos bonus!',
  'Congratulations on unlocking these powerful cars!': '¡Felicidades por desbloquear estos autos tan poderosos!',
  'While these cars are quite hard to train, they are': 'Aunque estos autos son difíciles de entrenar, son',
  'very powerful and worth using!': '¡muy poderosos y vale la pena usarlos!',
  'You have unlocked the ability to use "car points", which can give your':
    'Desbloqueaste los "puntos de auto", que le pueden dar a tus',
  'cars unique powers and perks, which last even if you then reset them.':
    'autos poderes y ventajas únicos, que se mantienen aunque luego los reinicies.',
  'Earn car points by resetting (now "selling") unwanted cars.':
    'Gana puntos de auto reiniciando (ahora "vendiendo") los autos que no quieras.',
  'YOU HAVE GAINED 1 BONUS STAT POINT!': '¡GANASTE 1 PUNTO DE STATS EXTRA!',
  // ---- the car select (xtGraphics.carselect) ----
  'SPECIAL ATTACK:': 'ATAQUE ESPECIAL:', "LET'S GO!": '¡VAMOS!', TRANSFER: 'TRANSFERIR',
  'bonus cars': 'autos bonus', 'normal cars': 'autos normales', 'extra stats': 'stats extra', 'normal stats': 'stats normales',
  'RESET CAR': 'REINICIAR', 'SELL CAR': 'VENDER AUTO', 'CHANGE STATS': 'CAMBIAR STATS',
  'RESHUFFLE STATS': 'MEZCLAR STATS', 'LEVEL TRANSFER': 'PASAR NIVEL', YES: 'SÍ', NO: 'NO',
  'OVERALL:': 'GENERAL:', 'TOP SPEED:': 'VELOCIDAD:', 'ACCELERATION:': 'ACELERACIÓN:', 'CONTROL:': 'CONTROL:',
  'STUNTING:': 'ACROBACIAS:', 'STRENGTH:': 'FUERZA:', 'DEFENCE:': 'DEFENSA:',
  '[ Car Locked ]': '[ Auto bloqueado ]',
  'This car unlocks when stage 30 is completed...': 'Este auto se desbloquea al completar la pista 30...',
  'This car unlocks when you complete the first bonus stage...': 'Este auto se desbloquea al completar la primera pista bonus...',
  'This car unlocks when you complete the second bonus stage...': 'Este auto se desbloquea al completar la segunda pista bonus...',
  'This car unlocks when you complete the second bonus stage by racing...': 'Este auto se desbloquea al ganar la segunda pista bonus corriendo...',
  'This car unlocks when you complete the second bonus stage by wasting...': 'Este auto se desbloquea al ganar la segunda pista bonus destruyendo...',
  'This car unlocks when you complete the third bonus stage...': 'Este auto se desbloquea al completar la tercera pista bonus...',
  'This car unlocks when you complete the fifth bonus stage...': 'Este auto se desbloquea al completar la quinta pista bonus...',
  'This car unlocks when you complete the sixth bonus stage...': 'Este auto se desbloquea al completar la sexta pista bonus...',
  // special attacks
  'A random car gets reduced speed.': 'Un auto al azar pierde velocidad.',
  'Swaps its strength with a random car.': 'Cambia su fuerza con un auto al azar.',
  'Unlimited power.': 'Potencia ilimitada.', 'You get unlimited power.': 'Tienes potencia ilimitada.',
  "Drains a random car's health.": 'Le drena la vida a un auto al azar.',
  'Reduces the defence of the car in first.': 'Reduce la defensa del auto en primer lugar.',
  "Reduces a random car's defence.": 'Reduce la defensa de un auto al azar.',
  'Reduces the defence of a random car.': 'Reduce la defensa de un auto al azar.',
  'Reduces the speed of a random car.': 'Reduce la velocidad de un auto al azar.',
  'These boosts double past 50% damage.': 'Se duplican pasado el 50% de daño.',
  // the stat "+" tooltips
  '+ overall speed': '+ velocidad general', '+ acceleration': '+ aceleración', '+ grip on the ground': '+ agarre en el suelo',
  '+ stunting speed': '+ velocidad de acrobacias', '+ distance reached in air': '+ distancia en el aire',
  '+ damage dealt': '+ daño causado', '+ maximum health': '+ vida máxima', '+ stage hazard protection': '+ protección contra la pista',
  '+ debuff power/resistance': '+ poder/resistencia a efectos', '+ braking power*': '+ potencia de frenado*',
  '+ reversing speed*': '+ velocidad en reversa*', '+ turning sensitivity*': '+ sensibilidad de giro*',
  '+ power efficiency*': '+ eficiencia de potencia*', '+ time spent at MAX power*': '+ tiempo con potencia MÁXIMA*',
  '+ special attack duration*': '+ duración del ataque especial*', '* only affected by stat points.': '* solo lo afectan los puntos de stats.',
  // reset / sell / change stats / transfer
  'are you sure? You get no': '¿seguro? No recibes', 'stat points from this!': '¡puntos de stats por esto!',
  'points. Continue?': 'de auto. ¿Continuar?', 'This lets you either': 'Esto te deja',
  'reshuffle the stats of': 'mezclar los stats de', 'your car, or transfer': 'tu auto, o pasar',
  'your level and stat': 'tu nivel y puntos', 'points to another car.': 'de stats a otro auto.',
  'Unlocking a new stage': 'Desbloquear una pista nueva', 'gives you a free stat': 'te da una mezcla de',
  'reshuffle, but costs': 'stats gratis, si no cuesta', 'stat points otherwise.': 'puntos de stats.',
  'lets you transfer your': 'te deja pasar tu', 'level and stat points': 'nivel y puntos de stats',
  'to another car once.': 'a otro auto una vez.',
  'No free reshuffles left.': 'No quedan mezclas gratis.', '1 free reshuffle left.': 'Queda 1 mezcla gratis.',
  'No level transfers left.': 'No quedan pases de nivel.', '1 level transfer left.': 'Queda 1 pase de nivel.',
  "you'll get your stat points": 'recuperas tus puntos de stats', 'do you want to continue?': '¿quieres continuar?',
  'you will reset this car and': 'vas a reiniciar este auto y', 'transfer its level and stat': 'pasar su nivel y puntos',
  'select car to': 'elige el auto', 'transfer to...': 'que recibe...', 'transfer to': 'pasar a',
  'This cannot be undone and will save the': 'Esto no se puede deshacer y guardará la',
  'game. Continue?': 'partida. ¿Continuar?', 'You will reset your:': 'Vas a reiniciar tu:',
  'And transfer its level and stat points to:': 'Y pasar su nivel y puntos de stats a:',
};

const STUNT = new RegExp(`^(${Object.keys(ADJ).join('|')})( .*)?!$`);

export const EXT_ES_PATTERNS = [
  [/^Power up (\d+)%$/, 'Potencia +$1%'],
  // the car select
  [/^Created by (.*)$/, 'Creado por $1'],
  [/^STAT POINTS:(\s*)(-?\d+)$/, 'PUNTOS DE STATS:$1$2'],
  [/^CAR POINTS:(\s*)(-?\d+)$/, 'PUNTOS DE AUTO:$1$2'],
  [/^This car unlocks when stage (\d+) is completed\.\.\.$/, 'Este auto se desbloquea al completar la pista $1...'],
  [/^you will get (\d+) car$/, 'recibirás $1 puntos'],
  [/^back but at the cost of (\d+)\.$/, 'pero te cuesta $1.'],
  [/^(\d+) free reshuffles left\.$/, 'Quedan $1 mezclas gratis.'],
  [/^(\d+) level transfers left\.$/, 'Quedan $1 pases de nivel.'],
  [/^(\d+)% speed boost\.$/, '+$1% de velocidad.'],
  [/^(\d+)% strength boost\.$/, '+$1% de fuerza.'],
  [/^(\d+)% control boost\.$/, '+$1% de control.'],
  [/^(\d+)% defence boost\.$/, '+$1% de defensa.'],
  [/^(\d+)% speed cut\.$/, '-$1% de velocidad.'],
  [/^(\d+)% speed\/strength boost\.$/, '+$1% de velocidad/fuerza.'],
  [/^(\d+)% strength\/defence boost\.$/, '+$1% de fuerza/defensa.'],
  [/^(\d+)% strength and (\d+)% speed boost\.$/, '+$1% de fuerza y +$2% de velocidad.'],
  [/^Strength\/Defence boost: (.*)$/, 'Fuerza/Defensa: +$1'],
  [/^Strength\/Speed boost: (.*)$/, 'Fuerza/Velocidad: +$1'],
  [/^Speed\/Stunting boost: (.*)$/, 'Velocidad/Acrobacias: +$1'],
  [/^Speed boost: (.*)$/, 'Velocidad: +$1'],
  [/^Control boost: (.*)$/, 'Control: +$1'],
  [/^Acceleration boost: (.*)$/, 'Aceleración: +$1'],
  [/^Defence boost: (.*)$/, 'Defensa: +$1'],
  [/^Every stat increases by (\d+)%\.$/, 'Todos los stats suben $1%.'],
  [/^(.*) has wasted you!$/, '¡$1 te destruyó!'],
  [/^(.*) has wasted (?!all the cars!)(.*)!$/, '¡$1 destruyó a $2!'],
  [/^The undead cars are targeting (.*)!$/, '¡Los autos no muertos van por $1!'],
  [/^YOU HAVE GAINED (\d+) BONUS STAT POINTS!$/, '¡GANASTE $1 PUNTOS DE STATS EXTRA!'],
  [/^Stage (\d+) Completed!$/, '¡Pista $1 completada!'],
  [/^Stage (\d+) is now unlocked!$/, '¡La pista $1 ya está desbloqueada!'],
  [/^Failed to complete Stage (\d+)!$/, '¡No completaste la pista $1!'],
  [/^If you beat the stage again by wasting, you can unlock (.*)!$/, '¡Si vuelves a ganar la pista destruyendo, desbloqueas a $1!'],
  [/^If you beat the stage again by racing, you can unlock (.*)!$/, '¡Si vuelves a ganar la pista corriendo, desbloqueas a $1!'],
  [/^(-?\d+) TOTAL WASTES$/, '$1 DESTRUCCIONES EN TOTAL'],
  [/^(-?\d+) WASTES WITH (.*)$/, '$1 DESTRUCCIONES CON $2'],
  [/^(-?\d+) CLEARED IN TOTAL$/, '$1 PISTAS GANADAS EN TOTAL'],
  [/^(-?\d+) CLEARED WITH (.*)$/, '$1 PISTAS GANADAS CON $2'],
  [/^(\d+(?:\.\d+)?)(st|nd|rd|th)$/, '$1º'],
  [/^Level (\d+)$/, 'Nivel $1'],
  [/^level (\d+)(.*)$/, 'nivel $1$2'],
  [/^strength: (.*)$/, 'fuerza: $1'],
  [/^speed: (.*) mph$/, 'velocidad: $1 mph'],
  [STUNT, (s) => trStunt(s) ?? s],
];
