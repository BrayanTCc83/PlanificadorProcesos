package algoritmo;

import estructura.ColaSimple;
import memoria.GestorMemoria;
import patrones.observer.Observador;
import proceso.GestorProcesos;
import proceso.Proceso;

public class PlanificadorRoundRobin implements Observador<Proceso> {
    private final int quantum;
    private final GestorMemoria memoria;
    private final ColaSimple<Proceso> colaListos;
    private int tiempoGlobal = 0;
    private boolean estaPlanificando = false;
    private boolean estaProcesando = false;
    private boolean estaInicializado = false;

    public PlanificadorRoundRobin(int quantum) {
        this.memoria = GestorMemoria.recuperarGestorMemoria();
        this.memoria.agregarObservador(this);
        this.colaListos = new ColaSimple<>();
        this.quantum = quantum;
    }

    public void procesamientoCPU(Proceso proceso) {
        this.estaProcesando = true;
        proceso.establecerTiempoSubida(tiempoGlobal);
        System.out.println("\nProcesando proceso " + proceso.recuperarNombre() + ", subio al procesador en el tiempo " + tiempoGlobal + " [ms].");

        for(int i = 0; i < quantum; i++) {
            if(proceso.estaFinalizado())
                break;

            tiempoGlobal += 1;
            proceso.incrementarTiempoEjecutado(1);

            System.out.println("El tiempo del sistema es " + tiempoGlobal + " [ms].");
            GestorProcesos.recuperarGestorProcesos().iniciarSimulacion();
        }

        if (proceso.recuperarTiempoEjecutado() < proceso.recuperarTiempoEjecucion()) {
            System.out.println("\nProceso " + proceso.recuperarNombre() + " sin terminar, vuelve a la cola de listos.");
            colaListos.insertar(proceso);
        } else {
            proceso.establecerTiempoFinalizacion(tiempoGlobal);
            System.out.println("\nProceso " + proceso.recuperarNombre() + " ha terminado.");
            GestorProcesos.recuperarGestorProcesos().agregarAProcesosFinalizados(proceso);
        }
        this.estaProcesando = false;

        if(memoria.tieneProcesos())
            planificarCortoPlazo();
        if(!colaListos.estaVacia())
            planificarMedioPlazo();
    }

    public void planificarCortoPlazo() {
        if(!memoria.tieneProcesos() || this.estaProcesando)
            return;
        
        Proceso proceso = memoria.extraerProceso();
        procesamientoCPU(proceso);
    }

    public void planificarMedioPlazo() {
        this.estaPlanificando = true;
        while (!colaListos.estaVacia()) {
            Proceso proceso = colaListos.eliminar();
            if(!memoria.insertarProceso(proceso)) {
                System.out.println("La memoria esta llena, encolando proceso " + proceso.recuperarNombre() + " en listos nuevamente.");
                colaListos.insertar(proceso);
            }
        }
        this.estaPlanificando = false;
        planificarCortoPlazo();
    }

    public int obtenerTiempoGlobal() {
        return this.tiempoGlobal;
    }

    public void actualizarTiempoGlobal(int tiempoGlobal) {
        if(this.estaInicializado)
            return;

        for(int i = this.tiempoGlobal; i <= tiempoGlobal; i++)
            System.out.println("El tiempo del sistema es " + i + " [ms].");
        this.tiempoGlobal = tiempoGlobal;
        this.estaInicializado = true;
    }

    @Override
    public void notificar() {
        if(!this.estaPlanificando)
            planificarMedioPlazo();
    }

    @Override
    public void notificar(Proceso proceso) {
        System.out.println("\nHa llegado el proceso " + proceso.recuperarNombre() + ", se encolara en la cola de listos.");
        colaListos.insertar(proceso);
    }
}