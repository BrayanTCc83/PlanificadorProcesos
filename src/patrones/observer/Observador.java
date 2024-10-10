package patrones.observer;

public interface Observador<T> {
    public void notificar();
    public void notificar(T objeto);
}
