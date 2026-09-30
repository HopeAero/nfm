# Toyota Corolla 2011 para Need for Madness

Primera versión blanca, modelada directamente con coordenadas y polígonos `.rad`, sin Blender. La foto aportada guía el frente y el perfil; la parte trasera es aproximada. No pretende ser una réplica de ingeniería del vehículo.

## Usarlo

1. Abre el Car Maker del juego.
2. Pulsa **Import…** y selecciona `Toyota Corolla 2011.rad`.
3. Guarda el auto en tu navegador y selecciónalo en el lanzador.

Vista pública: https://hopeaero.github.io/nfm/web/corolla-preview.html

## Qué incluye

- 134 polígonos de carrocería y cuatro ruedas nativas: 210 piezas con ruedas.
- Silueta de sedán, pilares, ventanas, parrillas, faros delanteros y luces traseras.
- Clase B, presupuesto de 600 puntos; comportamiento pensado para NFM.
- Calibración de deformación mediante el código del Car Maker.

Se omiten interiores, espejos y detalles diminutos para conservar el estilo de la guía. Las ruedas son las del juego; no reproducen exactamente los rines de la fotografía.

## Verificación y edición

`build.mjs` genera la geometría y calibra el daño. Esa calibración usa azar, por lo que regenerar puede variar ligeramente la resistencia. El `.rad` entregado ya está calibrado. `manifest.json` registra sus valores y referencias.

`verify.mjs` carga este archivo con CarDefine y corre 80 ticks de la pista 1 con aceleración y giro, comprobando movimiento, valores finitos y geometría emitida. `preview.png` fue capturada del motor NFM real en Chrome.

Desde la raíz del repositorio: `node output/corolla-2011/verify.mjs`. No requiere servidor.

Guía aportada: https://docs.google.com/presentation/d/1MiJ1Lbp8c2HN3KRx5jy5fPyffyOdCrXLT5VjUF0FyKo/edit
