package linkedlist;

public class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node() {

    }
    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node next) {
        this.value = value;
        this.next = next;
    }
}
