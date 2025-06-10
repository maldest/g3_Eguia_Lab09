package graph;

import graph.Vertex;
import graph.Edge;
import utils.ListLinked;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    public void insertVertex(E data){
        //metodo
        Vertex<E> = new Vertex<>(data);
        if (listVertex.search(v) == -1){
            listVertex.insert(v);
        }
    }

    public void insertEdge(E verOri, E verDes){
        //metodo
        Vertex <E> v0ri = list.Vertex.get(listVertex.search(New Vertex<>(verOri)));
        Vertex <E> v0ri = list.Vertex.get(listVertex.search(New Vertex<>(verDes)));

        if (vOri != null && vDes != null){
            vOri.getListAdj().insert(new Edge<>(vDes));
            vDes.getListAdj().insert(new Edge<> (vOri));


        }
    }

    public String toString(){
        return this.listVertex.toString();
    }
    
}
