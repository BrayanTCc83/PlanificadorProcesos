package proceso;

import estructura.ColaSimple;

public class PlanificadorRoundRobin {
    private final int quantum;
    private final Memoria memoria;
    private int tiempoGlobal = 0;

    private ColaSimple<Proceso> colaEjecucion;

    public PlanificadorRoundRobin(int quantum, Memoria memoria, ColaSimple<Proceso> colaProcesos) {
        this.quantum = quantum;
        this.memoria = memoria;
        this.colaEjecucion = colaProcesos;
    }

    public void ejecutar() {
        while (!colaEjecucion.estaVacia()) {
            Proceso proceso = colaEjecucion.eliminar();

            if (memoria.cargarProceso(proceso)) {
                if (proceso.recuperarTiempoPrimeraSubida() == Proceso.NO_TIEMPO) {
                    proceso.establecerTiempoPrimeraSubida(tiempoGlobal);

                    System.out.println("Tiempo de respuesta para " + proceso.recuperarNombre() + ": " + 
                    (tiempoGlobal - proceso.recuperarTiempoLlegada()));
                }

                int tiempoEjecutado = Math.min(quantum, proceso.recuperarTiempoEjecucion() - proceso.recuperarTiempoEjecutado());
                proceso.incrementarTiempoEjecutado(tiempoEjecutado);
                tiempoGlobal += tiempoEjecutado; 

                if (proceso.recuperarTiempoEjecutado() < proceso.recuperarTiempoEjecucion()) {

                    colaEjecucion.insertar(proceso);
                    System.out.println("Proceso " + proceso.recuperarNombre() + " sin terminar, vuelve a la cola");
                } else {

                    proceso.establecerTiempoFinalizacion(tiempoGlobal);
                    memoria.liberarMemoria(proceso);
                    System.out.println("Proceso " + proceso.recuperarNombre() + " ha terminado.");
                }
            } else {

                System.out.println("No se pudo cargar el proceso " + proceso.recuperarNombre());
            }
        }
    }

    public void mostrarTiemposPromedio() {
    }
}