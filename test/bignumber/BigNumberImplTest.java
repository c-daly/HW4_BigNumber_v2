package bignumber;

import org.junit.Test;

import static org.junit.Assert.*;

public class BigNumberImplTest {

    @Test
    public void testParameterlessConstructor() {
        BigNumber num = new BigNumberImpl();
    }

    @Test
    public void testStringConstructor() {
        BigNumber num = new BigNumberImpl("1000000000");
        System.out.println(num);
        assertEquals("1000000000", num.toString());
    }

    @Test
    public void testToString() {
        BigNumber num = new BigNumberImpl();
        num.shiftLeft(1);
        num.addDigit(5);
        assertEquals("5", num.toString());
    }
    @Test
    public void length() {
        BigNumber num = new BigNumberImpl();
        assertEquals(1, num.length());
    }

    @Test
    public void shiftLeft() {
       BigNumber num = new BigNumberImpl();
       num.shiftLeft(1);
       num.addDigit(1);
       num.shiftLeft(9);
       assertEquals("1000000000", num.toString());
    }

    @Test
    public void shiftRight() {
    }

    @Test
    public void testShiftLeftOnZero() {
        BigNumber num = new BigNumberImpl();
        num.shiftLeft(9);
        assertEquals("0", num.toString());
    }
    @Test
    public void addDigit() {
        BigNumber num = new BigNumberImpl();
        num.shiftLeft(1);
        num.addDigit(4);
    }

    @Test
    public void getDigitAt() {
        BigNumber num = new BigNumberImpl();
        num.shiftLeft(1);
        num.addDigit(4);
        num.shiftLeft(1);
        num.addDigit(5);
        num.shiftLeft(1);
        num.addDigit(6);
        num.shiftLeft(1);
        num.addDigit(7);
        num.shiftLeft(1);
        num.addDigit(8);
        num.shiftLeft(1);
        num.addDigit(9);
        System.out.println(num.toString());
        assertEquals(7, num.getDigitAt(2));
    }

    @Test
    public void copy() {
    }

    @Test
    public void add() {
        BigNumber num1 = new BigNumberImpl("1000");
        BigNumber num2 = new BigNumberImpl("111");
        BigNumber result = num1.add(num2);
        assertEquals("1111", result.toString());
    }

    @Test
    public void compareTo() {
    }
}