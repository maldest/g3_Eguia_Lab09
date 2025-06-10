import graph.GraphLink;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GraphLink<String> grafo = new GraphLink<>();

        // ==== DATOS PREDEFINIDOS ====
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");
        grafo.insertVertex("E");

        grafo.insertEdge("A", "B");
        grafo.insertEdge("A", "C");
        grafo.insertEdge("B", "D");
        grafo.insertEdge("C", "E");

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n====== MENÚ DEL GRAFO ======");
            System.out.println("1. Mostrar grafo");
            System.out.println("2. Recorrido BFS desde un vértice");
            System.out.println("3. Camino BFS entre dos vértices");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nRepresentación del grafo:");
                    System.out.println(grafo);
                    break;

                case 2:
                    System.out.print("Ingrese vértice de inicio: ");
                    String inicio = sc.nextLine();
                    grafo.bfs(inicio);
                    break;

                case 3:
                    System.out.print("Ingrese vértice origen: ");
                    String origen = sc.nextLine();
                    System.out.print("Ingrese vértice destino: ");
                    String destino = sc.nextLine();
                    ArrayList<String> camino = grafo.bfsPath(origen, destino);
                    if (camino.isEmpty()) {
                        System.out.println("No hay camino entre " + origen + " y " + destino);
                    } else {
                        System.out.println("Camino: " + camino);
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 0);
    }
}

