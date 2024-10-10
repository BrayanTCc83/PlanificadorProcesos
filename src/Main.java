import memoria.GestorMemoria;

import java.util.Scanner;

import algoritmo.PlanificadorRoundRobin;
import proceso.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PlanificadorRoundRobin planificador = new PlanificadorRoundRobin(4);
        GestorProcesos gestorProcesos = GestorProcesos.recuperarGestorProcesos();
        gestorProcesos.agregarObservador(planificador);

        try {
            GestorMemoria.cambiarTamanoMemoria(1024);

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

                gestorProcesos.prepararProceso(nuevoProceso);
            }

            System.out.println("\nLista de procesos a simular:");
            System.out.println(gestorProcesos.recuperarColaOrdenada());

            gestorProcesos.iniciarSimulacion();
            planificador.mostrarTiemposPromedio();

            scanner.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            //System.out.println("Tiempo de respuesta para " + proceso.recuperarNombre() + ": " +  (tiempoGlobal - proceso.recuperarTiempoLlegada()));
        }
    }
}
