package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphListEdge<V,E> {
    ArrayList<VertexObj<V,E>> secVertex;
    ArrayList<EdgeObj<V,E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // a) insertVertex(v)
    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            secVertex.add(new VertexObj<>(v, secVertex.size()));
        }
    }

    // b) insertEdge(v, z)
    public void insertEdge(V v, V z) {
        VertexObj<V,E> v1 = getVertex(v);
        VertexObj<V,E> v2 = getVertex(z);

        if (v1 != null && v2 != null) {
            EdgeObj<V,E> edge = new EdgeObj<>(v1, v2, null, secEdge.size());
            if (!secEdge.contains(edge)) {
                secEdge.add(edge);
            }
        }
    }

    // c) searchVertex(v)
    public boolean searchVertex(V v) {
        return secVertex.stream().anyMatch(vert -> vert.getInfo().equals(v));
    }

    // d) searchEdge(v, z)
    public boolean searchEdge(V v, V z) {
        VertexObj<V,E> v1 = getVertex(v);
        VertexObj<V,E> v2 = getVertex(z);
        if (v1 == null || v2 == null) return false;

        return secEdge.stream().anyMatch(edge ->
            (edge.getEndVertex1().equals(v1) && edge.getEndVertex2().equals(v2)) ||
            (edge.getEndVertex1().equals(v2) && edge.getEndVertex2().equals(v1))
        );
    }

    // e) bfs(v): recorrido en anchura
    public void bfs(V v) {
        VertexObj<V,E> start = getVertex(v);
        if (start == null) {
            System.out.println("Vértice no encontrado.");
            return;
        }

        ArrayList<VertexObj<V,E>> visited = new ArrayList<>();
        Queue<VertexObj<V,E>> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);

        System.out.print("BFS desde " + v + ": ");

        while (!queue.isEmpty()) {
            VertexObj<V,E> current = queue.poll();
            System.out.print(current + " ");

            for (EdgeObj<V,E> edge : secEdge) {
                VertexObj<V,E> neighbor = null;
                if (edge.getEndVertex1().equals(current)) {
                    neighbor = edge.getEndVertex2();
                } else if (edge.getEndVertex2().equals(current)) {
                    neighbor = edge.getEndVertex1();
                }

                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    private VertexObj<V,E> getVertex(V v) {
        for (VertexObj<V,E> vert : secVertex) {
            if (vert.getInfo().equals(v)) {
                return vert;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vértices: ");
        for (VertexObj<V,E> v : secVertex) {
            sb.append(v).append(" ");
        }
        sb.append("\nAristas: ");
        for (EdgeObj<V,E> e : secEdge) {
            sb.append(e).append(" ");
        }
        return sb.toString();
    }
}
