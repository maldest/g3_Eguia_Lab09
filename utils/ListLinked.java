package utils;

public class listLinked {
    private Node<T> head;

    public ListLinked(){
        head = null;
    }

    public void insert(T data){
        Node <T> newNode = new Node<>(data);
        if (head == null) head = newNode;
        else{
            Node<T> aux = head;
            while (aux.getNext() != null) aux = aux.getNext();
            aux.setNext(newNode);
        }
    }

    public int search(T data){
        Node<T> aux = head;
        int pos = 0;
        while (aux != null){
            if (aux.getData().equals(data)) return pos;
            aux = aux.getNext();
            pos++;
        }
        return -1
    }
    public T get(int pos){
        Node<T> aux = head;
        int i = 0;
        while
    }
}
