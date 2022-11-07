package bignumber;

import iterator.interfaces.ILinkedListIterator;

public interface BigNumber extends Comparable<BigNumber> {
    int length();
    void set(int index, int value);
    void shiftLeft(int num_shifts);
    void shiftLeftForCarry(int num_shifts);
    void shiftRight(int num_shifts);
    void addDigit(int digit);
    void addToTail(int digit);
    int addDigitWithCarry(int digit);
    int getDigitAt(int position);
    BigNumber copy();
    BigNumber add(BigNumber number);
    ILinkedListIterator<Integer> getIterator();


}
