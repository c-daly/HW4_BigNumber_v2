package linkedlist;

import iterator.LinkedListIterator;
import iterator.interfaces.ILinkedListIterator;
import linkedlist.interfaces.ILinkedList;

public class LinkedList<T extends Comparable<T>> implements ILinkedList<T> {
    private Node<T> head;

    public LinkedList() {
    }

    @Override
    public int length() {
        int length = 1;
        if (head == null) {
            return 0;
        }

        Node<T> tp = head;
        while (tp.next != null) {
            tp = tp.next;
            length++;
        }


        return length;
    }

    @Override
    public void add(T val) {
        if (head == null) {
            addToHead(val);
        } else {
            Node<T> tp = head;
            while (tp.next != null) {
                tp = tp.next;
            }
            tp.next = new Node<T>(val);
        }

    }

    public void addToHead(T val) {
        if (head == null) {
            head = new Node<T>(val);
        } else {
            Node<T> newHead = new Node<T>(val);
            newHead.next = head;
            head = newHead;
        }
    }

    @Override
    public T get(int index) {
        int x = 0;
        Node<T> tp = head;

        while (tp != null) {
            if (x == index) {
                return tp.value;
            }
            x += 1;
            tp = tp.next;
        }
        return tp.value;
    }

    @Override
    public void set(int index, T value) {
        Node<T> tp = head;
        int x = 0;
        while (tp.next != null) {
            if (x == index) {
                tp.value = value;
            }
            tp = tp.next;
            x++;
        }
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public ILinkedListIterator<T> iterator() {
        return new LinkedListIterator(head);
    }

    private Node<T> getNodeAtIndex(int index) {
        Node<T> tp = head;
        int x = 0;
        while (tp.next != null) {
            if (x == index) {
               return tp;
            }
            x++;
        }
        return tp;
    }
}
