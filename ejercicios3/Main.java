import graph.GraphListEdge;

public class Main {
    public static void main(String[] args) {
        // Crear un grafo no dirigido con tipo de dato String
        GraphListEdge<String, Integer> grafo = new GraphListEdge<>();

        // Insertar vértices
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");
        grafo.insertVertex("E");

        // Insertar aristas
        grafo.insertEdge("A", "B");
        grafo.insertEdge("A", "C");
        grafo.insertEdge("B", "D");
        grafo.insertEdge("C", "E");
        grafo.insertEdge("D", "E");

        // Mostrar mensaje de éxito
        System.out.println("Grafo construido con vértices y aristas:");

        // Probar búsqueda de vértices
        System.out.println("¿Existe vértice 'A'? " + grafo.searchVertex("A"));
        System.out.println("¿Existe vértice 'F'? " + grafo.searchVertex("F"));

        // Probar búsqueda de aristas
        System.out.println("¿Existe arista entre 'A' y 'C'? " + grafo.searchEdge("A", "C"));
        System.out.println("¿Existe arista entre 'B' y 'E'? " + grafo.searchEdge("B", "E"));

        // Recorrido BFS desde el vértice "A"
        System.out.println("\nRecorrido BFS desde el vértice A:");
        grafo.bfs("A");

        // Recorrido BFS desde el vértice "E"
        System.out.println("\nRecorrido BFS desde el vértice E:");
        grafo.bfs("E");
    }
}
