// EdgeObj.java
package Graph;
/**
 * Clase que representa una arista (edge) en el grafo.
 * Mantiene referencias a los dos vértices que conecta y la información
 * adicional (por ejemplo un peso) asociada a la arista.

 */
public class EdgeObj<V, E> {
    /** Información asociada a la arista (p.e. peso). */
    protected E info;
    /** Primer vértice extremo de la arista. */
    protected VertexObj<V, E> endVertex1;
    /** Segundo vértice extremo de la arista. */
    protected VertexObj<V, E> endVertex2;
    /**
     * Posición que ocupa la arista dentro de la colección de aristas
     * del grafo. Facilita operaciones de eliminación en tiempo O(1)
     * cuando se usa una lista o arreglo de aristas.
     */
    protected int position;

    /**
     * Construye una nueva arista que conecta {@code vert1} y {@code vert2} y
     * almacena la información {@code info}.
     */
    public EdgeObj(VertexObj<V, E> vert1, VertexObj<V, E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    // --- Métodos de acceso (getters) ---

    /** primer vértice extremo. */
    public VertexObj<V, E> getEndVertex1() {
        return endVertex1;
    }
    /** segundo vértice extremo. */
    public VertexObj<V, E> getEndVertex2() {
        return endVertex2;
    }
    /** información (peso) de la arista. */
    public E getInfo() {
        return info;
    }
    /** posición de la arista en la lista del grafo. */
    public int getPosition() {
        return position;
    }
}
