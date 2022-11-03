package linkedlist.interfaces;

import iterator.interfaces.ILinkedListIterator;

public interface ILinkedList<T extends Comparable<T>> extends Iterable<T> {
    int length();
    void add(T val);
    void addToHead(T val);
    T get(int index);
    void set(int index, T value);
    void remove(int index);
    ILinkedListIterator iterator();
}
