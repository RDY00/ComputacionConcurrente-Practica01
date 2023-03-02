## Práctica 1

INTEGRANTES:
- Nícolas Kano Chavira    315319204
- Fernando Márquez Pérez  420004482

NOTAS:
Para la ley de Amdahl (La aceleración teórica) utilizamos la función:
A = 1 / ((1-Fm) + Fm/Am)

En donde Fm es la fracción del tiempo que se usa la parte del sistema mejorada, que para ambos programas la tomamos como el 1 (o el 100%) porque todo el programa es concurrente, y Am es el factor de mejorar del sistema, que tomamos como la cantidad de hilos.

Para la aceleración práctica, utilizamos A = tiempoConcurrente / tiempoSecuencial. Para los tiempos, ejecutamos 20 veces las pruebas y promediamos los valores obtenidos.

La imagen de la tabla se anexa en TablaEjecuciones.png

