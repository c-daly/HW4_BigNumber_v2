package linkedlist;

import iterator.LinkedListIterator;
import iterator.interfaces.ILinkedListIterator;
import linkedlist.interfaces.ILinkedList;

public class LinkedList<T extends Comparable<T>> implements ILinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private T defaultValue = null;
    private int length = 0;
    //public LinkedList() {}
    public LinkedList(T initial_val) {
        head = new Node<T>(initial_val);
        tail = head;
        defaultValue = initial_val;
        length = 1;
    }

    @Override
    public int length() {
        return length;
        //int length = 1;
        //if (head == null) {
        //    return 0;
        //}

        //Node<T> tp = head;
        //while (tp.next != null) {
        //    tp = tp.next;
        //    length++;
        //}
    }

    @Override
    public void add(T val) {
        if (head == null) {
            addToHead(val);
            tail = head;
        } else {
            //Node<T> tp = head;
            //while (tp.next != null) {
            //    tp = tp.next;
            //}
            tail.next = new Node<T>(val);
            tail = tail.next;
        }
        length += 1;
    }

    public void addToHead(T val) {
        if (head == null) {
            head = new Node<T>(val);
            tail = head;
        } else {
            Node<T> newHead = new Node<T>(val);
            newHead.next = head;
            head = newHead;
        }
        length += 1;
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
        if(index == 0) {
            head.value = value;
        } else {
            if (index < length()) {
                Node<T> tp = getNodeAtIndex(index);
                tp.value = value;
            }
        }
   }

    @Override
    public void remove(int index) {

        if (index == 0) {
            removeHead();
            length -= 1;
            return;
        }
        if (index >= length()) {
            throw new IllegalArgumentException();
        }

        if (index == length() - 1) {
            removeTail();
            length -= 1;
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
        length -= 1;
    }

    private void removeTail() {
        Node<T> secondToLastNode = getNodeAtIndex(length() - 2);
        secondToLastNode.next = null;
        tail = secondToLastNode;
        length -= 1;
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
            tp = tp.next;
            x++;
        }
        return tp;
    }
}
