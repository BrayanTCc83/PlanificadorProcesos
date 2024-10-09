package proceso;

public class ListaProcesos {
    private class Nodo {
        Proceso proceso;
        Nodo siguiente;
    
        public Nodo(Proceso proceso) {
            this.proceso = proceso;
            this.siguiente = null;
        }
    }

    private Nodo head;

    public ListaProcesos() {
        this.head = null;
    }

    public void insertarProceso(Proceso proceso) {
        Nodo nuevoNodo = new Nodo(proceso);
        if (head == null) {
            head = nuevoNodo;
        } else {
            Nodo cursor = head;
            while (cursor.siguiente != null) {
                cursor = cursor.siguiente;
            }
            cursor.siguiente = nuevoNodo;
        }
        System.out.println("Subio el proceso ");
    }

    public Proceso eliminarProceso() {
        Proceso proceso = null;
        if (head != null) {
            proceso = head.proceso;
            head = head.siguiente;
        }
        System.out.println("Se eliminó el proceso ");
        return proceso;
    }

    @Override
    public String toString() {
        String string = "";
        int i = 0;
        Nodo cursor = head;
        while (cursor != null) {
            string += "[" + i + "]" + cursor.proceso.toString() + "\n";
            cursor = cursor.siguiente;
        }
        return string;
    }
}