package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class EstacionesDeTrabajo implements Runnable{

    private final BlockingQueue<ComponentesMaquina> bufferEntrada;
    private final BlockingQueue<ComponentesMaquina> bufferSalida;
    private final Semaphore semaforoEntrada;
    private final Semaphore semaforoSalida;
    private static int turno = 1;
    private static int numEstaciones;
    private int miTurno;

    public EstacionesDeTrabajo(BlockingQueue<ComponentesMaquina> bufferEntrada, BlockingQueue<ComponentesMaquina> bufferSalida, Semaphore semaforoEntrada, Semaphore semaforoSalida, int turno, int numEstaciones) {
        this.bufferEntrada = bufferEntrada;
        this.bufferSalida = bufferSalida;
        this.semaforoEntrada = semaforoEntrada;
        this.semaforoSalida = semaforoSalida;
        this.turno = turno;
        this.numEstaciones = numEstaciones;
    }

    public static synchronized int getTurno() {
        return turno;
    }

    public static synchronized void avanzarTurno() {
        turno = (turno % numEstaciones) + 1;
    }
    @Override
    public void run() {

        while (true) {
            try {
                if (getTurno() == miTurno) {
                    ComponentesMaquina componentesMaquina = producirComponentes();
                    bufferSalida.put(componentesMaquina);

                    avanzarTurno();
                }else {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ComponentesMaquina producirComponentes() {
        return new ComponentesMaquina();
    }
}
