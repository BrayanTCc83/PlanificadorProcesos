import memoria.GestorMemoria;

import java.util.Scanner;

import algoritmo.PlanificadorRoundRobin;
import estructura.ColaSimple;
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
            mostrarTiemposPromedio();

            scanner.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void mostrarTiemposPromedio() {
        GestorProcesos gestorProcesos = GestorProcesos.recuperarGestorProcesos();
        ColaSimple<Proceso> finalizados = gestorProcesos.recuperarProcesosFinalizados();

        float n = finalizados.obtenerTamano();
        float tiempoEsperaTotal = 0;
        float tiempoRespuestaTotal = 0;
        float tiempoEjecucionTotal = 0;
        while(!finalizados.estaVacia()) {
            Proceso proceso = finalizados.eliminar();
            int tiempoEspera = proceso.recuperarTiempoUltimaSubida() - proceso.recuperarTiempoLlegada() - proceso.recuperarTiempoEjecucionPrevio();
            int tiempoRespuesta = proceso.recuperarTiempoPrimeraSubida() - proceso.recuperarTiempoLlegada();
            int tiempoEjecucion = proceso.recuperarTiempoFinalizacion() - proceso.recuperarTiempoLlegada();

            tiempoEsperaTotal += tiempoEspera;
            tiempoRespuestaTotal += tiempoRespuesta;
            tiempoEjecucionTotal += tiempoEjecucion;

            System.out.println("\n" + proceso);
            System.out.println("\tTiempo de espera: " + tiempoEspera + " [ms].");
            System.out.println("\tTiempo de respuesta: " + tiempoRespuesta + " [ms].");
            System.out.println("\tTiempo de ejecucion: " + tiempoEjecucion + " [ms].");
        }
        System.out.println("\nEstadisticas finales");
        System.out.println("\tTiempo de espera promedio: " + tiempoEsperaTotal / n + " [ms].");
        System.out.println("\tTiempo de respuesta promedio: " + tiempoRespuestaTotal / n + " [ms].");
        System.out.println("\tTiempo de ejecucion promedio: " + tiempoEjecucionTotal / n + " [ms].");
    }
}
