[English](CHANGELOG.md) · **Español**

# Cambios desde el port de radicalarchive

Todo lo que cambió desde el port base de
[radicalarchive/nfm](https://github.com/radicalarchive/nfm) (su último commit es del
2026-08-16), incluido el trabajo hecho antes del primer commit de este repo. Está separado
según a qué parte afecta: el **port base** (NFM 2), el **Stage Maker**, **Extended Mode** y el
**Car Maker**. Lo que vive en archivos compartidos (colores, física, sonido) aparece en el
port base, aunque también aplique a Extended.

---

## Stage Maker (nuevo)

El port original no tenía creador de pistas; este es un port del Stage Maker de Java, con
mejoras que el original no tenía.

- **Editor de pistas en el navegador**: un mapa visto desde arriba, dibujado con el mismo
  motor del juego. Eliges una pieza y haces clic para ponerla: los caminos se unen punta con
  punta, y los checkpoints y las rampas se montan sobre el camino que tienen debajo. En modo
  Seleccionar arrastras piezas para moverlas; <kbd>R</kbd> rota y <kbd>Supr</kbd> borra.
  "Ajustar al camino" (o <kbd>Alt</kbd>) se desactiva para poner una pieza donde hagas clic.
- **Vista 3D**: la pista como la dibuja el juego, con cielo, niebla, montañas, nubes, luces y
  muros, desde cualquier ángulo. **Te puedes mover por ella**: arrastrar gira alrededor de la
  pista, el botón derecho (o <kbd>Shift</kbd>) la desliza, la rueda acerca, y las flechas y
  <kbd>+</kbd> <kbd>-</kbd> también mueven la cámara. Las piezas se ponen, eligen y mueven en
  3D igual que en el mapa.
- **Guarda igual que el original**: el paso que ordena la pista (orden de manejo, puntos que
  siguen los autos de la computadora, muros del borde) es una transcripción del
  `sortstage` de Java, verificada contra el propio Java.
- **Deshacer y rehacer** hasta 100 pasos (<kbd>Ctrl+Z</kbd> / <kbd>Ctrl+Y</kbd>).
- La pista también se edita como texto, y se **importa y exporta como `.txt`**, que abre el
  juego de escritorio.
- **Probar la pista** con un clic (Test drive), sin salir del editor.
- Montañas generadas por semilla, con un botón "Nuevas montañas".
- **Música propia**: además de los `.mod` del original, importa MP3, OGG, M4A o WAV.
- Las pistas se guardan en el navegador, y se pueden correr en NFM 2 y en Extended.
- En español.

---

## Port base (NFM 2)

### Novedades
- **Repetición instantánea**: los últimos 300 ticks de la carrera, con el daño de los autos,
  desde el menú de pausa (con "Grabación de repetición" activada en los ajustes).
- **Highlights de fin de carrera**: acrobacias, destrucciones, derrotas o llegadas ajustadas
  se repiten tres veces, con títulos y cortes de cámara, como en el juego original.
  <kbd>Enter</kbd> o <kbd>Esc</kbd> los saltan.
- **Menú de pausa original** (el `paused.gif` del juego): Continuar, Repetición
  instantánea, Instrucciones y Salir, con teclado o mouse. En multijugador la carrera sigue
  corriendo para los demás, y al salir se les avisa.
- **Controles clásicos de carrera**: <kbd>A</kbd> muestra las flechas hacia los autos y
  <kbd>S</kbd> el radar, como en Java.
- La pantalla de fin de carrera y los selectores de autos y pistas son los del juego
  original, con la entrada de cámara a la pista.
- **Traducción al español**: textos, HUD, cuenta regresiva, selectores, resultados y los
  sprites con letras en inglés, redibujados en español sobre los originales. La ayuda del
  Car Maker también está traducida.
- Las pistas multijugador de NFM 2 (28–32), que antes solo existían en el lobby online, ahora
  están en Free Play, en una pestaña Multijugador.
- El launcher tiene una entrada para Extended Mode y una opción "Mostrar rendimiento"
  (apagado / fps / ms / todo) que sirve para los dos juegos.
- Modo desarrollador en los ajustes: los parámetros de prueba por URL (`?stage=`,
  `?stats=`, ...) solo funcionan si lo activas o si la página la sirve el servidor local.
  Los enlaces de prueba del Car Maker y del Stage Maker funcionan siempre.

### Bugs arreglados
- **Humo de las ruedas**: el polvo que levantan las ruedas estaba portado de otra versión del
  código. Se dibujaba sin descontar la posición de la cámara y opaco, a la mitad del color del
  camino, así que en vez de humo aparecían **manchas negras raras**, sobre todo al empezar
  las pistas nocturnas. Ahora sale donde debe y translúcido, como en Java.
- **Física idéntica a Java**: 13 errores de redondeo entre float y double en la conducción
  (`Mad.drive`) y en el dibujado (`Plane`). Con el mismo azar, Java y el port dan el mismo
  resultado en cada uno de los 300 ticks comparados. Una herramienta de auditoría
  (`web/tools/float-audit.mjs`) revisa el resto del port.
- **Colores de iluminación**: `Color.RGBtoHSB` y `Color.HSBtoRGB` se calculaban en doble
  precisión, y Java los calcula en float. Algunas caras de autos y pistas quedaban un tono
  distinto al del juego original. Ahora el cálculo es idéntico al de Java.
- **Vistas previas de pistas**: la pista 8 y otras grandes no se dibujaban vistas desde
  arriba. Ahora se ven las 32.
- En los selectores de autos y pistas se veía una rejilla negra sobre los caminos, por las
  uniones entre polígonos. Arreglado.
