package Graph;
import Listlinked.ListLinked;
import java.util.*;
/**
 * Clase que representa un grafo no dirigido utilizando listas enlazadas.
 * Soporta operaciones como inserción de vértices/aristas, búsquedas, recorridos (DFS/BFS),
 * detección de conexión y caminos más cortos con Dijkstra.
 */
public class GraphLink<E> {
    // Lista de vértices del grafo
    protected ListLinked<Vertex<E>> listVertex;
    // Constructor: inicializa la lista de vértices
    public GraphLink() {
        listVertex = new ListLinked<>();
    }
    /**
     * Inserta un nuevo vértice si no existe ya en el grafo.
     */
    public void insertVertex(E data) {
        Vertex<E> newVertex = new Vertex<>(data);
        if (!listVertex.contains(newVertex)) {
            listVertex.add(newVertex);
        }
    }
    /**
     * Inserta una arista no dirigida entre dos vértices (sin peso).
     */
    public void insertEdge(E verOri, E verDes) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);
        if (vOri != null && vDes != null && !vOri.listAdj.contains(new Edge<>(vDes))) {
            vOri.listAdj.add(new Edge<>(vDes));
            vDes.listAdj.add(new Edge<>(vOri));
        }
    }
    /**
     * Inserta una arista no dirigida con peso entre dos vértices.
     */
    public void insertEdgeWeight(E v, E z, int w) {
        Vertex<E> vOri = getVertex(v);
        Vertex<E> vDes = getVertex(z);
        if (vOri != null && vDes != null && !vOri.listAdj.contains(new Edge<>(vDes))) {
            vOri.listAdj.add(new Edge<>(vDes, w));
            vDes.listAdj.add(new Edge<>(vOri, w));
        }
    }
    /**
     * Verifica si existe un vértice con el dato especificado.
     */
    public boolean searchVertex(E v) {
        return getVertex(v) != null;
    }
    /**
     * Verifica si existe una arista entre dos vértices.
     */
    public boolean searchEdge(E v, E z) {
        Vertex<E> vertexV = getVertex(v);
        Vertex<E> vertexZ = getVertex(z);
        if (vertexV == null || vertexZ == null) return false;
        return vertexV.listAdj.contains(new Edge<>(vertexZ));
    }
    /**
     * Elimina una arista entre dos vértices.
     */
    public void removeEdge(E v, E z) {
        Vertex<E> vertexV = getVertex(v);
        Vertex<E> vertexZ = getVertex(z);
        if (vertexV != null && vertexZ != null) {
            vertexV.listAdj.remove(new Edge<>(vertexZ));
            vertexZ.listAdj.remove(new Edge<>(vertexV));
        }
    }
    /**
     * Elimina un vértice y todas sus conexiones.
     */
    public void removeVertex(E v) {
        Vertex<E> target = getVertex(v);
        if (target != null) {
            for (int i = 0; i < listVertex.size(); i++) {
                Vertex<E> current = listVertex.get(i);
                current.listAdj.remove(new Edge<>(target));
            }
            listVertex.remove(target);
        }
    }
    /**
     * Devuelve el vértice que contiene el dato dado, o null si no existe.
     */
    private Vertex<E> getVertex(E v) {
        for (int i = 0; i < listVertex.size(); i++) {
            Vertex<E> current = listVertex.get(i);
            if (current.getData().equals(v)) return current;
        }
        return null;
    }
    /**
     * Realiza un recorrido en profundidad (DFS) desde un vértice dado.
     */
    public void dfs(E v) {
        Vertex<E> start = getVertex(v);
        if (start == null) return;

        boolean[] visited = new boolean[listVertex.size()];
        dfsRecursive(start, visited);
    }
    /**
     * Método auxiliar recursivo para DFS.
     */
    private void dfsRecursive(Vertex<E> vertex, boolean[] visited) {
        int index = listVertex.indexOf(vertex);
        if (index == -1 || visited[index]) return;

        System.out.print(vertex.getData() + " ");
        visited[index] = true;

        for (int i = 0; i < vertex.listAdj.size(); i++) {
            Vertex<E> neighbor = vertex.listAdj.get(i).getRefDest();
            dfsRecursive(neighbor, visited);
        }
    }
    /**
     * Realiza un recorrido en anchura (BFS) desde un vértice dado.
     */
    public void bfs(E v) {
        Vertex<E> start = getVertex(v);
        if (start == null) return;
        boolean[] visited = new boolean[listVertex.size()];
        Queue<Vertex<E>> queue = new LinkedList<>();
        queue.add(start);
        visited[listVertex.indexOf(start)] = true;
        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.print(current.getData() + " ");

            for (int i = 0; i < current.listAdj.size(); i++) {
                Vertex<E> neighbor = current.listAdj.get(i).getRefDest();
                int index = listVertex.indexOf(neighbor);
                if (!visited[index]) {
                    queue.add(neighbor);
                    visited[index] = true;
                }
            }
        }
    }
    /**
     * Retorna la ruta más corta (sin pesos) entre dos vértices usando BFS.
     */
    public ArrayList<E> bfsPath(E startData, E endData) {
        Vertex<E> start = getVertex(startData);
        Vertex<E> end = getVertex(endData);
        if (start == null || end == null) return null;
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        boolean[] visited = new boolean[listVertex.size()];
        queue.add(start);
        visited[listVertex.indexOf(start)] = true;
        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (current.equals(end)) break;
            for (int i = 0; i < current.listAdj.size(); i++) {
                Vertex<E> neighbor = current.listAdj.get(i).getRefDest();
                int idx = listVertex.indexOf(neighbor);
                if (!visited[idx]) {
                    visited[idx] = true;
                    prev.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        ArrayList<E> path = new ArrayList<>();
        Vertex<E> current = end;
        while (current != null && !current.equals(start)) {
            path.add(0, current.getData());
            current = prev.get(current);
        }

        if (current == null) return null;
        path.add(0, start.getData());
        return path;
    }
    /**
     * Algoritmo de Dijkstra para encontrar el camino más corto considerando pesos.
     */
    public ArrayList<E> shortPath(E startData, E endData) {
        Vertex<E> start = getVertex(startData);
        Vertex<E> end = getVertex(endData);
        if (start == null || end == null) return null;
        Map<Vertex<E>, Integer> dist = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        PriorityQueue<Vertex<E>> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        Set<Vertex<E>> visited = new HashSet<>();

        // Inicializa las distancias
        for (int i = 0; i < listVertex.size(); i++) {
            Vertex<E> v = listVertex.get(i);
            dist.put(v, Integer.MAX_VALUE);
        }
        dist.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex<E> u = pq.poll();
            if (!visited.add(u)) continue;
            for (int i = 0; i < u.listAdj.size(); i++) {
                Edge<E> edge = u.listAdj.get(i);
                Vertex<E> neighbor = edge.getRefDest();
                int newDist = dist.get(u) + edge.getWeight();
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, u);
                    pq.add(neighbor);
                }
            }
        }
        ArrayList<E> path = new ArrayList<>();
        Vertex<E> current = end;

        while (current != null && !current.equals(start)) {
            path.add(0, current.getData());
            current = prev.get(current);
        }

        if (current == null) return null;
        path.add(0, start.getData());
        return path;
    }
    /**
     * Devuelve la ruta más corta como una pila (stack), usando Dijkstra.
     */
    public Stack<E> dijkstra(E startData, E endData) {
        ArrayList<E> path = shortPath(startData, endData);
        if (path == null) return null;

        Stack<E> stack = new Stack<>();
        for (E data : path) {
            stack.push(data);
        }
        return stack;
    }
    /**
     * Verifica si el grafo es conexo (si todos los vértices están alcanzables entre sí).
     */
    public boolean isConexo() {
        if (listVertex.size() == 0) return true;
        boolean[] visited = new boolean[listVertex.size()];
        dfsRecursive(listVertex.get(0), visited);
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
    /**
     * Retorna la representación en texto del grafo (lista de vértices).
     */
    public String toString() {
        return listVertex.toString();
    }
}