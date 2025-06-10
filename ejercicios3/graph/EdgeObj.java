package graph;

public class EdgeObj<V,E> {
    protected E info;
    protected VertexObj<V,E> endVertex1;
    protected VertexObj<V,E> endVertex2;
    protected int position;

    public EdgeObj(VertexObj<V,E> vert1, VertexObj<V,E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    public VertexObj<V,E> getEndVertex1() {
        return endVertex1;
    }

    public VertexObj<V,E> getEndVertex2() {
        return endVertex2;
    }

    public E getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EdgeObj<?, ?> edge) {
            return (edge.endVertex1.equals(endVertex1) && edge.endVertex2.equals(endVertex2)) ||
                   (edge.endVertex1.equals(endVertex2) && edge.endVertex2.equals(endVertex1)); // no dirigido
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + endVertex1 + " - " + endVertex2 + ")";
    }
}
