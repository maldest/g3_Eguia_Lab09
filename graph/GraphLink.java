package graph;

import java.util.LinkedList;
import java.util.Queue;

import utils.ListLinked;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    //metodo
    public void insertVertex(E data) {
        Vertex<E> v = new Vertex<>(data);
        if (listVertex.search(v) == -1) {
            listVertex.insert(v);
        }
    }

    //metodo
    public void insertEdge(E verOri, E verDes) {
        Vertex<E> vOri = listVertex.get(listVertex.search(new Vertex<>(verOri)));
        Vertex<E> vDes = listVertex.get(listVertex.search(new Vertex<>(verDes)));

        if (vOri != null && vDes != null) {
            vOri.getListAdj().insert(new Edge<>(vDes));
            vDes.getListAdj().insert(new Edge<>(vOri)); // no dirigido
        }
    }


    //Ejercicios 01
    //a
    public void bfs(int v) {
    // Arreglo para marcar los vértices ya visitados
    boolean[] visited = new boolean[size];

    // Cola para manejar el orden de visita de los vértices
    Queue<Integer> queue = new LinkedList<>();

    // Se marca el vértice de inicio como visitado y se encola
    visited[v] = true;
    queue.add(v);

    System.out.print("BFS desde " + v + ": ");

    // Mientras haya vértices en la cola, seguimos explorando
    while (!queue.isEmpty()) {
        int actual = queue.poll(); // se toma el siguiente vértice de la cola
        System.out.print(actual + " "); // se muestra como visitado

        // Se recorren todos los vecinos del vértice actual
        Node link = list[actual]; // lista de adyacencia del vértice actual
        while (link != null) {
            int u = link.vertex;

            // Si el vecino no ha sido visitado, se marca y se agrega a la cola
            if (!visited[u]) {
                visited[u] = true;
                queue.add(u);
            }

            link = link.next; // pasar al siguiente vecino
        }
    }
    System.out.println(); // salto de línea al finalizar recorrido
}
    

    @Override
    public String toString() {
        return this.listVertex.toString();
    }
}

