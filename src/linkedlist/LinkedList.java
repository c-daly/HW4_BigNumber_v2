package linkedlist;

import iterator.LinkedListIterator;
import iterator.interfaces.ILinkedListIterator;
import linkedlist.interfaces.ILinkedList;

public class LinkedList<T extends Comparable<T>> implements ILinkedList<T> {
    private Node<T> head;
    private T defaultValue = null;
    private int length = 0;
    //public LinkedList() {}
    public LinkedList(T initial_val) {
        head = new Node<T>(initial_val);
        defaultValue = initial_val;
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
    public void addToTail(T val) {
        Node<T> lastNode = getNodeAtIndex(length() - 1);
        Node<T> newNode = new Node<T>(val);
        lastNode.next = newNode;
        newNode.prev = lastNode;
    }

    @Override
    public T get(int index) {
        int x = 0;
        Node<T> tp = head;

        while (tp.next != null) {
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
        if (index >= length()) {
            throw new IllegalArgumentException();
        }

        if (index == 0) {
            removeHead();
            return;
        }

        if (index == length() - 1) {
            removeTail();
            return;
        }
        Node<T> nodeToRemove = getNodeAtIndex(index);
        Node<T> prevNode = nodeToRemove.prev;
        Node<T> nextNode = nodeToRemove.next;

        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }

    private void removeHead() {
        if(head != null && head.next != null) {
            head = head.next;
        } else {
            head = new Node<T>(defaultValue);
        }
    }

    private void removeTail() {
        Node<T> secondToLastNode = getNodeAtIndex(length() - 2);
        secondToLastNode.next = null;
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
