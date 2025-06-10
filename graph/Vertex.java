package graph;

import java.util.List;

import utils.listLinked;

public class Vertex <e>{
    private E data;
    protected ListLinked<Edge<E>> listAdj;

    public Vertex(E data){
        this.data = data;
        list.Adj = new ListLinked<Edge<E>>();

    }

    public E getData() {
        return data;
    }

    //getter

    public ListLinked<Edge<E>> getListAdj(){
        return listAdj;
    }



    public boolean equals (Object o) {
        if(o instanceof Vertex<?>){
            Vertex<?> v = (Vertex<?>)o;
            return this.data.equals(v.data);
        }   
        return false;

    }

    public String toString() {
        return this.data + " --> " + this.listAdj.toString() + "\n";
    }
}