- **Tirones en el selector de pistas**: en las pistas más pesadas (NFM 1: 9 y 10; NFM 2:
  15 y 16) un fotograma atrasado encadenaba varios ticks seguidos y la vista previa se
  congelaba 200–300 ms. Ahora se descarta el atraso, como hace el bucle de Java, y el peor
  caso es de unos 60–85 ms.
- Al volver de una carrera, el selector de autos quedaba sin música.
- <kbd>Enter</kbd> en carrera (la pausa del juego) cambiaba la música de la pista.
- "Continuar" después de una carrera vuelve al menú principal, como en Java.
- El volumen de efectos al 0% o al 25% seguía sonando al 100%: el control de volumen se
  creaba después de haber recibido el ajuste y lo ignoraba.

### Sonido
- **Silbido del motor en ralentí**: los efectos originales están grabados a 8 kHz y cada
  navegador los convertía a su manera. Con una conversión lineal quedaba un silbido agudo
  mientras el auto estaba quieto. Ahora el juego hace esa conversión él mismo, con un filtro
  sinc de alta calidad, y los bucles se convierten de forma que la unión siga siendo suave.
- **Zumbido en Opera GX**: Opera GX producía un zumbido de 375 Hz (48 000 / 128) al repetir
  en bucle los sonidos del motor. Ahora el juego **mezcla todos los efectos él mismo** en un
  AudioWorklet (`web/mixer.js`) y el navegador solo reproduce una señal ya terminada, así que
  suena igual en cualquier navegador. En Chromium se verificó que la salida es idéntica al
  sonido original, muestra por muestra. Donde no hay AudioWorklet, el juego vuelve a
  reproducir los sonidos como antes.

---

## Extended Mode (nuevo)

### El juego
- Extended Mode corre en el navegador. Primero se descompiló su jar y se reparó el resultado
  contra el bytecode (el descompilador se equivocaba en 126 conversiones de tipo). Después
  su código se tradujo a JavaScript y cada
  parte se comparó contra el jar original: los modelos cargan idénticos, el dibujado coincide
  llamada por llamada (~3 millones de llamadas, incluyendo fuego, teletransporte,
  invisibilidad, congelamiento y demás efectos de los 39 autos), y la física y la carga de
  pistas se reproducen exactas sobre trazas capturadas del jar.
- **Free Play** con los 39 autos y un selector de pistas en dos grupos: las 32 pistas de
  NFM 2 y las pistas propias de Extended.
- **Modo Carrera** completo, con el progreso guardado en el navegador: desbloqueo pista por
  pista, puntos de stats con Confirmar / Deshacer, pistas bonus, modo difícil, niveles de
  escala, scouting y cambio de auto.
- Se pueden correr **pistas de NFM 2 y del Stage Maker** con la jugabilidad de Extended. Se
  ven como en el port base: niebla, parches de suelo, sombreado de colinas y parpadeo de los
  checkpoints.
- Extended usa el menú de pausa y la pantalla de fin de carrera del port base, y tiene su
  **repetición instantánea**.
- Música (tracker y los `.ogg` de la carrera), efectos de sonido y chispas al rozar, como en
  el port base.
- **Traducción al español** completa: textos de la carrera, acrobacias, pantallas finales,
  selector de autos y sprites.
- En Free Play se elige la cantidad de autos desde el launcher, incluso **contrarreloj con
  un solo auto**.

### Rendimiento
- **60 fps**: el juego avanza a ~19 ticks por segundo, y ahora Extended dibuja fotogramas
  intermedios entre un tick y el siguiente, como ya lo hacía el port base. En escenas pesadas
  pasó de 26–44 fps a 58–60 fps.
- En la pista 3 de la carrera, la simulación bajó de 8.9 a 3.8 ms por tick y el dibujado de
  15 a 11 ms por fotograma. Los resultados de la simulación no cambiaron.
- **Carga más rápida**: los archivos se descargan en paralelo (de ~2.5 s a ~1 s), los datos
  de los bots se cargan solo para la pista elegida y el juego se precarga mientras estás en
  el menú.

### Bugs arreglados
- **24 de las 27 pistas** de `tracks.radq` usaban una lista de modelos vieja, con cada número
  de pieza corrido en 4; por eso el juego original marcaba ese modo como NO DISPONIBLE.
  Ahora se corrigen al cargar y se pueden correr.
- Las pistas nocturnas (10 y 14) congelaban el juego.
- Las pistas de carrera con música `.ogg` crasheaban la carrera.
- La cámara temblaba en las curvas.
- La pantalla de fin de carrera mostraba "estática de TV".
- Las pistas de NFM 2 perdían el color del suelo (manchas blancas).
- El selector de pistas se congelaba después de correr una pista de NFM 2.
- Las colinas de NFM 2 aparecían de golpe cuando ya estabas cerca.
- El polvo de las ruedas, las chispas y el polvo al derrapar ahora se ven como en el port
  base.

---

## Car Maker → autos nuevos en Extended

- Los autos que haces en el Car Maker del port se pueden correr en Extended, además de los 39
  originales.
- Nueva pestaña **Extended** en el Car Maker: habilidad especial, stats propios, vida y daño,
  más un botón **"Probar en Extended"**.
- El Car Maker ahora edita el **autor** del auto (la línea `carmaker` de NFM 2), y el
  selector de Extended lo acredita.
- En Free Play, tus autos tienen su propia vista, con el mismo interruptor que tenía NFM 2.
- Si un auto no puede cargar, el juego dice por qué, y un auto roto ya no rompe Free Play.
- Arreglado: la pestaña Extended duplicaba el costo de cada slider.
