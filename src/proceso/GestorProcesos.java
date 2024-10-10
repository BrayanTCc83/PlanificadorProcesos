package proceso;

import algoritmo.PlanificadorRoundRobin;
import estructura.ColaOrdenada;
import patrones.observer.Observable;
import patrones.observer.Observador;

public class GestorProcesos implements Observable<Proceso> {
    private static GestorProcesos gestorProcesos = null;

    private ColaOrdenada colaProcesosParaGenerar;
    private Observador<Proceso> observador = null;

    public static GestorProcesos recuperarGestorProcesos() {
        if(gestorProcesos == null)
            gestorProcesos = new GestorProcesos();
        return GestorProcesos.gestorProcesos;
    }
    
    private GestorProcesos() {
        colaProcesosParaGenerar = new ColaOrdenada();
    }

    public void iniciarSimulacion() {
        while(observador == null);
        
        PlanificadorRoundRobin planificador = (PlanificadorRoundRobin) observador;
        if(!colaProcesosParaGenerar.estaVacia() && colaProcesosParaGenerar.ultimoProceso().recuperarTiempoLlegada() > planificador.obtenerTiempoGlobal())
            planificador.actualizarTiempoGlobal(colaProcesosParaGenerar.ultimoProceso().recuperarTiempoLlegada());

        while(!colaProcesosParaGenerar.estaVacia()) {
            if(colaProcesosParaGenerar.ultimoProceso().recuperarTiempoLlegada() != planificador.obtenerTiempoGlobal())
                break;
            observador.notificar(colaProcesosParaGenerar.eliminar());
        }
        observador.notificar();
    }

    public ColaOrdenada recuperarColaOrdenada() {
        return this.colaProcesosParaGenerar;
    }

    public void prepararProceso(Proceso proceso) {
        colaProcesosParaGenerar.insertar(proceso);
    }

    @Override
    public void agregarObservador(Observador<Proceso> o) {
        this.observador = o;
    }

    @Override
    public void removerObservador(Observador<Proceso> o) {
        if(this.observador == o)
            this.observador = null;
    }
}