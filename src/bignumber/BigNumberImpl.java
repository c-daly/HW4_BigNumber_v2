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
        return digits.length();
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
    public void shiftLeftForCarry(int num_shifts) {
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
    public void set(int index, int value) {
        digits.set(index, value);
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
        int tempTotal = digit + getDigitAt(0);

        ILinkedListIterator<Integer> it = digits.iterator();

        if (tempTotal > 9) {
            it.set(tempTotal - 10);
            return(1);
        } else {
            it.set(tempTotal);
            return 0;
        }
    }
    @Override
    public void addToTail(int value) {
        digits.add(value);
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
            digits.add(1);
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
        ILinkedListIterator<Integer> it1 = this.getIterator();
        ILinkedListIterator<Integer> it2 = number.getIterator();
        int sum = 0;

        BigNumber newNumber = new BigNumberImpl();

        int x = 0;
        //while (it1.hasNext() || it2.hasNext()) {
        while(x < this.length() || x < number.length()) {
            int it1_value = x < this.length() ? this.getDigitAt(x) : 0;
            int it2_value = x < number.length() ? number.getDigitAt(x) : 0;
            int carry_value = newNumber.length() > x ? newNumber.getDigitAt(x) : 0;

            sum = it1_value + it2_value + carry_value;
            if (sum > 9) {
                if (x < newNumber.length()) {
                    newNumber.set(x, sum - 10);
                    if (x + 1 < newNumber.length()) {
                        newNumber.set(x + 1, 1);
                    } else {
                        newNumber.addToTail(1);
                    }
                } else {
                    newNumber.addToTail(sum - 10);
                    newNumber.addToTail(1);
                }
                //newNumber.set(x, 1);
                //newNumber.shiftLeftForCarry(1);
                //newNumber.set(x, sum - 10);
            } else {
                if (x > newNumber.length() - 1) {
                    newNumber.addToTail(sum);
                } else {
                    newNumber.set(x, sum);
                }
            }
            x++;
        }

        return newNumber;
    }
    //@Override
    public BigNumber add2(BigNumber number) {
        BigNumber newNumber = new BigNumberImpl();
        int index = 0;
        int carry = 0;
        int newDigit;
        int x3 = 0;
        int longestLength = length() > number.length() ? length() : number.length();
        for (int x = 0; x < longestLength; x++) {
            newNumber.shiftLeft(1);
            int x1 = x < length() ? getDigitAt(x) : 0;
            int x2 = x < number.length() ? number.getDigitAt(x) : 0;
            //int x3 = newNumber.length() > x ? newNumber.getDigitAt(x) : 0;
            if (x1 + x2 + x3 > 9) {
                int sum = (x1 + x2 + x3) - 10;
                newNumber.set(x, sum);
                x3 = 1;
            } else {
                newNumber.set(x, x1 + x2 + x3);
                x3 = 0;
            }
        }

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

    public ILinkedListIterator<Integer> getIterator() {
        return digits.iterator();
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
