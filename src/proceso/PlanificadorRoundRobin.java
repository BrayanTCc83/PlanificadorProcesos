package proceso;

import java.util.LinkedList;
import java.util.Queue;

public class PlanificadorRoundRobin {
    private final int quantum;
    private final Memoria memoria;
    private final ListaProcesos lista;
    private int tiempoGlobal = 0;

    public PlanificadorRoundRobin(int quantum, Memoria memoria, ListaProcesos lista) {
        this.quantum = quantum;
        this.memoria = memoria;
        this.lista = lista;
    }

    public void ejecutar() {
        Queue<Proceso> colaEjecucion = new LinkedList<>();

        while (!lista.estaVacia()) {
            Proceso proceso = lista.eliminarProceso();
            colaEjecucion.add(proceso);
        }

        while (!colaEjecucion.isEmpty()) {
            Proceso proceso = colaEjecucion.poll();
            if (memoria.cargarProceso(proceso)) {
                proceso.establecerTiempoPrimeraSubida(tiempoGlobal);

                int tiempoEjecutado = Math.min(quantum, proceso.recuperarTiempoEjecucion() - proceso.recuperarTiempoEjecutado());
                proceso.incrementarTiempoEjecutado(tiempoEjecutado);
                tiempoGlobal += tiempoEjecutado; 

                if (proceso.recuperarTiempoEjecutado() < proceso.recuperarTiempoEjecucion()) {
                    colaEjecucion.add(proceso);
                    System.out.println("Proceso " + proceso.recuperarNombre() + " no ha terminado, vuelve a la cola de listos.");
                } else {
                    proceso.establecerTiempoFinalizacion(tiempoGlobal);
                    memoria.liberarMemoria(proceso);
                    System.out.println("Proceso " + proceso.recuperarNombre() + " ha terminado.");
                }
            } else {
                System.out.println("No se puede cargar el proceso " + proceso.recuperarNombre() + " en memoria.");
            }
        }
    }

    public void mostrarTiemposPromedio() {
    }
}