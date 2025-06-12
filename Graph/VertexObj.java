// VertexObj.java
package Graph;
/**
 * Clase que modela un vértice (vertex) dentro de un grafo.
 * param <V> Tipo de dato almacenado en el vértice.
 * param <E> Tipo de la información de las aristas (no se usa aquí
 *           directamente, pero se mantiene para coherencia con EdgeObj).
 */
public class VertexObj<V, E> {
    /** Información (etiqueta) asociada al vértice. */
    protected V info;

    /**
     * Índice o posición del vértice en la colección de vértices del grafo.
     * Útil para algoritmos que requieren acceso rápido por posición.
     */
    protected int position;

    /**
     * Construye un vértice con la etiqueta {code info} y lo ubica en
     * la posición indicada.
     *
     * param info      etiqueta del vértice (nombre, id, etc.)
     * param position  índice en la lista del grafo
     */
    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }
    /** @return etiqueta almacenada en el vértice. */
    public V getInfo() {
        return info;
    }
    /** posición del vértice en la lista del grafo. */
    public int getPosition() {
        return position;
    }
    /**
     * Dos vértices se consideran iguales si almacenan la misma información
     * (etiqueta). Esto facilita operaciones de búsqueda en estructuras que
     * no distinguen entre instancias físicas y lógicas.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof VertexObj<?, ?> v) {
            return this.info.equals(v.info);
        }
        return false;
    }
}

