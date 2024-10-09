public class Proceso {
    private static int CONTEO_PROCESOS = 1;
    private final int id;
    private final String nombre;
    private final int tamano;
    private final int tiempoEjecucion;
    private final int prioridad;
    private final int tiempoLlegada;
    private int tiempoPrimeraSubida, tiempoUltimaSubida, tiempoTotalEjecutado, tiempoTotalEjecución;

    public Proceso(String nombre, int tamano, int tiempoEjecucion, int prioridad, int tiempoLlegada) {
        this.id = Proceso.CONTEO_PROCESOS++;
        this.nombre = nombre;
        this.tamano = tamano;
        this.tiempoEjecucion = tiempoEjecucion;
        this.prioridad = prioridad;
        this.tiempoLlegada = tiempoLlegada;
    }

    public int recuperarId() {
        return this.id;
    }

    public String recuperarNombre() {
        return this.nombre;
    }

    public int recuperarTamano() {
        return this.tamano;
    }

    public int recuperarTiempoEjecucion() {
        return this.tiempoEjecucion;
    }

    public int recuperarTiempoLlegada() {
        return this.tiempoLlegada;
    }

    public int recuperarPrioridad() {
        return this.prioridad;
    }

    @Override
    public String toString() {
        return "Proceso[" + this.id + "] "
        + "{ "
            + "nombre: " + this.nombre + ", " 
            + "tamaño: " + this.tamano + ", "
            + "tiempo_llegada: " + this.tiempoLlegada + ", "
            + "tiempo_ejecución: " + this.tiempoEjecucion + ", "
            + "prioridad: " + this.prioridad + " "
        + "}";
    }

    public static int recuperarConteoProcesos() {
        return CONTEO_PROCESOS;
    }
}