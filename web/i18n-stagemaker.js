// The stage maker's half of the Spanish dictionary (see i18n.js). Part names
// stay as the game names them. Part descriptions (parts.js DESCRIPTIONS, the
// applet's discp[]) are translated line by line: the first line by part
// number below, the "attaches to" lines by the entries after it; the lines
// that only list part names pass through untouched.

import { DESCRIPTIONS } from './stagemaker/parts.js';

export const STAGEMAKER_ES = {
  // ---- page ----
  'Scenery': 'Escenario', 'Sky': 'Cielo', 'Dust / Fog': 'Polvo / Niebla', 'Ground': 'Suelo',
  'Clouds': 'Nubes', 'Coverage': 'Cobertura', 'Ground Texture': 'Textura del suelo', 'Amount': 'Intensidad',
  'Atmosphere RGB Mask': 'Máscara RGB de la atmósfera', 'Car Lights :': 'Luces del auto :',
  'On': 'Sí', 'Off': 'No', 'Dust/Fog Properties': 'Polvo / Niebla', 'Density': 'Densidad',
  'Near / Far': 'Cerca / Lejos', 'Mountains': 'Montañas', 'New mountains': 'Nuevas montañas',
  'Import song…': 'Importar canción…', 'Delete this song': 'Borrar esta canción', 'My songs': 'Mis canciones',
  'The mountains on the horizon are not drawn by hand: this number is the seed the game builds the whole range from. The same number always gives the same mountains; "New mountains" rolls another range. Turn on the 3D view to see them.':
    'Las montañas del horizonte no se dibujan a mano: este número es la semilla con la que el juego genera toda la cordillera. El mismo número da siempre las mismas montañas; "Nuevas montañas" sortea otra. Activa la vista 3D para verlas.',
  'Import any MP3, OGG, M4A, WAV (or a .mod / .xm tracker module): it is kept in this browser and plays in the race. An imported song only plays here; the desktop game races that stage in silence.':
    'Importa cualquier MP3, OGG, M4A o WAV (o un módulo tracker .mod / .xm): se guarda en este navegador y suena en la carrera. Una canción importada solo suena aquí; el juego de escritorio corre esa pista en silencio.',
  'Sound Track': 'Banda sonora', 'Volume': 'Volumen', 'Listen': 'Escuchar', 'Stop': 'Parar', '(none)': '(ninguna)',
  'Sky, fog, ground, clouds, mountains and the RGB mask, as the original Stage Maker sets them. A dark mask turns the car lights on. The sound track plays in the race; with none the stage races in silence, as in the original.':
    'Cielo, niebla, suelo, nubes, montañas y la máscara RGB, como los ajusta el Stage Maker original. Una máscara oscura enciende las luces de los autos. La banda sonora suena en la carrera; sin ella la pista se corre en silencio, como en el original.',
  'Stage Maker': 'Creador de pistas', 'Parts': 'Piezas', 'Part types': 'Tipos de pieza',
  'Stage': 'Pista', 'Laps': 'Vueltas', 'Hoop height': 'Altura del aro', 'New hoop height': 'Altura de aros nuevos',
  'Selected part': 'Pieza seleccionada', 'Rotation': 'Rotación', 'Height': 'Altura', 'Width': 'Ancho', 'Length': 'Largo',
  'Checkpoint (raised)': 'Punto de control (elevado)', 'Ground Pile': 'Montón de tierra',
  'Height above the ground, at least 500. Cars fly through it to get fixed.':
    'Altura sobre el suelo, mínimo 500. Los autos lo atraviesan volando para repararse.',
  'Height 0 keeps it on the road. Above 0 it becomes the raised checkpoint the original editor makes, floating at that height.':
    'Altura 0 lo deja sobre el camino. Por encima de 0 se vuelve el punto de control elevado del editor original, flotando a esa altura.',
  'Width and length from 2 to 6, as in the original editor.': 'Ancho y largo de 2 a 6, como en el editor original.',
  'The game always stands this part on the ground: the stage file keeps no height for it, so neither the race nor the desktop game could use one.':
    'El juego siempre pone esta pieza sobre el suelo: el archivo de la pista no guarda altura para ella, así que ni la carrera ni el juego de escritorio podrían usarla.',
  'Source (.txt)': 'Código (.txt)', 'Stage source': 'Código de la pista', 'Map': 'Mapa',
  'Stage map': 'Mapa de la pista',
  'parts': 'piezas', 'checkpoints': 'puntos de control', 'fixing hoops': 'aros de reparación',
  'Select': 'Seleccionar', 'Rotate 90°': 'Rotar 90°', 'Delete part': 'Borrar pieza', 'Fit view': 'Ajustar vista',
  'Roads': 'Caminos', 'Ramps': 'Rampas', 'Obstacles': 'Obstáculos', 'Trees': 'Árboles',
  'Checkpoints': 'Puntos de control', 'Start': 'Salida',
  'This is the stage itself, as text: the map edits it for you, and you can type here directly. Saving writes the parts in driving order, marks the points the computer cars drive through and sets the boundary walls, exactly as the original Stage Maker does. Your stages are saved in this browser; Export gives you a .txt you can share or open in the desktop game.':
    'Esta es la pista en sí, como texto: el mapa la edita por ti, y también puedes escribir aquí directamente. Al guardar, las piezas se ordenan en el orden de manejo, se marcan los puntos por los que pasan los autos de la computadora y se ponen los muros del borde, igual que el Stage Maker original. Tus pistas se guardan en este navegador; Exportar te da un .txt que puedes compartir o abrir en el juego de escritorio.',
  'Pick a part, then click the map to place it: roads snap end to end, checkpoints and ramps sit on the road under them. Select mode: click a part to pick it, drag it to move it.':
    'Elige una pieza y haz clic en el mapa para ponerla: los caminos se unen punta con punta, los puntos de control y las rampas se montan sobre el camino que tienen debajo. Modo Seleccionar: haz clic en una pieza para elegirla y arrástrala para moverla.',
  'rotates,': 'rota,', 'deletes,': 'borra,',
  'goes back to Select. Drag with the right button (or drag empty ground) to move the view, scroll to zoom.':
    'vuelve a Seleccionar. Arrastra con el botón derecho (o sobre terreno vacío) para mover la vista; usa la rueda para acercar.',
  '3D view': 'Vista 3D', 'Snap to road': 'Ajustar al camino',
  'Hold Alt to flip it for one placement': 'Mantén Alt para invertirlo al colocar o mover',
  'goes back to Select. Untick Snap to road (or hold': 'vuelve a Seleccionar. Desmarca Ajustar al camino (o mantén',
  ') to put a part exactly where you click. Drag with the right button (or drag empty ground) to move the view, scroll to zoom.':
    ') para poner una pieza exactamente donde hagas clic. Arrastra con el botón derecho (o sobre terreno vacío) para mover la vista; usa la rueda para acercar.',
  'The stage as the game draws it, sky and scenery included, from any angle. Place, pick and move parts here just as on the map. Drag empty ground to turn around the stage; drag with the right button (or':
    'La pista como la dibuja el juego, con cielo y paisaje, desde cualquier ángulo. Pon, elige y mueve piezas aquí igual que en el mapa. Arrastra sobre terreno vacío para girar alrededor de la pista; arrastra con el botón derecho (o',
  ') to slide, scroll to zoom. With ✋ Hand, dragging always moves the view. The arrows and':
    ') para desplazarte, usa la rueda para acercar. Con ✋ Mano, arrastrar siempre mueve la vista. Las flechas y',
  'move it too.': 'también la mueven.',
  'goes back to Select, then to the map.': 'vuelve a Seleccionar y luego al mapa.',
  '✋ Hand': '✋ Mano', '↶ Undo': '↶ Deshacer', '↷ Redo': '↷ Rehacer',
  'Undo (Ctrl+Z)': 'Deshacer (Ctrl+Z)', 'Redo (Ctrl+Y)': 'Rehacer (Ctrl+Y)', 'Drag to move the view': 'Arrastra para mover la vista',
  'goes back to the map.': 'vuelve al mapa.',
  'the game could not load this stage': 'el juego no pudo cargar esta pista',
  // ---- the stage select's choices (carselect.js) ----
  'Custom': 'Personalizadas', 'All': 'Todas', 'No custom stages yet': 'Aún no hay pistas personalizadas',
  'No custom stages yet: make one in the Stage Maker.': 'Aún no hay pistas personalizadas: crea una en el Creador de pistas.',
  'Please Test Drive this stage in the Stage Maker to make sure it can be loaded!':
    '¡Prueba esta pista en el Creador de pistas para asegurarte de que se puede cargar!',
  'Stages': 'Pistas',
  // ---- messages ----
  'The stage needs a start piece, and it has to be the first part.':
    'La pista necesita una pieza de salida, y tiene que ser la primera.',
  'A stage needs at least two checkpoints to race.': 'Una pista necesita al menos dos puntos de control para correrse.',
  'A stage can have at most 5 fixing hoops.': 'Una pista puede tener como máximo 5 aros de reparación.',
  'The start piece cannot be deleted; move it instead.': 'La pieza de salida no se puede borrar; muévela.',
  'Discard your unsaved changes?': '¿Descartar los cambios sin guardar?',
  'Name for the new stage:': 'Nombre de la pista nueva:', 'My Stage': 'Mi pista',
  // ---- part descriptions: the lines after the first ----
  'Attaches correctly to the following other parts :': 'Se une correctamente a estas otras piezas:',
  'Attaches correctly over and to the following other parts :': 'Se monta y se une correctamente a estas otras piezas:',
  'Attaches correctly over following other parts :': 'Se monta correctamente sobre estas otras piezas:',
  'Attaches correctly over the following other parts :': 'Se monta correctamente sobre estas otras piezas:',
  'Attaches correctly over and to following other parts :': 'Se monta y se une correctamente a estas otras piezas:',
  'Mounts correctly over the following other parts :': 'Se monta correctamente sobre estas otras piezas:',
  '(Any stage must have at least two checkpoints to work).': '(Toda pista necesita al menos dos puntos de control para funcionar).',
  "Attaches correctly over only the 'NormalRoad' part.": "Se monta correctamente solo sobre la pieza 'NormalRoad'.",
  'Trees/Cactus are not to be used as obstacles of the race course!': '¡Los árboles y cactus no son obstáculos de la carrera!',
  'They are to be used as out of path ground decoration only.': 'Son solo decoración del terreno fuera del camino.',
  'They are to be used as ground decoration and out of race course obstacles (ground obstacles)!':
    '¡Son decoración del terreno y obstáculos fuera del recorrido (obstáculos de terreno)!',
  'Place it anywhere in the stage at an height your choose, the only important thing is that it needs to be reachable by the cars.':
    'Ponlo en cualquier lugar de la pista a la altura que quieras; lo único importante es que los autos puedan alcanzarlo.',
  'cars jumping the ramp should try to go over it or through it without getting caught crashing (without getting':
    'autos que saltan la rampa deben intentar pasar por encima o a través sin quedar atrapados chocando (sin quedar',
  'caught in it, getting caught in the net!).': 'enredados, ¡atrapados en la red!).',
};

