package estructura;

public class ColaSimple<T> {
protected class Nodo {
    T value;
    Nodo siguiente;

    public Nodo(T value) {
        this.value = value;
        this.siguiente = null;
    }
}

protected Nodo head;
protected int tamano;

public ColaSimple() {
    this.head = null;
    this.tamano = 0;
}

public void insertar(T value) {
    Nodo nuevoNodo = new Nodo(value);
    if (head == null) {
        head = nuevoNodo;
        this.tamano++;
    } else {
        Nodo cursor = head;
        while (cursor.siguiente != null) {
            cursor = cursor.siguiente;
        }
        cursor.siguiente = nuevoNodo;
        this.tamano++;
    }
}

public T eliminar() {
    T value = null;
    if (head != null) {
        value = head.value;
        head = head.siguiente;
        this.tamano--;
    }
    return value;
}

public int obtenerTamano() {
    return this.tamano;
}

public boolean estaVacia() {
    return this.tamano == 0;
}

@Override
public String toString() {
    String string = "";
    int i = 0;
    Nodo cursor = head;
    while (cursor != null) {
        string += "[" + (i++) + "]" + cursor.value.toString() + "\n";
        cursor = cursor.siguiente;
    }
    return string;
}
}
