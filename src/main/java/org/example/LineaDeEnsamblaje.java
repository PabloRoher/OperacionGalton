package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class LineaDeEnsamblaje implements Runnable{

    private final BlockingQueue<ComponentesMaquina> bufferSalida;
    private final Semaphore semaforoSalida;

    public LineaDeEnsamblaje(BlockingQueue<ComponentesMaquina> bufferSalida, Semaphore semaforoSalida) {
        this.bufferSalida = bufferSalida;
        this.semaforoSalida = semaforoSalida;
    }

    @Override
    public void run() {
        while (true){
            try {
                semaforoSalida.acquire();
                ComponentesMaquina componentesMaquina = bufferSalida.take();
                System.out.println("Ensamblado: " + componentesMaquina);

            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }

    }

}
