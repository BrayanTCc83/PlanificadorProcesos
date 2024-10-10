package memoria;

import estructura.ColaSimple;
import patrones.observer.Observable;
import patrones.observer.Observador;
import proceso.Proceso;

public class GestorMemoria implements Observable<Proceso> {
    public static final int NO_ASIGNADA = -1;
    private static GestorMemoria gestorMemoria = null;
    private static int tamano = 1000;

    private final int MAXIMO_MEMORIA;
    private ColaSimple<Proceso> memoria;
    private int memoriaOcupada;
    private Observador<Proceso> observador = null;

    public static GestorMemoria recuperarGestorMemoria() {
        if(gestorMemoria == null)
            gestorMemoria = new GestorMemoria();
        return gestorMemoria;
    }

    public static void cambiarTamanoMemoria(int tamano) throws Exception {
        if(GestorMemoria.tamano == GestorMemoria.NO_ASIGNADA)
            throw new Exception("La memoria ya ha sido asignada.");
        GestorMemoria.tamano = tamano;
    }

    private GestorMemoria() {
        MAXIMO_MEMORIA = GestorMemoria.tamano;
        memoria = new ColaSimple<>();
    }

    public boolean insertarProceso(Proceso proceso) {
        int tamano = proceso.recuperarTamano();
        if(MAXIMO_MEMORIA < tamano + memoriaOcupada)
            return false;

        System.out.println("El proceso " + proceso.recuperarNombre() + " ha subido a memoria y se encuentra listo para ejecucion.");
        memoria.insertar(proceso);
        memoriaOcupada += tamano;
        return true;
    }

    public Proceso extraerProceso() {
        Proceso proceso = memoria.eliminar();
        if(proceso == null)
            return proceso;

        memoriaOcupada -= proceso.recuperarTamano();
        return proceso;
    }

    public int obtenerMemoriaDisponible() {
        return MAXIMO_MEMORIA - memoriaOcupada;
    }

    public int obtenerMemoriaOcupada() {
        return memoriaOcupada;
    }
    
    public boolean tieneProcesos() {
        return !memoria.estaVacia();
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