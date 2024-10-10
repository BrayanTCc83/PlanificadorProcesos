package proceso;

public class Proceso {
    private static int CONTEO_PROCESOS = 1;
    public static final int NO_TIEMPO = -1;
    private final int id;
    private final String nombre;
    private final int tamano;
    private final int tiempoEjecucion;
    private final int tiempoLlegada;
    private int tiempoPrimeraSubida = Proceso.NO_TIEMPO, 
                tiempoUltimaSubida = Proceso.NO_TIEMPO, 
                tiempoFinalizacion = Proceso.NO_TIEMPO, 
                tiempoEjecutado = 0;

    public Proceso(String nombre, int tamano, int tiempoEjecucion, int tiempoLlegada) {
        this.id = Proceso.CONTEO_PROCESOS++;
        this.nombre = nombre;
        this.tamano = tamano;
        this.tiempoEjecucion = tiempoEjecucion;
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

    public void establecerTiempoSubida(int tiempo) {
        if(tiempoPrimeraSubida == Proceso.NO_TIEMPO)
            tiempoPrimeraSubida = tiempo;
        tiempoUltimaSubida = tiempo;
    }

    public int recuperarTiempoPrimeraSubida() {
        return this.tiempoPrimeraSubida;
    }

    public int recuperarTiempoFinalizacion() {
        return this.tiempoFinalizacion;
    }

    public void incrementarTiempoEjecutado(int tiempo) {
        if(tiempoEjecutado + tiempo > tiempoEjecucion)
            tiempoEjecutado = tiempoEjecucion;
        else
            tiempoEjecutado += tiempo;
    }

    public void establecerTiempoFinalizacion(int tiempo) {
        this.tiempoFinalizacion = tiempo;
    }

    public int recuperarTiempoEjecutado() {
        return this.tiempoEjecutado;
    }

    public boolean estaFinalizado() {
        return tiempoEjecucion - tiempoEjecutado == 0;
    }

    @Override
    public String toString() {
        return "Proceso "
        + "{ "
            + "id: " + id + ", " 
            + "nombre: " + nombre + ", " 
            + "tamaño: " + tamano + ", "
            + "tiempo_llegada: " + tiempoLlegada + ", "
            + "tiempo_ejecución: " + tiempoEjecucion + ", "
            + "tiempo_restante: " + (tiempoEjecucion - tiempoEjecutado) + " "
        + "}";
    }

    public static int recuperarConteoProcesos() {
        return CONTEO_PROCESOS;
    }
}