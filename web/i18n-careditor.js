// The car maker's half of the Spanish dictionary (see i18n.js). Its long,
// multi-line help texts are not here: they are translated by index in
// careditor/helptext-es.js.

export const CAREDITOR_ES = {
  // ---- page ----
  'Car Maker': 'Creador de autos', '← Launcher': '← Menú',
  'Body': 'Carrocería', 'Wheels': 'Ruedas', 'Stats': 'Estadísticas', 'Physics': 'Física',
  'Colours': 'Colores', 'First': 'Primero', 'Second': 'Segundo',
  'Every piece of the car stores its own colour, so changing one here repaints all the pieces that were the old colour. Pieces you painted some other shade are left alone.':
    'Cada pieza del auto guarda su propio color, así que cambiar uno aquí repinta todas las piezas que tenían el color anterior. Las piezas que pintaste de otro tono se quedan como están.',
  'Scale': 'Escala', 'Scale X': 'Escala X', 'Scale Y': 'Escala Y', 'Scale Z': 'Escala Z', 'Align': 'Alinear', 'Rotate X 90°': 'Rotar X 90°', 'Rotate Y 90°': 'Rotar Y 90°',
  'Rotate Z 90°': 'Rotar Z 90°', 'Mirror in X': 'Reflejar en X',
  'Source (.rad)': 'Código (.rad)', 'Save': 'Guardar', 'Save •': 'Guardar •', 'New…': 'Nuevo…',
  'Save as…': 'Guardar como…', 'Delete': 'Borrar', 'Import…': 'Importar…', 'Export': 'Exportar',
  'Preview': 'Vista previa', 'pieces': 'piezas', 'wheels': 'ruedas', 'radius': 'radio',
  'Test drive': 'Probar', 'Crash': 'Choque', 'Roof crash': 'Choque de techo', 'Fix': 'Reparar',
  'Pause spin': 'Pausar giro', 'Spin': 'Girar', 'Reset view': 'Restablecer vista',
  'Drag to turn the car, scroll to zoom, or use the arrow keys. The car is drawn here exactly the way the game draws it, so this is what you will be driving.':
    'Arrastra para girar el auto, usa la rueda del mouse para acercar o las flechas del teclado. El auto se dibuja aquí exactamente como lo dibuja el juego, así que esto es lo que vas a manejar.',
  "This is the car itself, as text — everything above just edits it for you, and you can type here directly. Your cars are saved in this browser. The default cars can't be overwritten: save one and you get your own copy to race, with the original still there. Export gives you a":
    'Este es el auto en sí, como texto: todo lo de arriba solo lo edita por ti, y también puedes escribir aquí directamente. Tus autos se guardan en este navegador. Los autos del juego no se pueden sobrescribir: si guardas uno, obtienes tu propia copia para correr y el original sigue ahí. Exportar te da un archivo',
  'file you can share or open in the desktop game.': 'que puedes compartir o abrir en el juego de escritorio.',
  'Class': 'Clase', 'Crash look': 'Cómo se ve al chocar', 'Engine': 'Motor', 'Match': 'Copiar de',
  'Car preview. Arrow keys turn the car, plus and minus zoom.':
    'Vista previa del auto. Las flechas giran el auto; más y menos acercan.',
  'Car source': 'Código del auto',
  'Front wheels': 'Ruedas delanteras', 'Back wheels': 'Ruedas traseras', 'Rims': 'Rines',
  'Colour of the rims inside the tyres.': 'Color de los rines dentro de las llantas.',
  'not set': 'sin definir', 'not calibrated': 'sin calibrar',
  'ready to race': 'listo para correr', 'not raceable yet': 'todavía no se puede correr',
  'My cars': 'Mis autos', 'Default Cars': 'Autos del juego', 'My Car': 'Mi auto',
  "Start from one of the game's own cars — copies its stats so you can adjust from there.":
    'Parte de uno de los autos del juego: copia sus estadísticas para que ajustes desde ahí.',
  "This is one of the game's cars. Saving makes your own copy of it — the original stays as it is.":
    'Este es uno de los autos del juego. Al guardar se crea tu propia copia; el original se queda como está.',
  'Flip the car left-to-right. Select part of the text below first to flip only that part — which is how you build one side and mirror it onto the other.':
    'Voltea el auto de izquierda a derecha. Selecciona antes una parte del texto de abajo para voltear solo esa parte: así se construye un lado y se refleja en el otro.',
  'Save as:': 'Guardar como:', 'Name for the new car:': 'Nombre del auto nuevo:',
  // ---- stats, physics, engine (rad.js / helptext.js) ----
  'Speed': 'Velocidad', 'Acceleration': 'Aceleración', 'Stunts': 'Acrobacias',
  'Strength': 'Fuerza', 'Endurance': 'Resistencia',
  'Handbrake': 'Freno de mano', 'Turning Sensitivity': 'Sensibilidad de giro', 'Tire Grip': 'Agarre',
  'Bouncing': 'Rebote', 'Lifts Others': 'Levanta a otros', 'Gets Lifted': 'Es levantado',
  'Pushes Others': 'Empuja a otros', 'Gets Pushed': 'Es empujado',
  'Aerial Rotation Speed': 'Giro en el aire', 'Aerial Control/Gliding': 'Control aéreo / planeo',
  'Crash Radius': 'Radio de choque', 'Crash Magnitude': 'Magnitud de choque', 'Roof Destruction': 'Destrucción del techo',
  'Normal Engine': 'Motor normal', 'V8 Engine': 'Motor V8', 'Retro Engine': 'Motor retro',
  'Power Engine': 'Motor potente', 'Diesel Engine': 'Motor diésel',
  "Like Tornado Shark, Sword of Justice or Radical One's engine.": 'Como el motor de Tornado Shark, Sword of Justice o Radical One.',
  "High speed engine like Formula 7, Drifter X or Might Eight's engine.": 'Motor de alta velocidad, como el de Formula 7, Drifter X o Mighty Eight.',
  'Like Wow Caninaro, Lead Oxide or Kool Kat’s engine.': 'Como el motor de Wow Caninaro, Lead Oxide o Kool Kat.',
  'Turbo/super charged engine like Max Revenge, High Rider or Dr Monstaa’s engine.': 'Motor turbo, como el de MAX Revenge, High Rider o DR Monstaa.',
  'Big diesel powered engine for big cars like EL King or  M A S H E E N .': 'Gran motor diésel para autos grandes como EL KING o M A S H E E N.',
  'Top speed. The five stats share a fixed budget, so raising one lowers the others.':
    'Velocidad máxima. Las cinco estadísticas comparten un presupuesto fijo, así que subir una baja las demás.',
  'How hard the car pulls away from a standstill and out of corners.':
    'Con cuánta fuerza arranca el auto desde parado y al salir de las curvas.',
  'How well the car flips and spins in mid-air. Also makes the two aerial settings on the Physics tab count for more.':
    'Qué tan bien se voltea y gira el auto en el aire. También hace que los dos ajustes aéreos de la pestaña Física pesen más.',
  'How hard the car is to wreck — how much punishment it takes before it is wasted.':
    'Qué tan difícil es destrozar el auto: cuánto castigo aguanta antes de quedar destruido.',
  'How well the car keeps going once it is damaged, over a whole race.':
    'Qué tan bien sigue andando el auto una vez dañado, a lo largo de toda una carrera.',
  'A class is a points budget shared between the five stats: Class A cars get 680 to spend, Class C gets 520. That is why raising one stat lowers the others. Pick a higher class for a stronger car, and it will race against tougher opponents.':
    'Una clase es un presupuesto de puntos que comparten las cinco estadísticas: los autos de Clase A tienen 680 para repartir y los de Clase C, 520. Por eso subir una estadística baja las demás. Elige una clase más alta para un auto más fuerte, y correrá contra rivales más duros.',
  'Stretch or shrink the whole car along one axis — 100% leaves it as built. Use this to get the size right without redrawing anything. The game will not race a car that is far too big or too small; the radius beside the preview should sit between 120 and 400.':
    'Estira o encoge todo el auto en un eje; 100% lo deja como está. Úsalo para ajustar el tamaño sin redibujar nada. El juego no deja correr un auto demasiado grande o demasiado pequeño: el radio junto a la vista previa debería quedar entre 120 y 400.',
  'Move or turn the whole car. Rotate if you built it facing the wrong way — it should point away from you, nose at the far end. Nudge to centre it, so it turns about its middle instead of swinging around some point off to one side.':
    'Mueve o gira todo el auto. Rótalo si lo construiste mirando hacia el lado equivocado: debe apuntar lejos de ti, con la nariz al fondo. Desplázalo para centrarlo, así gira sobre su centro en vez de alrededor de un punto a un lado.',
  'The engine your car sounds like, and how its power comes on. Pick the one closest to the kind of car you have built.':
    'El motor al que suena tu auto y cómo entrega la potencia. Elige el más parecido al tipo de auto que construiste.',
  // ---- wheels (rad.js) ----
  'Height': 'Altura', 'Width': 'Ancho', 'Rims Size': 'Tamaño del rin', 'Rims Depth': 'Fondo del rin', 'Hide': 'Ocultar',
  'How far out to the side each wheel sits — half the width between the two. Always a positive number.':
    'Qué tan afuera, hacia el costado, va cada rueda: la mitad del ancho entre las dos. Siempre un número positivo.',
  'How high the wheels sit. Lower numbers raise them into the body, higher numbers drop them below it.':
    'A qué altura van las ruedas. Números más bajos las suben hacia la carrocería; más altos las bajan.',
  'How far forward or back the pair sits. The front pair needs a positive number, the back pair a negative one.':
    'Qué tan adelante o atrás va el par. El par delantero necesita un número positivo; el trasero, uno negativo.',
  'How big the wheels are. Too big and they poke through the bodywork.': 'Qué tan grandes son las ruedas. Si son muy grandes, atraviesan la carrocería.',
  'How fat the tyres are.': 'Qué tan anchas son las llantas.',
  'How big the rim is inside the tyre.': 'Qué tan grande es el rin dentro de la llanta.',
  'How far back the rim sits from the outside face of the tyre.': 'Qué tan hundido está el rin respecto a la cara exterior de la llanta.',
  'How far the wheel tucks up inside the bodywork. Anything past 40 is treated as 40.':
    'Cuánto se mete la rueda dentro de la carrocería. Todo lo que pase de 40 cuenta como 40.',
  // ---- readiness (rad.js problems) ----
  'Pick a first and a second colour on the Body tab.': 'Elige un primer y un segundo color en la pestaña Carrocería.',
  'The car needs four wheels — set the front and back pairs on the Wheels tab.':
    'El auto necesita cuatro ruedas: define los pares delantero y trasero en la pestaña Ruedas.',
  'The wheels are the wrong way round: the front pair needs a positive Z, the back pair a negative one, and both need a positive ±X.':
    'Las ruedas están al revés: el par delantero necesita Z positivo, el trasero Z negativo, y los dos ±X positivo.',
  'There is barely any car here yet — build more of it before racing.': 'Todavía casi no hay auto aquí: construye más antes de correr.',
  'The car has no stats yet — set them on the Stats tab.': 'El auto aún no tiene estadísticas: defínelas en la pestaña Estadísticas.',
  'One of the stats is outside the allowed range of 16 to 200.': 'Una de las estadísticas está fuera del rango permitido de 16 a 200.',
  'The car has no handling set yet — fill in the Physics tab.': 'El auto aún no tiene manejo definido: completa la pestaña Física.',
  'One of the Physics settings is outside the allowed range of 0 to 100.': 'Uno de los ajustes de Física está fuera del rango permitido de 0 a 100.',
  // ---- the Extended tab (careditor/extended.js, web/ext/extlines.js) ----
  'Extended': 'Extended', 'Special': 'Especial', 'By class (automatic)': 'Según la clase (automático)',
  'Stats and physics in Extended': 'Estadísticas y física en Extended', 'Same as NFM 2': 'Igual que en NFM 2',
  'Own': 'Propias', 'own': 'propias', 'invalid': 'inválido', 'invalid — ignored': 'inválido: se ignora',
  'Health and damage in Extended': 'Vida y daño en Extended', 'Health': 'Vida', 'Damage taken': 'Daño recibido',
  'Try in Extended': 'Probar en Extended', 'No description in the game.': 'El juego no lo describe.',
  'What this car is like in NFM 2 Extended. NFM 2 ignores this tab; the same car races in both games.':
    'Cómo es este auto en NFM 2 Extended. NFM 2 ignora esta pestaña: el mismo auto corre en los dos juegos.',
  'Percent of what this car has in NFM 2. The number beside it is what Extended uses.':
    'Porcentaje de lo que este auto tiene en NFM 2. El número de al lado es el que usa Extended.',
  'The special in the file is not a stock car — ignored.': 'El especial del archivo no es un auto del juego: se ignora.',
};

export const CAREDITOR_ES_PATTERNS = [
  [/^(.*) \(shipped\)$/, '$1 (del juego)'],
  [/^(\d)\/4 defined$/, '$1/4 definidas'],
  [/^no such car: (.*)$/, 'no existe el auto: $1'],
  [/^saved (.*)$/, 'guardado: $1'],
  [/^deleted (.*)$/, 'borrado: $1'],
  [/^failed to start: (.*)$/, 'no se pudo iniciar: $1'],
  [/^Delete "(.*)"\? A shipped car of the same name comes back\.$/, '¿Borrar "$1"? Si hay un auto del juego con ese nombre, vuelve a aparecer.'],
  [/^(.*) copy$/, 'copia de $1'],
  [/^Rims ?$/, 'Rines '],
];
