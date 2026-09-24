// Spanish versions of the game's own help texts (CarMaker's usage[]), by the
// same index -- see helptext.js. Index 4 is the author's joke for decompilers;
// the editor never shows it, so it is left in English.

export const USAGE_ES = [
  // 0
  'Freno de mano:\nDefine la potencia del freno de mano del auto.\n' +
  'Cuanto más freno de mano tenga, más rápido frena cuando presionas Espacio mientras manejas.\n' +
  'Pero cuanto menos freno de mano, más puede derrapar el auto al presionar Espacio.\n\n',
  // 1
  'Sensibilidad de giro:\nDefine qué tan rápido gira el auto (o qué tan rápido responden las ruedas al girar).\n' +
  'Cuanto más sensible al giro, más rápido gira y responde el auto.\n\n' +
  'Para un auto rápido pensado para carreras se recomienda una sensibilidad de giro alta,\n' +
  'para que pueda tomar curvas cerradas y rápidas.\n' +
  '¡Pero demasiada sensibilidad de giro puede hacer que el auto sea difícil de manejar!\n\n' +
  'Para un auto más lento y grande (como El King) se recomienda una sensibilidad de giro\n' +
  'más baja, para un efecto más realista.\n\n',
  // 2
  'Agarre de los neumáticos:\nDefine la fuerza con que las ruedas del auto se agarran al suelo.\n\n' +
  'Cuanto más agarre, más se pega el auto a la pista.\n' +
  'Cuanto menos agarre, más derrapa el auto en las curvas.\n\n' +
  'Algo de derrape puede ayudar porque hace que el auto se maneje más suave, mientras que\n' +
  'poco derrape puede hacerlo más nervioso; depende de cómo te guste manejar el auto y de\n' +
  'cómo lo sientas.\n\n',
  // 3
  'Rebote:\nDefine cómo rebota el auto cuando golpea el suelo o un obstáculo.\n\n' +
  'El rebote puede ayudar con las acrobacias: si caes de cabeza y el auto rebota,\n' +
  'puede darse la vuelta antes de volver a caer y evitar un "mal aterrizaje".\n\n' +
  'Pero el rebote no ayuda a controlar el auto ni a correr.\n\n',
  // 4 -- the author's note to decompilers, never shown
  null,
  // 5
  'Levanta a otros:\nDefine si el auto levanta a otros autos cuando choca con ellos de frente, y\n' +
  'qué tan alto puede levantarlos.\n\n' +
  '¿El auto tiene la nariz en punta como MAX Revenge, Radical One o La Vita Crab, una\n' +
  'nariz o frente en punta que pueda meterse bajo las ruedas de otros autos y levantarlos?\n' +
  'Si es así, dale algo de "Levanta a otros".\n\n' +
  'Si el frente es un bloque, como en la mayoría de los autos, dale 0.\n\n',
  // 6
  'Es levantado:\nDefine si el auto puede ser levantado por encima de otros autos cuando choca con\n' +
  'ellos, y qué tan alto.\n\n' +
  '¿El auto está más alto sobre el suelo como Wow Caninaro, o tiene ruedas grandes como\n' +
  'Dr Monstaa? ¿Debería saltar por encima de los autos al chocar con ellos?\n' +
  'Si es así, dale algo de "Es levantado" según qué tan alto deba subir.\n\n' +
  'Si el auto está bajo, como la mayoría, debería tener 0.\n\n',
  // 7
  'Empuja a otros:\nDefine si el auto empuja a otros autos cuando choca con ellos, y qué tan lejos\n' +
  'puede empujarlos.\n\n' +
  '¿Es un auto pesado y de carrocería fuerte como MASHEEN o El King, que al chocar con\n' +
  'otros autos los aparta?\n' +
  '¿O tiene parachoques o piezas especiales para empujar autos, como Sword of Justice?\n' +
  'Si es así, dale algo de "Empuja a otros" según la fuerza con que creas que puede empujar.\n\n' +
  'Si es un auto como cualquier otro, de peso y resistencia normales, dale 0.\n\n',
  // 8
  'Es empujado:\nDefine si el auto sale empujado cuando choca con otros autos, y qué tan lejos.\n\n' +
  'Si el auto es más liviano que la mayoría, debería salir empujado al chocar con otros.\n' +
  'Salir empujado puede ayudar a un auto débil, porque lo aleja más rápido del peligro\n' +
  '(del auto que lo golpeó), así recibe menos golpes y escapa mejor.\n' +
  'Pero salir empujado no ayuda a correr.\n\n',
  // 9
  'Velocidad de giro en el aire:\nAjusta qué tan rápido puede girar y voltearse el auto en el aire al hacer una acrobacia.\n\n' +
  'Este valor también depende de la estadística "Acrobacias" del auto: si es alta, este valor\n' +
  'tendrá un efecto mucho mayor; si es baja, tendrá un efecto menor.\n\n' +
  'Si crees que el auto gira demasiado rápido o demasiado lento en el aire al hacer una\n' +
  'acrobacia, usa este valor para ajustarlo.\n\n' +
  'Si gira demasiado rápido en el aire, puede ser difícil de controlar al voltearse y difícil\n' +
  'de aterrizar derecho.\n\n' +
  'Si es un auto grande y pesado como MASHEEN o El King, debería girar poco en el aire\n' +
  'para un efecto realista.\n\n',
  // 10
  'Control aéreo / planeo:\nAjusta la capacidad del auto de impulsarse en el aire y planear al hacer acrobacias.\n\n' +
  'Por si no lo sabes, en el juego:\n' +
  'Girar hacia atrás impulsa el auto hacia arriba.\n' +
  'Girar hacia adelante impulsa el auto hacia adelante.\n' +
  'Girar a los lados impulsa el auto hacia la izquierda y la derecha.\n\n' +
  'Este valor ajusta la fuerza de ese impulso en el aire.\n\n' +
  'También depende de la estadística "Acrobacias" del auto: si es alta, este valor tendrá un\n' +
  'efecto mucho mayor; si es baja, tendrá un efecto menor.\n\n' +
  'Si el auto tiene alas o aletas, como Radical One o Kool Kat, debería tener más control\n' +
  'aéreo y capacidad de planeo.\n\n',
  // 11
  '¡Prueba de cómo se ve al chocar!\nDefine cómo se verá el auto cuando se dañe.\n' +
  'O sea, cómo se irá viendo el auto a medida que se daña hasta quedar destruido.\n\n' +
  'IMPORTANTE:\n' +
  'Haz una prueba de "Choque normal" junto con una de "Choque de techo" hasta que el auto quede totalmente destruido (destruido y en llamas).\n' +
  'Haz también una prueba de "Choque normal" sola (sin el choque de techo) hasta que el auto quede destruido.\n' +
  'Un "Choque de techo" pasa sobre todo cuando el auto cae de techo desde un salto alto.\n' +
  'Un "Choque normal" es lo que pasa cuando el auto choca con otros autos y obstáculos.\n\n' +
  'Haz clic en los nombres de los valores "Radio", "Magnitud" y "Destrucción del techo" para ver qué hace cada uno.\n\n' +
  '>  Haz la prueba de choque más de una vez para asegurarte de que así quieres que se vea el auto al dañarse\n' +
  'hasta quedar destruido.',
  // 12
  'Radio de choque:\nEl radio alrededor del choque dentro del cual se ven afectadas las piezas/polígonos.\n\n' +
  'O sea, la cantidad de piezas que se afectan en un choque (las que están alrededor del\n' +
  'punto del choque).\n\n' +
  'Aumentar el radio hace que se deformen más piezas alrededor del punto del choque.\n' +
  'Reducirlo hace que se deformen menos.\n\n',
  // 13
  'Magnitud de choque:\nLa magnitud de la deformación y las abolladuras en las piezas/polígonos afectados.\n\n' +
  'O sea, cuánto se destruye cada pieza al chocar.\n\n' +
  'Con más magnitud, la pieza se destruye más con el mismo daño;\n' +
  'con menos magnitud, se destruye menos con ese mismo daño.\n\n',
  // 14
  'Destrucción del techo:\nCuánto se destruye la parte de arriba del auto.\n' +
  'La profundidad de las abolladuras y la destrucción desde arriba.\n\n' +
  'Para ver bien el efecto de este valor, prueba a chocar solo el techo (sin un choque normal),\n' +
  'varias veces, reparando el auto y cambiando el valor para ver la diferencia.\n\n' +
  'En el juego, el choque de techo pasa normalmente cuando el auto cae de cabeza desde\n' +
  'un salto, o cuando un auto grande como Dr Monstaa lo pisa.\n\n',
];
