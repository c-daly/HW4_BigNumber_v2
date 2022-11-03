package bignumber;

import iterator.interfaces.ILinkedListIterator;
import linkedlist.LinkedList;
import linkedlist.interfaces.ILinkedList;

public class BigNumberImpl implements BigNumber {
    private ILinkedList<Integer> digits = new LinkedList<Integer>();

    public BigNumberImpl() {
        digits.add(0);
    }

    public BigNumberImpl(String number) {
        this.digits = new LinkedList<Integer>();

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
        this.digits = new LinkedList<Integer>();
        digits.add(0);

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
        return digits.length();
    }

    @Override
    public void shiftLeft(int num_shifts) {
        //if (digitsEqualZero()) {
        //    return;
        //}
        if (num_shifts < 0) {
            //shiftRight(num_shifts * -1);
            return;
        } else {
            for (int x = 0; x < num_shifts; x++) {
                digits.addToHead(0);
            }
        }
    }

    @Override
    public void shiftRight(int num_shifts) {

    }

    @Override
    public void addDigit(int digit) {
        ILinkedListIterator<Integer> it = digits.iterator();

        int tempTotal = digit + getDigitAt(0);

        if (tempTotal > 9) {
            it.set(0);
            handleCarry(it);
        } else {
            it.set(tempTotal);
        }
    }

    private void handleCarry(ILinkedListIterator<Integer> it) {
        int tempTotal = it.prev();
        if (tempTotal > 9) {
            it.set(0);
            handleCarry(it);
        } else {
            it.set(tempTotal);
        }
    }

    @Override
    public int getDigitAt(int position) {
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
        for (int x = 0; x <= longestLength; x++) {
            int x1 = x < digits.length() ? getDigitAt(x) : 0;
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
        return this.toString().compareTo(o.toString());
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
            if (it.next() > 0) {
                return false;
            }
        }
        return true;
    }
}
