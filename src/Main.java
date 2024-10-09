import proceso.*;

public class Main {
    public static void main(String[] args) {
        Proceso p1 = new Proceso("P1", 1, 10, 10, 0);
        ListaProcesos lista = new ListaProcesos();
        lista.insertarProceso(p1);

        System.out.println("Hello");
        System.out.println("Lista de procesos listos: \n" + lista);
    }
}