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
        BigNumber num = new BigNumberImpl("87099998372888190123456789");
        System.out.println(num);
        assertEquals("87099998372888190123456789", num.toString());
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
        System.out.println(num.toString());
        assertEquals(5, num.getDigitAt(0));
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