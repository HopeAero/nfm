# Need for Madness en el navegador — NFM 2 y Extended Mode

![icon](data/icon.png)

Need for Madness 2 y **Need for Madness 2 Extended Mode (v2.8)** corriendo directo en el
navegador, en JavaScript + WebGL, sin Java ni plugins. Los dos juegos salen del mismo
launcher, comparten el motor de dibujo, el sonido, el Car Maker y el Stage Maker, y un auto
hecho en el Car Maker se puede correr en los dos.

Qué cambió desde el punto de partida: [`CHANGELOG.md`](CHANGELOG.md).

## De dónde parte

Este proyecto parte del port de NFM a la web de
**[radicalarchive/nfm](https://github.com/radicalarchive/nfm)**
([jugar el original](https://radicalarchive.github.io/nfm)). Todo el crédito del port base es
suyo: los JAR originales se descompilaron y el Java se transcribió línea por línea a JS,
con WebGL para dibujar. Ese port ya traía:

- NFM 2 jugable, con su física, HUD, sonido y menús
- multijugador online privado
- repetición instantánea (los últimos 300 ticks)
- los controles clásicos de carrera (A: flechas de autos, S: radar/mapa, Esc: pausa)
- el Car Maker y el Stage Maker
- mejoras de resolución y fps sobre los 800x450 / 19 fps fijos del original
- una versión parcheada del juego de Java para correr en Java moderno (`./start.sh`)
- el código de Extended Mode descompilado y validado contra su jar, como referencia para un
  port futuro (`decompilation/extended/`)

Sobre esa base, este fork:

- **porta Extended Mode al navegador**: Free Play, Modo Carrera con progreso guardado, sus
  39 autos, sus pistas y también las de NFM 2 y el Stage Maker, a 60 fps y en español;
- **agrega autos nuevos del Car Maker a Extended**, con una pestaña propia en el Car Maker;
- **arregla y mejora el port base**: colores idénticos a Java, sonido sin silbidos ni
  zumbidos en ningún navegador, pistas multijugador de NFM 2 en Free Play, entre otras cosas.

El detalle, separado entre port base, Extended y Car Maker, está en
[`CHANGELOG.md`](CHANGELOG.md).

## Cómo se hizo el port de Extended

Igual que el port base: el Java descompilado de Extended se traduce a JS siguiendo un
contrato estricto (`web/TRANSPILE_SPEC.md`: enteros de 32 bits, redondeo a float32,
asignaciones compuestas), y cada parte se compara contra el jar original ejecutándolo en
paralelo (`web/tools/`). Los modelos cargan idénticos, el dibujado coincide llamada por
llamada, y la física y la carga de pistas se reproducen exactas sobre trazas capturadas del
jar.

## Correrlo

```sh
python3 web/tools/serve.py 8123     # desde la raíz del repo
# y abrir http://localhost:8123/
```

Tests: `cd web && node --test`. Cómo medir, verificar y desplegar: `AGENTS.md`.

## Estructura

- **`index.html`** — el launcher (elegir juego, auto y pista; ajustes).
- **`web/`** — el port. `main.html` es el juego; `web/ext/` es Extended Mode.
- **`ext/`** — los datos de Extended (sus `.radq`, fuentes y sonidos), sin modificar.
- **`java/`** — el juego original parcheado (`Game.jar`, lo corre `start.sh`) y el jar
  intacto (`Game.jar.bak`). Es la referencia contra la que se compara el port, no se compila.
- **`decompilation/`** — el Java descompilado de NFM 2 y de Extended, y el plan del port.
  Solo lectura.
- **`data/`, `stages/`, `mycars/`, `mystages/`, `music/`** — los archivos del juego,
  idénticos byte a byte al original y **no se modifican**.

## Documentos

- `CHANGELOG.md` — todo lo que cambió desde el port de radicalarchive
- `AGENTS.md` — cómo correr, desplegar, medir y verificar; las invariantes
- `WORK.md` — descubrimientos y trampas, lo más nuevo al final
- `TASKS.md` — qué está hecho, qué sigue, qué está bloqueado
- `web/TRANSPILE_SPEC.md` — el contrato Java → JS
- `decompilation/PORT_SPEC.md` — el plan original y las reglas para delegar trabajo
