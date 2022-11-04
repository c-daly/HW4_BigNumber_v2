package bignumber;

import iterator.interfaces.ILinkedListIterator;
import linkedlist.LinkedList;
import linkedlist.interfaces.ILinkedList;

public class BigNumberImpl implements BigNumber {
    private ILinkedList<Integer> digits = new LinkedList<Integer>(0);
    private int length = 1;
    public BigNumberImpl() {
    }

    public BigNumberImpl(String number) {
        //this.digits = new LinkedList<Integer>(0);
        for (int x = 0; x < number.length(); x++) {
            int i = Character.getNumericValue(number.charAt(x));
            if (i < 0 || i > 9) {
                throw new IllegalArgumentException();
            }
            shiftLeft(1);
            addDigit(i);
        }
    }

    public BigNumberImpl(BigNumber num) {
        String number = num.toString();
        this.digits = new LinkedList<Integer>(0);

        for (int x = 0; x < number.length(); x++) {
            int i = Character.getNumericValue(number.charAt(x));
            if (i < 0 || i > 9) {
                throw new IllegalArgumentException();
            }
            shiftLeft(1);
            addDigit(i);
        }
    }

    @Override
    public int length() {
        //return digits.length();
        //return toString().length();
        return length;
    }

    @Override
    public void shiftLeft(int num_shifts) {
        if (digitsEqualZero()) {
            return;
        }
        if (num_shifts < 0) {
            shiftRight(num_shifts * -1);
            return;
        } else {
            for (int x = 0; x < num_shifts; x++) {
                digits.addToHead(0);
                length++;
            }
        }
    }

    @Override
    public void shiftRight(int num_shifts) {
        if (digitsEqualZero()) {
            return;
        }
        if(num_shifts < 0) {
            shiftLeft(num_shifts * -1);
            return;
        } else {
            for (int x = 0; x < num_shifts; x++) {
                digits.remove(0);
                length--;
                //if(length == 0) {
                //    digits.add(0);
                //}
            }
        }
    }

    @Override
    public void addDigit(int digit) {
        if(digit < 0 || digit > 9) {
            throw new IllegalArgumentException();
        }
        ILinkedListIterator<Integer> it = digits.iterator();

        int tempTotal = digit + getDigitAt(0);

        if (tempTotal > 9) {
            it.set(tempTotal - 10);
            handleCarry(it);
        } else {
            it.set(tempTotal);
        }
    }
    @Override
    public int addDigitWithCarry(int digit) {
        if(digit < 0 || digit > 9) {
            throw new IllegalArgumentException();
        }
        ILinkedListIterator<Integer> it = digits.iterator();

        int tempTotal = digit + getDigitAt(0);

        if (tempTotal > 9) {
            it.set(tempTotal - 10);
            return(1);
        } else {
            it.set(tempTotal);
            return 0;
        }
    }

    private void handleCarry(ILinkedListIterator<Integer> it) {
        int tempTotal = 0;
        if(it.hasPrev()) {
            tempTotal = it.prev();
            if (tempTotal > 9) {
                it.set(tempTotal - 10);
                handleCarry(it);
            } else {
                it.set(tempTotal);
            }
        } else {
            digits.addToTail(1);
            length++;
        }
    }

    @Override
    public int getDigitAt(int position) {
        if(position < 0 || position >= length()) {
            throw new IllegalArgumentException();
        }
        return digits.get(position);
    }

    @Override
    public BigNumber copy() {
        return new BigNumberImpl(this);
    }

    @Override
    public BigNumber add(BigNumber number) {
        BigNumber newNumber = new BigNumberImpl();
        int index = 0;
        int newDigit;
        int longestLength = length() > number.length() ? length() : number.length();
        for (int x = 0; x < longestLength; x++) {
            int x1 = x < length() ? getDigitAt(x) : 0;
            int x2 = x < number.length() ? number.getDigitAt(x) : 0;
            newNumber.shiftLeft(1);
            newNumber.addDigit(x1);
            newNumber.addDigit(x2);
        }
        //for (int digit : digits) {
        //    newDigit = digit + getDigitAt(index);
        //    newNumber.shiftLeft(1);
        //    newNumber.addDigit(newDigit);
        //    index++;
        //}

        return newNumber;
    }

    @Override
    public int compareTo(BigNumber o) {
        if(this.toString().compareTo(o.toString()) == 0) {
            return 0;
        }

        if(this.toString().length() > o.toString().length()) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ILinkedListIterator<Integer> it = digits.iterator();
        while (it.hasNext()) {
            Integer x = it.next();
            sb.append(x.toString());
        }
        sb.append(it.currentValue().toString());
        return sb.reverse().toString();

    }

    private boolean digitsEqualZero() {
        ILinkedListIterator<Integer> it = digits.iterator();
        if(it.currentValue() > 0) {
            return false;
        }
        while (it.hasNext()) {
            if (it.next() > 0 || it.currentValue() > 0) {
                return false;
            }
        }
        return true;
    }
}
