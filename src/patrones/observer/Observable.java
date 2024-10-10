package patrones.observer;

public interface Observable<T> {
    public void agregarObservador(Observador<T> o);
    public void removerObservador(Observador<T> o);
}
