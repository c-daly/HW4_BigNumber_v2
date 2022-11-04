package iterator.interfaces;

import java.util.Iterator;

public interface ILinkedListIterator<T> extends Iterator<T> {

    void set(T value);
    T prev();
    T currentValue();
    boolean hasPrev();
}
