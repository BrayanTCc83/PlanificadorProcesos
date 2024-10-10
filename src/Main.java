import estructura.ColaSimple;
import java.util.Scanner;
import proceso.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Proceso p1 = new Proceso("P1", 1, 10, 10, 0);
        ColaSimple<Proceso> lista = new ColaSimple<>();
        //lista.insertar(p1);

        Memoria memoria = new Memoria(1024);

        System.out.println("Bienvenido al simulador de planificación de procesos.");
        System.out.print("Inserte el número de procesos: ");
        int numeroProcesos = scanner.nextInt();

        for (int i = 0; i < numeroProcesos; i++) {
            System.out.println("\nDatos del proceso... " + (i + 1) + ":");

            System.out.print("Inserte nombre del proceso: ");
            String nombre = scanner.next();

            System.out.print("Inserte tamaño del proceso: ");
            int tamano = scanner.nextInt();

            System.out.print("Inserte tiempo de ejecución del proceso: ");
            int tiempoEjecucion = scanner.nextInt();

            System.out.print("Inserte tiempo de llegada del proceso: ");
            int tiempoLlegada = scanner.nextInt();

            Proceso nuevoProceso = new Proceso(nombre, tamano, tiempoEjecucion, tiempoLlegada);

            lista.insertar(nuevoProceso);
        }

        System.out.println("\nLista de procesos listos:");
        System.out.println(lista);

        PlanificadorRoundRobin planificador = new PlanificadorRoundRobin(100, memoria, lista);

        planificador.ejecutar();

        planificador.mostrarTiemposPromedio();

        scanner.close();
    }
}
