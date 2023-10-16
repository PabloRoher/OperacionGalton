package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.lang.Thread;

public class Simulacion {

    int capacidad = 100;

    int numEstaciones = 2;
    BlockingQueue<ComponentesMaquina> bufferEntrada = new ArrayBlockingQueue<>(capacidad);
    BlockingQueue<ComponentesMaquina> bufferSalida = new ArrayBlockingQueue<>(capacidad);
    Semaphore semaforoEntrada = new Semaphore(capacidad);
    Semaphore semaforoSalida = new Semaphore(0);

    public void iniciarSimulacion(){
        Thread estacion1 = new Thread(new EstacionesDeTrabajo(bufferEntrada, bufferSalida, semaforoEntrada, semaforoSalida, 1, numEstaciones));
        Thread estacion2 = new Thread(new EstacionesDeTrabajo(bufferEntrada, bufferSalida, semaforoEntrada, semaforoSalida, 2, numEstaciones));

        estacion1.start();
        estacion2.start();

        Thread lineaEnsamblaje = new Thread(new LineaDeEnsamblaje(bufferSalida, semaforoSalida));

        lineaEnsamblaje.start();
    }


}
