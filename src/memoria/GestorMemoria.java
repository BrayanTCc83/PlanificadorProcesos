package memoria;

import estructura.ColaSimple;
import proceso.Proceso;

public class GestorMemoria {
    private static GestorMemoria gestorMemoria = null;
    private static int tamano = 1000;

    private final int MAXIMO_MEMORIA;
    private ColaSimple<Proceso> memoria;
    private int memoriaOcupada;

    public static GestorMemoria recuperarGestorMemoria() {
        if(gestorMemoria == null)
            gestorMemoria = new GestorMemoria();
        return GestorMemoria.gestorMemoria;
    }

    public static void cambiarTamanoMemoria(int tamano) {
        GestorMemoria.tamano = tamano;
    }

    private GestorMemoria() {
        MAXIMO_MEMORIA = GestorMemoria.tamano;
    }

    public boolean insertarProceso(Proceso proceso) {
        int tamano = proceso.recuperarTamano();
        if(MAXIMO_MEMORIA < tamano + memoriaOcupada)
            return false;

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
}