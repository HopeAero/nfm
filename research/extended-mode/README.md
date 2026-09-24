# Need for Madness 2 — Extended Mode v2.8

## Estado y prioridad

Esta carpeta conserva la investigación de Extended Mode para una ampliación **posterior**. Primero hay que completar y verificar el port del juego original en `web/`. Nada de Extended Mode se carga ni se despliega como parte del juego actual.

Los archivos originales siguen en `D:\platica\Need for Madness 2 - Extended Mode v2.8\`. No se han copiado sus ejecutables, archivos `.radq` ni recursos a este repositorio; esta ficha permite localizarlos y volver a identificarlos. Si se mueve esa carpeta, buscar los archivos por sus hashes SHA-256.

| Archivo original | SHA-256 |
| --- | --- |
| `madness.jar` | `d35de9c3334c70176dd821a96f37cec4be0aa3ce3362209dbb26a539e9d2478d` |
| `data/models.radq` | `4054c6c7d0163b214b5e0a365de2ab88854cf9d9fcb506347c270ba5b63c8992` |
| `data/Files/careertracks.radq` | `83eac09136bd995216594699627b1fa3de4df7259c44f28c8352573a2fd5a054` |

## Lo que se comprobó

- El JAR extendido contiene 154 clases: 27 propias o ajenas a las bibliotecas de audio incluidas y 127 de esas bibliotecas. Comparte 14 nombres de clase con el JAR base; ninguno de los 14 archivos de clase es idéntico. Es una rama modificada del mismo motor, no un paquete de coches y pistas que se pueda incorporar sin cambios.
- En el juego base, la física está en `Mad`; en Extended Mode aparece en `Madness`, con métodos adicionales como `ghostcolide`, `respawn` y `teleport`. `RunApp` asume el arranque. `xtGraphics`, `Control` y `GameSparker` tienen cambios grandes. `ContO`, `Plane`, `Medium`, `Record` y `CheckPoints` también difieren.
- Hay 78 archivos `.radq`: 64 son ZIP normales y 14 usan sustitución de bytes. Los 14 se decodificaron **en memoria** y pasaron la comprobación CRC de todas sus entradas. La transformación intercambia estos pares hexadecimales en todo el archivo: `4B↔55`, `24↔40`, `35↔13`, `15↔2C`, `3B↔48`, `0B↔31`, `0D↔44`. El bytecode de `GameSparker` realiza esos intercambios antes de leer un `ZipInputStream`. No hay que modificar los originales para inspeccionarlos.
- `models.radq` contiene 129 entradas frente a 84 de `data/models.zip` del juego base: 64 nombres coinciden, 65 aparecen solo en Extended Mode y 20 solo en el base. Varios nombres nuevos son variantes `b`; **65 entradas nuevas no equivale a 65 coches jugables nuevos**. Modelos adicionales incluyen `drmonstaa.rad`, `highrider.rad`, `mightyeight.rad`, `railfire.rad`, `secretcar1.rad` y `tornadoshark.rad`. Las directivas nuevas observadas son `firedam`, `glass` y `noOutline`; el port actual ya reconoce `noOutline`.
- Los paquetes de pistas contienen 27 pistas normales (`tracks.radq`), 31 principales de carrera y 4 bonus (`careertracks.radq`, 35 archivos `.txt` en total), 17 clásicas (`classictracks.radq`) y 5 de enfrentamiento (`matchtracks.radq`). Las pistas de carrera usan 38 nombres de directiva ausentes del formato base; entre ellos `setfloat`, `setfire`, `teleset`, `specialchk` y `chkfloat`. Las pistas normales no mostraron nombres de directiva nuevos respecto a las pistas base. También existen archivos de pistas de bots.
- `images.radq` contiene 60 imágenes, 14 con nombres ausentes en `data/images.zip` del juego base, por ejemplo `special.gif`, `stunts.gif`, `track1.jpg`, `track2.jpg` y `fixhoop.gif`. Hay música y sonidos adicionales en `data/Files/`.

## Descompilación (2026-09-23)

El código fuente descompilado de las 26 clases del juego está en [`decompilation/extended/`](../../decompilation/extended/README.md), verificado por bytecode: 97,6 % de coincidencia de opcodes, frente al 98,7 % del juego base con la misma medida. `radq.py`, en esta carpeta, desempaqueta los 78 `.radq` (64 normales, 14 con intercambio de bytes) sin modificar los originales.

## Límite de la comprobación

Con Java 8, `java -jar madness.jar` abrió una ventana que respondía pero permaneció negra durante las pruebas. La consola mostró errores de formato de audio y la ausencia inicial de archivos de guardado. El ejecutable envoltorio de 64 bits salió enseguida. Se detuvieron los procesos y se eliminó el respaldo de guardado que generó la prueba. **La jugabilidad no quedó verificada visualmente**; las conclusiones anteriores proceden del JAR y de los recursos.

## Cuando el port base esté completo

1. Comparar por comportamiento las clases de la versión extendida con las clases base ya portadas, empezando por `Madness`, `Control`, `GameSparker`, `xtGraphics`, `Bots`, `Contva`, `ContO`, `Plane`, `Medium`, `Record` y `CheckPoints`.
2. Adaptar la lectura de recursos `.radq` sin alterar los archivos originales y validar modelos y pistas nuevas con pruebas concretas.
3. Incorporar la lógica y las pantallas adicionales por partes, contrastando cada función con la versión Java cuando sea posible.

La prioridad y las tareas vigentes del port base están en [`TASKS.md`](../../TASKS.md). Los descubrimientos breves también están en [`WORK.md`](../../WORK.md).
