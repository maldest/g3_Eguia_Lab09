package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Representa un grafo no dirigido utilizando listas de vértices y aristas.
 */
public class GraphListEdge<V, E> {
    private ArrayList<VertexObj<V, E>> secVertex;
    private ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        secVertex = new ArrayList<>();
        secEdge = new ArrayList<>();
    }

    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            secVertex.add(new VertexObj<>(v, secVertex.size()));
        }
    }

    public boolean searchVertex(V v) {
        return getVertex(v) != null;
    }

    public void insertEdge(V v1, V v2) {
        VertexObj<V, E> vert1 = getVertex(v1);
        VertexObj<V, E> vert2 = getVertex(v2);

        if (vert1 != null && vert2 != null && !searchEdge(v1, v2)) {
            secEdge.add(new EdgeObj<>(vert1, vert2, null, secEdge.size()));
        }
    }

    public boolean searchEdge(V v1, V v2) {
        VertexObj<V, E> vert1 = getVertex(v1);
        VertexObj<V, E> vert2 = getVertex(v2);
        if (vert1 == null || vert2 == null) return false;

        for (EdgeObj<V, E> edge : secEdge) {
            if ((edge.getEndVertex1().equals(vert1) && edge.getEndVertex2().equals(vert2)) ||
                (edge.getEndVertex1().equals(vert2) && edge.getEndVertex2().equals(vert1))) {
                return true;
            }
        }
        return false;
    }

    public void bfs(V startData) {
        VertexObj<V, E> start = getVertex(startData);
        if (start == null) return;

        boolean[] visited = new boolean[secVertex.size()];
        Queue<VertexObj<V, E>> queue = new LinkedList<>();

        queue.add(start);
        visited[start.getPosition()] = true;

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.print(current.getInfo() + " ");

            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;
                if (edge.getEndVertex1().equals(current)) {
                    neighbor = edge.getEndVertex2();
                } else if (edge.getEndVertex2().equals(current)) {
                    neighbor = edge.getEndVertex1();
                }

                if (neighbor != null && !visited[neighbor.getPosition()]) {
                    visited[neighbor.getPosition()] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    private VertexObj<V, E> getVertex(V info) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(info)) {
                return vertex;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Vertices:\n");
        for (VertexObj<V, E> v : secVertex) {
            sb.append(v.getInfo()).append("\n");
        }

        sb.append("Aristas:\n");
        for (EdgeObj<V, E> e : secEdge) {
            sb.append(e.getEndVertex1().getInfo())
              .append(" -- ")
              .append(e.getEndVertex2().getInfo())
              .append("\n");
        }
        return sb.toString();
    }
}
