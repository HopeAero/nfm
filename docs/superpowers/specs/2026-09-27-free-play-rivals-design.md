# Free Play: pantalla Rivales — diseño

Fecha: 2026-09-27. Estado: aprobado en chat, pendiente de revisión escrita.

## Objetivo

En Free Play (NFM2 y Extended) no hay forma cómoda de elegir cuántos autos
corren ni contra qué autos. Hoy:

- NFM2: la cantidad vive en `S.players` ("Cars on track"), en la página `opts`
  del launcher, a la que nada enlaza. Inalcanzable desde el juego.
- Extended: fila "Free Play cars" en el menú Extended del launcher, antes de
  entrar; no se ve al elegir pista.
- Rivales: siempre los sortea `sortcars()`. En Extended a menudo salen autos
  "rotos" (`maxmag` de 100000–360000, destruyen de un golpe).

Éxito: desde el propio flujo de Free Play el jugador elige cantidad de autos,
filtra rivales por tier, arma un pool propio ("full Mini", "full MASHEEN") y
puede fijar el auto de rivales concretos. Sin cambiar nada, correr sigue
costando un `Enter`.

Fuera de alcance: autos del Car Maker como rivales; carreras de career;
multijugador.

## Flujo

Solo Free Play, ambos modos:

    Coche → Pista → Rivales → carrera

- `Enter` en Rivales corre con la configuración mostrada (recordada de la vez
  anterior).
- `Esc` vuelve a la pista.
- Career y netplay no pasan por Rivales ni leen su configuración.

## Pantalla Rivales

Un único módulo DOM, `web/rivals.js`, montado sobre el canvas del juego con el
estilo de los controles de Extended (fondo negro, borde `rgb(47,179,255)`,
Arial bold). Textos en inglés en el código, traducidos con `tr()` / `i18n.js`
como el resto.

    RIVALS — <nombre de pista>
    Cars on track:  ◂ 7 ▸            (NFM2 1–8 · Extended 1–19)
    Draw:  (•) Game's pick   ( ) Only my pool
    Tiers: [C] [B] [A] [S]   [All] [None]
    [x] Mini (C)  [x] MASHEEN (A)  [ ] Dr Monstaa (S) ...   agrupados por tier
    Fixed slots:  1 [Random ▾]  2 [Random ▾]  3 [Mini ▾] ...  (cantidad − 1)
    [ ◂ Stage ]                 [ Race ]

- **Game's pick**: el resultado de `sortcars()` tal cual (incluido el auto
  jefe que fuerza la pista). El pool se ignora; los puestos fijos se respetan.
- **Only my pool**: rivales sorteados solo entre los autos marcados.
- **Tier**: atajo, no estado. Clic en `[A]` marca todos los A si alguno falta,
  si no desmarca todos los A. NFM2 no muestra `[S]`.
- **Fixed slots**: un `<select>` por rival (`Random` o un auto). Ganan sobre
  el sorteo en ambos modos.
- Teclado: `◂ ▸` cambia la cantidad, `Enter` corre, `Esc` vuelve. El resto,
  ratón. Los controles no retienen foco (`blur()` tras cada cambio, como los
  selects de pista), para que las teclas sigan llegando a la pantalla.
- Persistencia por modo en `localStorage`, envuelta en try/catch:
  `nfm.rivals.nfm2` / `nfm.rivals.ext` =
  `{ mode: 'game'|'pool', pool: [carIndex...], fixed: [carIndex|null...] }`.
  La cantidad sigue en `S.players` / `S.extplayers` (launcher). La fila "Free
  Play cars" del menú Extended se elimina.
- Pool por defecto (sin nada guardado): todos los autos; modo `game`.

## Tiers

Escala C / B / A / S.

**NFM2** (16 autos): del `cclass` del juego (`web/CarDefine.js:49`).
0–1 → C (6 autos), 2–3 → B (5), 4 → A (5). Sin S.

**Extended** (39 autos): tabla fija `web/ext/tiers.js`, `TIER[carIndex]`, con
el nombre del auto en comentario por línea. Generada una vez por
`web/tools/ext-tiers.mjs` y luego ajustada a mano:

1. Puntaje por auto con sus stats reales (`web/ext/Madness.js`,
   `web/ext/xtGraphics.js`): velocidad máxima (`swits[.][2]`), aceleración
   (`acelf`), aguante (`log(maxmag)`) y daño que hace (`outdam`), cada uno
   normalizado a percentil sobre los 39.
2. **S**: `maxmag` en el grupo alto del salto natural de los datos (~20000 →
   ~100000). El umbral exacto lo fija el script mirando los datos y queda
   escrito en él.
3. El resto se reparte en C/B/A por puntaje, con los cortes elegidos para que
   los autos 23–38 (los 16 de NFM2) caigan en su tier de NFM2 tanto como sea
   posible. Los que no caigan se listan en la salida del script.
4. La tabla se presenta al usuario; sus correcciones se escriben directo en
   `tiers.js` y mandan sobre el script.

## Aplicación a `sc[]`

Función pura en `web/rivals.js`:

    pickRivals({ mode, pool, fixed }, gameSc, n, random) -> sc

- `gameSc`: el `sc[]` que dejó `sortcars()`; `sc[0]` es el jugador y no se toca.
- `n`: autos en pista (jugador incluido); se rellenan `sc[1..n-1]`.
- `mode === 'game'`: se parte de `gameSc`.
- `mode === 'pool'`: cada puesto libre se sortea del pool con `random()`, sin
  repetir mientras queden autos del pool sin usar; agotado, se permite repetir.
- Después, `fixed[k-1]` no nulo pisa `sc[k]`.
- Pool vacío en modo `pool` → se comporta como `game`. Nunca queda un puesto
  sin auto.
- `random` es el `random()` del juego (no `Math.random`), para que replays y
  `selftest` sigan deterministas.

Conexión:

- **NFM2**: el launcher (`startCarSelect`) abre Rivales tras
  `runStageSelect` y pasa la configuración a `startRace` como
  `?rivals=<JSON>`. `web/main.js`, en la rama que hoy llama
  `xt.sortcars(stage)`, aplica `pickRivals` cuando hay `?rivals=`. La rama
  `?cars=same` se mantiene tal cual.
- **Extended**: `runMenus` (`web/ext/menus.js`) abre Rivales tras el `Enter`
  del selector de pista y antes de pasar a fase 5; `Esc` vuelve a la pista.
  `web/ext/race.js` envuelve `xt.sortcars` (como ya envuelve `randomno`),
  solo en Free Play: corre el original y aplica `pickRivals`.
  `freePlayPlayers` pasa de `const` a `let` y la pantalla lo actualiza, para
  que la cantidad elegida valga en esa misma carga de pista.

## Pruebas

- `web/rivals.test.js`: pool de 1 auto (todos iguales), pool vacío (= game),
  puestos fijos sobre ambos modos, más rivales que autos en el pool
  (repite solo al agotarse), `sc[0]` intacto, determinismo con el mismo
  `random`.
- `web/ext/tiers.test.js`: 39 entradas, todas en C/B/A/S; lista de los autos
  23–38 cuyo tier difiere del de NFM2 igual a la aprobada.
- `cd web && node --test` completo en verde.
- Navegador real, ambos modos: "full Mini", "full MASHEEN", puestos fijos,
  1 / 8 / 19 autos; `Enter` directo corre con lo recordado; `Esc` vuelve a la
  pista.
- Deploy (push a `main`) al terminar; anotar en `TASKS.md` y lo aprendido en
  `WORK.md`.
