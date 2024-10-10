package proceso;

public class Memoria {
    private int espacioDisponible;

    public Memoria(int tamano) {
        this.espacioDisponible = tamano;
    }

    public boolean cargarProceso(Proceso proceso) {
        if (proceso.recuperarTamano() <= espacioDisponible) {
            espacioDisponible -= proceso.recuperarTamano();
            System.out.println("Cargado en memoria: " + proceso.recuperarNombre() + ". Espacio disponible: " + espacioDisponible);
            return true;
        } else {
            System.out.println("No hay suficiente memoria para " + proceso.recuperarNombre());
            return false;
        }
    }

    public void liberarMemoria(Proceso proceso) {
        espacioDisponible += proceso.recuperarTamano();
        System.out.println("Memoria liberada por " + proceso.recuperarNombre() + ". Espacio disponible: " + espacioDisponible);
    }
}

