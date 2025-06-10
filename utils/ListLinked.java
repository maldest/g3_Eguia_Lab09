package utils;

public class ListLinked<T> {
    private Node<T> head;

    public ListLinked() {
        this.head = null;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) head = newNode;
        else {
            Node<T> aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
        }
    }

    public int search(T data) {
        Node<T> aux = head;
        int pos = 0;
        while (aux != null) {
            if (aux.getData().equals(data)) {
                return pos;
            }
            aux = aux.getNext();
            pos++;
        }
        return -1;
    }

    public T get(int pos) {
        Node<T> aux = head;
        int i = 0;
        while (aux != null) {
            if (i == pos) return aux.getData();
            aux = aux.getNext();
            i++;
        }
        return null;
    }

    public boolean remove(T data) {
        if (head == null) return false;

        if (head.getData().equals(data)) {
            head = head.getNext();
            return true;
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> aux = head;
        while (aux != null) {
            sb.append(aux.getData()).append(" ");
            aux = aux.getNext();
        }
        return sb.toString();
    }
}
