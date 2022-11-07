package bignumber;

import org.junit.Test;

import static org.junit.Assert.*;

public class BigNumberImplTest {

    @Test
    public void testParameterlessConstructor() {
        BigNumber num = new BigNumberImpl();
        assertEquals("0", num.toString());
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
        num.shiftLeft(1);
        num.addDigit(1);
        num.shiftLeft(9);
        assertEquals(num.toString().length(), num.length());
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
        BigNumber num = new BigNumberImpl();
        num.shiftLeft(1);
        num.addDigit(1);
        num.shiftLeft(9);
        num.shiftRight(10);
        assertEquals("0", num.toString());

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
        num.addDigit(9);
        num.addDigit(9);
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
        BigNumber num1 = new BigNumberImpl("1009");
        BigNumber num2 = new BigNumberImpl("111");
        BigNumber result = num1.add(num2);
        assertEquals("1120", result.toString());
    }

    @Test
    public void bigAdd() {
        BigNumber rbg1 = new BigNumberImpl("43921880107207149000769734266774083098703674408814065288726326279157360324269541663242371876261081059745479587499259824567587823117008782029646251306215100191505281246609416538754867411283084707235627112017676905031196641486012427315884971334963985674627896189934847038098035798677851143883966740745084347570518000415210222525190");
        BigNumber rbg2 = new BigNumberImpl("442202177515521891284577969870508550820850727786317155310986557926519301696956697928982926588256470584861886316036727074296975494023870005462400465931535561384317661486958195623546192554152114462382997642012343241427430360562569848243128696652814232794646871905058217590570903364098487139182765863462466581464609740819406771879246572014421039834257367809815649009794941070967981164467187724819802008683512632731607055667234835090623879590455179059205573123295331897415522869901071892719437351468611800550496314142536775347364727644908622706624790048694510995204841254177025462803619647949141839455904738438161016843");
        BigNumber result = rbg1.add(rbg2);
        System.out.println(result.toString());
    }
    @Test
    public void compareTo() {
        BigNumber rbg1 = new BigNumberImpl("439218801072071490007697342667740830987036744088140652887263262791573603242695416632423718762610810597454795874992598245675878231170087820296462513062151001915052812466094165387548674112830847072356271120176769050311966414860124273158849713349639856746278961899348470380980357986778511438839667407450843475705180004152102225251906");
        BigNumber rbg2 = new BigNumberImpl("422021775155218912845779698705085508208507277863171553109865579265193016969566979289829265882564705848618863160367270742969754940238700054624004659315355613843176614869581956235461925541521144623829976420123432414274303605625698482431286966528142327946468719050582175905709033640984871391827658634624665814646097408194067718792465720144210398342573678098156490097949410709679811644671877248198020086835126327316070556672348350906238795904551790592055731232953318974155228699010718927194373514686118005504963141425367753473647276449086227066247900486945109952048412541770254628036196479491418394559047384381610168436741290401682924075205841007277699295091749941282081428940696065796955768681282962093600948989714686524180325441302349677404301178784");
        int x = rbg1.compareTo(rbg2);

        System.out.println(x);
    }
}