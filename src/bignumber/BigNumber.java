package bignumber;

public interface BigNumber extends Comparable<BigNumber> {
    int length();
    void shiftLeft(int num_shifts);
    void shiftRight(int num_shifts);
    void addDigit(int digit);
    int getDigitAt(int position);
    BigNumber copy();
    BigNumber add(BigNumber number);


}
