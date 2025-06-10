import graph.GraphLink;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GraphLink<String> grafo = new GraphLink<>();

        // Cargar vértices iniciales
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");
        grafo.insertVertex("E");

        // Cargar aristas ponderadas
        grafo.insertEdgeWeight("A", "B", 2);
        grafo.insertEdgeWeight("A", "C", 3);
        grafo.insertEdgeWeight("B", "D", 1);
        grafo.insertEdgeWeight("C", "D", 4);
        grafo.insertEdgeWeight("D", "E", 2);

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ DE GRAFO ---");
            System.out.println("1. Mostrar grafo");
            System.out.println("2. BFS desde un vértice");
            System.out.println("3. Camino BFS entre dos vértices");
            System.out.println("4. Ruta más corta (shortPath)");
            System.out.println("5. Verificar si es conexo");
            System.out.println("6. Ruta más corta Dijkstra");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Grafo:");
                    System.out.println(grafo);
                }
                case 2 -> {
                    System.out.print("Vértice de inicio: ");
                    String v = sc.nextLine();
                    grafo.bfs(v);
                }
                case 3 -> {
                    System.out.print("Vértice origen: ");
                    String v = sc.nextLine();
                    System.out.print("Vértice destino: ");
                    String z = sc.nextLine();
                    ArrayList<String> camino = grafo.bfsPath(v, z);
                    System.out.println("Camino BFS: " + camino);
                }
                case 4 -> {
                    System.out.print("Vértice origen: ");
                    String v = sc.nextLine();
                    System.out.print("Vértice destino: ");
                    String z = sc.nextLine();
                    ArrayList<String> camino = grafo.shortPath(v, z);
                    System.out.println("Ruta más corta (por peso): " + camino);
                }
                case 5 -> {
                    System.out.println("¿Es conexo?: " + grafo.isConexo());
                }
                case 6 -> {
                    System.out.print("Vértice origen: ");
                    String v = sc.nextLine();
                    System.out.print("Vértice destino: ");
                    String z = sc.nextLine();
                    ArrayList<String> ruta = grafo.dijkstra(v, z);
                    System.out.println("Ruta más corta (Dijkstra): " + ruta);
                }
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}

