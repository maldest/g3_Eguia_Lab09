package graph;

import java.util.*;
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

    //Ejercicio 1.a - BFS desde un vértice v
    public void bfs(E v) {
        int startIndex = listVertex.search(new Vertex<>(v));
        if (startIndex == -1) {
            System.out.println("Vértice no encontrado: " + v);
            return;
        }

        // Lista de vértices visitados
        ArrayList<Vertex<E>> visited = new ArrayList<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        Vertex<E> start = listVertex.get(startIndex);
        visited.add(start); // marcamos como visitado
        queue.offer(start); // lo agregamos a la cola

        System.out.print("BFS desde " + v + ": ");

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll(); // tomamos el siguiente en la cola
            System.out.print(current.getData() + " ");

            // recorremos su lista de adyacencias
            ListLinked<Edge<E>> adjList = current.getListAdj();
            for (int i = 0; i < adjList.size(); i++) {
                Vertex<E> neighbor = adjList.get(i).getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    //Ejercicio 1.b - BFSPath: retorna camino de v a z si existe
    public ArrayList<E> bfsPath(E v, E z) {
        int startIndex = listVertex.search(new Vertex<>(v));
        int endIndex = listVertex.search(new Vertex<>(z));

        if (startIndex == -1 || endIndex == -1) {
            System.out.println("Uno o ambos vértices no existen.");
            return new ArrayList<>();
        }

        Vertex<E> start = listVertex.get(startIndex);
        Vertex<E> end = listVertex.get(endIndex);

        Queue<Vertex<E>> queue = new LinkedList<>();
        ArrayList<Vertex<E>> visited = new ArrayList<>();
        HashMap<Vertex<E>, Vertex<E>> prev = new HashMap<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (current.equals(end)) break;

            ListLinked<Edge<E>> adjList = current.getListAdj();
            for (int i = 0; i < adjList.size(); i++) {
                Vertex<E> neighbor = adjList.get(i).getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    prev.put(neighbor, current); // guardamos su predecesor
                }
            }
        }

        // reconstrucción del camino
        ArrayList<E> path = new ArrayList<>();
        if (!prev.containsKey(end) && !start.equals(end)) return path;

        for (Vertex<E> at = end; at != null; at = prev.get(at)) {
            path.add(0, at.getData()); // agregamos en orden inverso
        }

        return path;
    }

    //Ejercicio 2a - Inserta una arista ponderada entre dos vértices
    public void insertEdgeWeight(E v, E z, int w) {
        Vertex<E> vOri = listVertex.get(listVertex.search(new Vertex<>(v)));
        Vertex<E> vDes = listVertex.get(listVertex.search(new Vertex<>(z)));

        if (vOri != null && vDes != null) {
            vOri.getListAdj().insert(new Edge<>(vDes, w));
            vDes.getListAdj().insert(new Edge<>(vOri, w)); // grafo no dirigido
        }
    }

    //Ejercicio 2b - Retorna un ArrayList con la ruta más corta desde v hasta z usando Dijkstra
    public ArrayList<E> shortPath(E v, E z) {
        return dijkstra(v, z); // Usa el método de abajo directamente
    }

    //Ejercicio 2d - Dijkstra: ruta más corta entre dos vértices (retorna ArrayList)
    public ArrayList<E> dijkstra(E v, E z) {
        int n = listVertex.size();
        Map<Vertex<E>, Integer> dist = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        Set<Vertex<E>> visited = new HashSet<>();
        PriorityQueue<Vertex<E>> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (int i = 0; i < n; i++) {
            Vertex<E> vertex = listVertex.get(i);
            dist.put(vertex, Integer.MAX_VALUE);
            prev.put(vertex, null);
        }

        Vertex<E> start = listVertex.get(listVertex.search(new Vertex<>(v)));
        Vertex<E> end = listVertex.get(listVertex.search(new Vertex<>(z)));

        if (start == null || end == null) return new ArrayList<>();

        dist.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (visited.contains(current)) continue;
            visited.add(current);

            ListLinked<Edge<E>> adj = current.getListAdj();
            for (int i = 0; i < adj.size(); i++) {
                Edge<E> edge = adj.get(i);
                Vertex<E> neighbor = edge.getRefDest();
                int newDist = dist.get(current) + edge.getWeight();
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // reconstrucción del camino
        ArrayList<E> path = new ArrayList<>();
        for (Vertex<E> at = end; at != null; at = prev.get(at)) {
            path.add(0, at.getData());
        }

        if (path.isEmpty() || !path.get(0).equals(v)) return new ArrayList<>(); // no hay camino
        return path;
    }

    //Ejercicio 2c - Verifica si el grafo es conexo
    public boolean isConexo() {
        if (listVertex.isEmpty()) return true;

        ArrayList<Vertex<E>> visited = new ArrayList<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        Vertex<E> start = listVertex.get(0);
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            ListLinked<Edge<E>> adj = current.getListAdj();

            for (int i = 0; i < adj.size(); i++) {
                Vertex<E> neighbor = adj.get(i).getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited.size() == listVertex.size();
    }

    @Override
    public String toString() {
        return this.listVertex.toString();
    }
}
