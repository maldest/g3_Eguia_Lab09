package graph;

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
    
    
    @Override
    public String toString() {
        return this.listVertex.toString();
    }
}

