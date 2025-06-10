package graph;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    //a Recorrido BFS desde un vértice 'v'
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
    
    //b
        public ArrayList<Integer> bfsPath(int v, int z) {
        // Arreglo para marcar qué vértices han sido visitados
        boolean[] visited = new boolean[size];

        // Arreglo que almacena el predecesor de cada vértice para reconstruir el camino
        int[] prev = new int[size];
        Arrays.fill(prev, -1); // todos sin predecesor al inicio

        // Cola para el recorrido BFS
        Queue<Integer> queue = new LinkedList<>();

        // Se marca el vértice de inicio como visitado y se agrega a la cola
        visited[v] = true;
        queue.add(v);

        // Mientras haya vértices por visitar
        while (!queue.isEmpty()) {
            int actual = queue.poll(); // vértice actual

            // Si ya llegamos al destino, se puede cortar
            if (actual == z) break;

            // Recorrer vecinos del vértice actual
            Node link = list[actual];
            while (link != null) {
                int u = link.vertex;

                // Si aún no fue visitado, se registra su predecesor y se encola
                if (!visited[u]) {
                    visited[u] = true;
                    prev[u] = actual; // 'actual' es el vértice anterior en el camino
                    queue.add(u);
                }

                link = link.next;
            }
        }

        // Crear el ArrayList para guardar el camino desde v hasta z
        ArrayList<Integer> path = new ArrayList<>();

        // Si el destino no fue alcanzado, retornamos camino vacío
        if (!visited[z]) return path;

        // Reconstrucción del camino desde z hacia v usando el arreglo prev[]
        for (int at = z; at != -1; at = prev[at]) {
            path.add(0, at); // agregamos al inicio para mantener el orden de origen → destino
        }

        return path;
    }

    @Override
    public String toString() {
        return this.listVertex.toString();
    }
}

