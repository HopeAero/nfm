# Cambios desde el port de radicalarchive

Todo lo que cambió desde el port base de
[radicalarchive/nfm](https://github.com/radicalarchive/nfm) (importado el 2026-09-23). Está
separado según a qué juego afecta: el **port base** (NFM 2), **Extended Mode** y el
**Car Maker**. Lo que vive en archivos compartidos (dibujo de colores, sonido) aparece en el
port base, aunque también aplique a Extended.

---

## Port base (NFM 2)

### Novedades
- Las pistas multijugador de NFM 2 (28–32), que antes solo existían en el lobby online, ahora
  están en Free Play, en una pestaña Multijugador.
- El launcher tiene una entrada para Extended Mode y una opción "Mostrar rendimiento"
  (apagado / fps / ms / todo) que sirve para los dos juegos.
- Modo desarrollador en los ajustes: los parámetros de prueba por URL (`?stage=`,
  `?stats=`, ...) solo funcionan si lo activas o si la página la sirve el servidor local.
  Los enlaces de prueba del Car Maker y del Stage Maker funcionan siempre.

### Bugs arreglados
- **Colores de iluminación**: `Color.RGBtoHSB` y `Color.HSBtoRGB` se calculaban en doble
  precisión, y Java los calcula en float. Algunas caras de autos y pistas quedaban un tono
  distinto al del juego original. Ahora el cálculo es idéntico al de Java.
- **Tirones en el selector de pistas**: en las pistas más pesadas (NFM 1: 9 y 10; NFM 2:
  15 y 16) un fotograma atrasado encadenaba varios ticks seguidos y la vista previa se
  congelaba 200–300 ms. Ahora se descarta el atraso, como hace el bucle de Java, y el peor
  caso es de unos 60–85 ms.
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
- Extended Mode corre en el navegador. Su código original se tradujo a JavaScript y cada
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
