import graph.GraphLink;

public class Main {
    public static void main(String[] args) {
        // Crear un grafo no dirigido con tipo de dato String
        GraphLink<String> grafo = new GraphLink<>();

        // Insertar vértices
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");

        // Insertar aristas
        grafo.insertEdge("A", "B");
        grafo.insertEdge("A", "C");
        grafo.insertEdge("B", "D");

        // Mostrar el grafo
        System.out.println("Representación del grafo:");
        System.out.println(grafo);
    }
}
