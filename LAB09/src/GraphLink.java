public class GraphLink <E>{
    protected ListLinkd<Vertex<E>> listVertex;

    public GrapLink(){
        listVertex = new ListLinked <Vertex<E>>();
    }

    public void insertVertex(E data){
        //metodo
    }

    pubilc void insertEdge(E verOri, E verDes){
        //metodo
    }

    public String toString(){
        return this.listVertex.toString();
    }
    
}
