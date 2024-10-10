package estructura;

import proceso.Proceso;

public class ColaOrdenada extends ColaSimple<Proceso> {
    @Override
    public void insertar(Proceso proceso) {
        Nodo nuevoNodo = new Nodo(proceso);
        if (head == null) {
            head = nuevoNodo;
            this.tamano++;
        } else {
            Nodo previo = null;
            Nodo cursor = head;
            while(cursor != null && cursor.value.recuperarTiempoLlegada() <= proceso.recuperarTiempoLlegada()) {
                previo = cursor;
                cursor = cursor.siguiente;
            }

            if(previo == null) {
                head = nuevoNodo;
                nuevoNodo.siguiente = cursor;
            } else {
                nuevoNodo.siguiente = previo.siguiente;
                previo.siguiente = nuevoNodo;
            }
            
            this.tamano++;
        }
    }
}