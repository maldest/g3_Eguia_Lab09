// Main.java
package Graph;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Clase de demostración que permite manipular un grafo desde consola.
 * Incluye un sencillo menú para añadir/eliminar vértices y aristas
 * así como ejecutar recorridos y algoritmos clásicos (BFS, DFS, Dijkstra).
 */
public class Main {
    public static void main(String[] args) {
        // Uso de try‑with‑resources para cerrar Scanner automáticamente.
        try (Scanner sc = new Scanner(System.in)) {
            GraphLink<String> graph = new GraphLink<>(); // Grafo no dirigido usando listas de adyacencia.
            int option;

            // Bucle principal: se repite hasta que el usuario elija salir.
            do {
                // --- Presentación del menú ---
                System.out.println("\n--- MENÚ DE GRAFO ---");
                System.out.println("1. Insertar vértice");
                System.out.println("2. Insertar arista simple");
                System.out.println("3. Insertar arista ponderada");
                System.out.println("4. Eliminar vértice");
                System.out.println("5. Eliminar arista");
                System.out.println("6. Buscar vértice");
                System.out.println("7. Buscar arista");
                System.out.println("8. Recorrido DFS");
                System.out.println("9. Recorrido BFS");
                System.out.println("10. BFS Path (camino por anchura)");
                System.out.println("11. Camino más corto (Dijkstra)");
                System.out.println("12. ¿Es conexo?");
                System.out.println("13. Salir");
                System.out.print("Seleccione una opción: ");
                // Lectura segura del entero; si no es número repetimos.
                while (!sc.hasNextInt()) {
                    System.out.println("⚠️  Debe ingresar un número.");
                    sc.next();
                }
                option = sc.nextInt();
                sc.nextLine(); // Consumir salto de línea

                String v1, v2;
                switch (option) {
                    case 1 -> { // Insertar vértice
                        System.out.print("Ingrese vértice: ");
                        v1 = sc.nextLine();
                        graph.insertVertex(v1);
                    }
                    case 2 -> { // Arista no ponderada
                        System.out.print("Origen: ");
                        v1 = sc.nextLine();
                        System.out.print("Destino: ");
                        v2 = sc.nextLine();
                        graph.insertEdge(v1, v2);
                    }
                    case 3 -> { // Arista ponderada
                        System.out.print("Origen: ");
                        v1 = sc.nextLine();
                        System.out.print("Destino: ");
                        v2 = sc.nextLine();
                        System.out.print("Peso: ");
                        int peso = sc.nextInt();
                        sc.nextLine();
                        graph.insertEdgeWeight(v1, v2, peso);
                    }
                    case 4 -> { // Eliminar vértice
                        System.out.print("Vértice a eliminar: ");
                        v1 = sc.nextLine();
                        graph.removeVertex(v1);
                    }
                    case 5 -> { // Eliminar arista
                        System.out.print("Origen: ");
                        v1 = sc.nextLine();
                        System.out.print("Destino: ");
                        v2 = sc.nextLine();
                        graph.removeEdge(v1, v2);
                    }
                    case 6 -> { // Buscar vértice
                        System.out.print("Buscar vértice: ");
                        v1 = sc.nextLine();
                        System.out.println("Existe: " + graph.searchVertex(v1));
                    }
                    case 7 -> { // Buscar arista
                        System.out.print("Origen: ");
                        v1 = sc.nextLine();
                        System.out.print("Destino: ");
                        v2 = sc.nextLine();
                        System.out.println("Existe arista: " + graph.searchEdge(v1, v2));
                    }
                    case 8 -> { // DFS
                        System.out.print("DFS desde: ");
                        v1 = sc.nextLine();
                        graph.dfs(v1);
                        System.out.println();
                    }
                    case 9 -> { // BFS
                        System.out.print("BFS desde: ");
                        v1 = sc.nextLine();
                        graph.bfs(v1);
                        System.out.println();
                    }
                    case 10 -> { // BFS Path
                        System.out.print("Inicio: ");
                        v1 = sc.nextLine();
                        System.out.print("Fin: ");
                        v2 = sc.nextLine();
                        ArrayList<String> path = graph.bfsPath(v1, v2);
                        System.out.println("Camino: " + path);
                    }
                    case 11 -> { // Dijkstra
                        System.out.print("Inicio: ");
                        v1 = sc.nextLine();
                        System.out.print("Fin: ");
                        v2 = sc.nextLine();
                        Stack<String> ruta = graph.dijkstra(v1, v2);
                        System.out.println("Ruta (Dijkstra): " + ruta);
                    }
                    case 12 -> // ¿Es el grafo conexo?
                        System.out.println("¿Es conexo? " + graph.isConexo());
                    case 13 -> System.out.println("¡Fin del programa!");
                    default -> System.out.println("Opción no válida.");
                }
            } while (option != 13);
        } // El Scanner se cierra automáticamente aquí.
    }
}