// First line of each description, by part number.
const FIRST_ES = {
  0: 'NormalRoad: camino de asfalto básico.',
  1: 'NormalRoad Edged: camino de asfalto con bloques en los bordes (un camino destructivo).',
  2: 'NormalRoad TwistedRight: camino de asfalto torcido hacia la derecha.',
  3: 'NormalRoad TwistedLeft: camino de asfalto torcido hacia la izquierda.',
  4: 'NormalRoad Turn: curva de asfalto.',
  5: 'OffRoad: camino de tierra arenosa básico.',
  6: 'OffRoad BumpyGreen: camino de tierra con vegetación irregular en el medio.',
  7: 'OffRoad Turn: curva de camino de tierra.',
  8: 'HalfpipeRoad: camino básico para la rampa de medio tubo.',
  9: 'HalfpipeRoad Turn: curva de medio tubo.',
  10: 'Normal-Off-Road Blend: transición entre el camino de asfalto y el de tierra.',
  11: 'Off-Halfpipe-Road Blend: transición entre el camino de tierra y el de medio tubo.',
  12: 'Halfpipe-Normal-Road Blend: transición entre el camino de asfalto y el de medio tubo.',
  13: 'NormalRoad End: el final del camino de asfalto.',
  14: 'OffRoad End: el final del camino de tierra.',
  15: 'HalfpipeRoad-Ramp Filler: pieza que va entre el camino de medio tubo y la rampa de medio tubo para alargar la distancia entre ambos.',
  16: 'Basic Ramp: rampa de asfalto básica de 30 grados.',
  17: 'Crash Ramp: rampa de 35 grados con grandes bloques laterales para chocar.',
  18: 'Two-Way Ramp: rampa de doble sentido inclinada 15 grados.',
  19: 'Two-Way High-Low Ramp: rampa de doble sentido de 15 grados, con un lado elevado para un salto más alto opcional.',
  20: 'Landing Ramp: rampa que es a la vez pendiente de aterrizaje y obstáculo; suele ir justo después de otra rampa.',
  21: 'Big-Takeoff Ramp: gran rampa de despegue para alcanzar alturas enormes con los autos.',
  22: 'Small Ramp: rampa pequeña que puede ir a cualquier lado del camino.',
  23: 'Offroad Bump Ramp: pequeña rampa-lomo para poner sobre los caminos de tierra.',
  24: 'Offroad Big Ramp: ¡la gran rampa de tierra como una montaña!',
  25: 'Offroad Ramp: ¡rampa de tierra de tamaño normal!',
  26: 'Halfpipe: la rampa de medio tubo; ¡dos enfrentadas forman un medio tubo para los autos!',
  27: 'Spiky Pillars: obstáculo que suele ir después de una rampa, ¡para que choquen los autos que no saltaron lo bastante alto o lejos!',
  28: 'Rail Doorway: portal de rieles que es obstáculo para los autos que vuelan por encima o pasan por debajo.',
  29: 'El muro',
  30: 'Checkpoint: el punto de control, que decide en última instancia cómo se corre tu pista; colócalo con cuidado.',
  31: 'Fixing Hoop: ¡el aro que repara un auto cuando vuela a través de él! Puedes poner hasta 5 por pista.',
  33: 'OffRoad BumpySides: camino de tierra con bancos de arena irregulares a los lados.',
  34: 'OffRoad-BumpySides Start: el comienzo del camino de tierra con bancos de arena a los lados.',
  35: 'NormalRoad-Raised Ramp: el comienzo del camino elevado sobre el suelo (NormalRoad Raised).',
  36: 'NormalRoad Raised: camino elevado sobre el suelo; los autos deben evitar caerse al manejar sobre él.',
  39: 'Tunnel Side Ramp: rampa para hacer un camino tipo túnel abierto arriba, ¡o como rampa de pared!',
  40: 'Launch Pad Ramp: rampa que lanza tu auto hacia arriba como un cohete, ¡con lados que atrapan al auto que la sube!',
  41: 'The Net: obstáculo para el centro del camino justo después de una rampa; la idea es que los',
  42: 'Speed Ramp: rampa con el ángulo perfecto para lanzar tu auto lo más lejos posible haciendo vueltas hacia adelante; mide la mitad del ancho del camino.',
  43: 'Offroad Hill Ramp: colina de tierra con dos inclinaciones distintas, por delante y por detrás, para saltar.',
  44: 'Bump Slide: pequeño obstáculo-lomo para los lados o el centro del camino.',
  45: 'Offroad Big Hill Ramp: gran colina de tierra con dos inclinaciones distintas, por delante y por detrás, para saltar.',
  46: 'Rollercoaster Start/End: la rampa que empieza y termina el camino de montaña rusa.',
  52: 'Offroad Dirt-Pile: montón de tierra como obstáculo, para poner en cualquier parte del centro del camino.',
  55: '¡Los árboles y cactus son decoración: van en el suelo fuera de la pista y NUNCA sobre un camino o una rampa!',
  66: '¡Los montones de tierra van en el suelo fuera de la pista y NUNCA sobre un camino o una rampa!',
};
for (const [sp, es] of Object.entries(FIRST_ES)) {
  STAGEMAKER_ES[DESCRIPTIONS[sp].split('\n')[0].replace(/\s+/g, ' ').trim()] = es;
}

export const STAGEMAKER_ES_PATTERNS = [
  [/^no such stage: (.*)$/, 'no existe la pista: $1'],
  [/^Stage (\d+)$/, 'Pista $1'],
  [/^Delete "(.*)"\?$/, '¿Borrar "$1"?'],
  [/^Attaches correctly to only (.*?),? and itself\.$/, 'Se une correctamente solo a $1 y a sí misma.'],
];
