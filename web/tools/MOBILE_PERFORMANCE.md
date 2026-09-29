# Estudio de rendimiento móvil — 29 de septiembre de 2026

El usuario observa caídas al iniciar carreras y girar la cámara en un Samsung A54. Este estudio identifica trabajo que conviene reducir; todavía no mide ese teléfono ni demuestra una mejora de FPS en él. La corrección publicada junto al estudio permite tocar o clicar las pantallas de resultado para continuar, usando la misma acción que Enter.

## Método y límites

Chrome con ventana real y GPU AMD Radeon RX 5500, CPU ralentizada 4× mediante DevTools. No se usó tiempo virtual ni renderizado headless para medir tiempos. Pista 9, ocho coches, sin música, antialiasing ni ghost; interpolación activada. Ventana de cinco segundos tras 2,5 segundos de calentamiento. La carrera activa se inicia omitiendo la introducción; el jugador queda sin acelerar. Cada variante vuelve a cargar la pista.

La ralentización es una prueba de estrés de CPU, no una emulación del A54. No reproduce su GPU, temperatura, memoria ni navegador. Las carreras tienen movimiento y azar: cambiar cámara o resolución puede cambiar la geometría visible y los tiempos de las colisiones. Por eso esta serie exploratoria no permite adjudicar porcentajes de mejora a una opción; una optimización necesitaría varios pares con estados y escenas reproducibles. Chrome explica las [limitaciones de Device Mode](https://developer.chrome.com/docs/devtools/device-mode) y la [calibración de ralentización de CPU](https://developer.chrome.com/docs/devtools/settings/throttling).

Reproducción desde la raíz, con `python web/tools/serve.py 8123` ejecutándose: `node web/tools/browser-mobile-perf.mjs`. El script requiere Chrome en la ruta Windows indicada. Evitar otros benchmarks en paralelo. Se desactiva la ralentización de ventanas ocultas para que cubrir la ventana no invalide la medición.

Datos completos: [serie de mediciones](perf-results/mobile-2026-09-29.json) y [muestras de CPU](perf-results/mobile-cpu-2026-09-29.json). Es la última serie aislada; sustituye las mediciones exploratorias anteriores.

## Resultados exploratorios

| Escena | Resolución | FPS medios | Dibujar ms/cuadro | Simular ms/tick | Vértices enviados / emitidos | ns/vértice enviado | Peor intervalo |
|---|---:|---:|---:|---:|---:|---:|---:|
| Introducción | 1600×900 | 14,7 | 61,23 | 0,53 | 21.838 / 73.257 | 2.804 | 150 ms |
| Introducción | 800×450 | 21,1 | 43,20 | 0,27 | 21.126 / 73.668 | 2.045 | 83 ms |
| Introducción, límite 30 FPS | 800×450 | 16,5 | 53,92 | 0,39 | 21.569 / 73.182 | 2.500 | 117 ms |
| Carrera, cámara normal | 1600×900 | 29,4 | 24,00 | 9,20 | 9.290 / 36.156 | 2.584 | 100 ms |
| Carrera, cámara orbital | 1600×900 | 40,2 | 17,64 | 7,12 | 4.768 / 25.044 | 3.700 | 150 ms |
| Carrera, cámara normal | 800×450 | 21,6 | 28,47 | 10,93 | 9.698 / 41.541 | 2.936 | 283 ms |

La introducción muestra aproximadamente 73.000 vértices emitidos por cuadro, frente a 25.000–42.000 en estas escenas de carrera. La vista amplia exige bastante más geometría. El dibujo ocupa la mayor parte del presupuesto de CPU; la simulación sí se vuelve más costosa cuando los coches corren. No se deben sumar ms/tick y ms/cuadro directamente: la simulación avanza unas 19 veces por segundo y el dibujo tiene otra frecuencia.

Bajar resolución ayuda en una variante de introducción, pero la carrera a resolución menor no mejora en esta serie. No hay una ganancia universal demostrada. La cámara orbital dibuja una escena distinta: su mayor FPS medio tampoco prueba que girar sea más barato; su peor intervalo sigue siendo alto. El límite de 30 FPS no recupera ese objetivo cuando el dibujo tarda más de 33 ms.

El perfil de cuatro segundos de la última escena sitúa `Plane.d` primero (527 muestras), seguido de trabajo como `_fillTrapezoid` (190), grabación `Record.rec` (174), creación de arrays (135) y simulación (134). Las muestras son atribución de CPU, no un porcentaje de mejora recuperable. El rellenado de polígonos cóncavos y el trabajo por cara merecen atención.

## Referencias compartidas

- **nfm-origins:** `graphics.cpp` mantiene buffers y su `shaders/poly.vs` transforma vértices con matrices en GPU. Es una arquitectura distinta, útil como referencia para una reescritura.
- **NFM World:** los shaders `data/shaders/Poly.fx` y `Ground.fx` contienen transformaciones en GPU e instancias. La carpeta contiene ejecutables y ensamblados; no se midió ese motor ni se dispone allí de todo su código fuente.
- **nfm-lit-master:** `src/nfm/lit/ContO.java` sigue dibujando con Java Graphics2D y ordenando caras con comparaciones cuadráticas. No ofrece una solución GPU para copiar. Sus lanzadores `madness.ini` y `madness64.ini` apuntan a `madness.jar`, ausente en la raíz: es un problema concreto que impide arrancar esa distribución. No se reconstruyó ese proyecto durante este estudio.

## Qué conviene probar después

1. **Reducir detalle visual y distancia en dispositivos limitados**, sobre todo en la introducción y el fondo. El beneficio potencial procede de llamar menos veces a `Plane.d`. Debe implementarse como una preferencia gráfica separada, conservando colisiones, efectos por tick y orden de dibujo, y compararse con escenas idénticas. Una vista de inicio menos amplia también es una candidata a medir.
2. **Perfilar los cuadros que se atascan en el A54**, con navegador y pista concretos. El perfil global no identifica por sí solo la causa de cada pico. Usar las herramientas de picos existentes antes de seleccionar otra optimización; incluir audio y ghost en la comprobación final.
3. **Evaluar el rellenado cóncavo y el trabajo por cara con pruebas de equivalencia**, manteniendo la regla even-odd. No cambiar la triangulación sólo porque una captura parezca correcta.
4. **Usar resolución 1× como opción existente**, especialmente si el dispositivo está limitado por GPU o ancho de banda. MDN recomienda considerar un [buffer de dibujo menor](https://developer.mozilla.org/en-US/docs/Web/API/WebGL_API/WebGL_best_practices). Medir en el teléfono antes de elegirlo automáticamente.

Trasladar la proyección a shaders no es un cambio pequeño: `Plane.d` necesita las coordenadas transformadas en CPU para culling, sombreado, distancia, orientación entre caras y relleno even-odd. Conservar sólo el orden de envío no basta. Los motores de referencia muestran una vía para una reescritura amplia, no una optimización que podamos aplicar directamente ni una promesa de 60 FPS.

Tampoco conviene reintroducir un pool genérico de arrays: `WORK.md` documenta dos pruebas anteriores donde empeoró el rendimiento. La grabación y las asignaciones son candidatas a investigación, no mejoras demostradas por aparecer en el perfil.

**No usar `Medium.resdown=2` como ajuste sólo gráfico:** `Mad.drive` lo consulta al aceptar trackers decorativos y puede cambiar colisiones. El renderer no tiene buffer de profundidad; cualquier cambio debe conservar el orden de envío. Este estudio no modifica física, geometría ni calidad automáticamente.

## Implementación posterior: ajustes de dibujo

Tras la comprobación del usuario, Chrome en su Samsung A54 mantiene la carrera fluida; las caídas repetidas se observaron en Kiwi. La caída inicial también aparece en Chrome. Esta observación centra la intervención en la introducción y permite dejar el detalle de carrera completo por defecto.

En Ajustes se añaden **Introducción ligera** (Sí por defecto), **Detalle del fondo** (Completo/Reducido) y **Mostrar montañas** (Sí/No). Los cambios se guardan y se aplican al iniciar otra carrera, en Classic y Extended.

La introducción conserva el recorrido y la duración originales; omite montañas, nubes y polígonos decorativos del suelo, y limita el dibujo de objetos del escenario a 4.000 unidades de profundidad de cámara más el radio del objeto. Cuando la cuenta atrás llega a 37 se recuperan las preferencias normales. El fondo reducido mantiene ese recorte en 8.000 unidades durante la carrera y omite nubes y detalle del suelo; la opción de montañas sigue siendo independiente. La pista y los obstáculos cercanos siguen dibujándose. Hay menos escenario lejano visible y puede aparecer al acercarse; ése es el intercambio visual del ajuste.

Los coches siempre quedan exentos del nuevo límite. `Mad`/`Madness` leen `ContO.dist === 0` para estado de daños y reparaciones, así que recortar coches no sería una modificación sólo visual. `resdown`, `fade`, los trackers, modelos originales, cámara, cuenta atrás y orden relativo de envío se conservan.

Se descartó una cámara de inicio más cercana: en una comparación del mismo estado aumentó los vértices emitidos de 44.184 a 67.542. Los polígonos mayores también pueden aumentar el relleno; acercar la cámara no prueba una reducción de trabajo.

Prueba reproducible: `node web/tools/browser-render-detail.mjs`, con el servidor local. Mantiene los mismos coches/objetos cargados, sin simular entre las variantes, calienta cuatro dibujos y mide veinte. Los tiempos son una microprueba estática de dibujo y envío a WebGL en Chrome de escritorio; **no son FPS de carrera ni una predicción para el A54**. Datos en [render-detail-2026-09-29.json](perf-results/render-detail-2026-09-29.json).

| Motor | Introducción original → ligera, caras | Vértices emitidos | Tiempo de dibujo estático |
|---|---:|---:|---:|
| Classic | 3.982 → 2.089 | 44.184 → 35.766 | 8,98 → 5,48 ms |
| Extended | 2.500 → 1.472 | 33.387 → 29.625 | 8,60 → 4,84 ms |

En la vista de carrera de esta prueba, fondo reducido sin montañas pasa de 4.087 a 2.218 caras en Classic y de 2.488 a 1.582 en Extended. Ocultar sólo montañas elimina 354/252 vértices emitidos respectivamente: aquí no son el mayor coste. No debe prometerse una ganancia grande por esa opción sola.

Verificaciones de navegador: menos geometría en ambos motores, montañas apagadas/restauradas, mismos campos de posición/rotación de los objetos y misma visibilidad de los coches al cambiar detalle; también guardado de los tres ajustes mediante sus controles reales y traducción española. Capturas revisadas de la introducción, fondo reducido y Ajustes. Falta confirmar la experiencia final en el Samsung A54 del usuario.
