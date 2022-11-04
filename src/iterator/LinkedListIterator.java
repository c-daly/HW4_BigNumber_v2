package iterator;

import iterator.interfaces.ILinkedListIterator;
import linkedlist.Node;

public class LinkedListIterator<T> implements ILinkedListIterator<T> {
    private Node<T> head;
    private Node<T> current;

    public LinkedListIterator(Node<T> head) {
        if(head == null) {
            throw new IllegalArgumentException();
        }
        this.head = head;
        this.current = head;
    }
    @Override
    public boolean hasNext() {
        if (current == null) {
            return false;
        }
        return(current.next != null && current.next.value != null);
    }

    public T currentValue() {
        if(current != null) {
            return current.value;
        }
        throw new IllegalStateException();
    }
    @Override
    public T next() {
        T data = current.value;
        current = current.next;
        return data;
    }

    @Override
    public T prev() {
        T data = current.value;
        current = current.prev;
        return data;
    }
    @Override
    public boolean hasPrev() {
        return current.prev != null;
    }

    @Override
    public void set(T value) {
       current.value = value;
    }
}
