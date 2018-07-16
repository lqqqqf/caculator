package calculator1;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    private static NumberRegistry numberRegistry;

    @BeforeClass
    public static void init() {
        //初始化NumberRegistry
        numberRegistry = NumberRegistry.newInstance();
        numberRegistry.register("四", "4");
        numberRegistry.register("four", "4");
        numberRegistry.register("2", "2");
    }

    @Test
    public void testOffset() {
        Calculator c = new Calculator(numberRegistry);
        //测试初始化是否成功
        c.offset(12);
        assertThat(c.getResult(), is("12"));
        //测试二次初始化是否成功
        c.offset(22);
        assertThat(c.getResult(), is("22"));
        //测试经过运算以后，初始化是否成功
        c.plus(12).multiply("2").divide(4).minus(5);
        c.offset(3);
        assertThat(c.getResult(), is("3"));
    }

    @Test
    public void testOffset1() {
        Calculator c = new Calculator(numberRegistry);
        //测试初始化是否成功
        c.offset("12");
        assertThat(c.getResult(), is("12"));
        //测试二次初始化是否成功
        c.offset("22");
        assertThat(c.getResult(), is("22"));
        //测试中文数字初始化
        c.offset("四");
        assertThat(c.getResult(), is("4"));
        //测试大数初始化
        c.offset("999999999999999999999999999999999999999999");
        assertThat(c.getResult(), is("999999999999999999999999999999999999999999"));
        //测试经过运算以后，初始化是否成功
        c.plus(12).multiply("2").divide(4).minus(5).multiply("33333333333333333333333333333333333333");
        c.offset("3");
        assertThat(c.getResult(), is("3"));
    }

    @Test
    public void testPlus() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset(7).plus(5).getResult(), is("12"));
        assertThat(c.offset(6).plus(8).plus(10).getResult(), is("24"));
        c.reset();
        assertThat(c.plus(2).getResult(), is("2"));
    }

    @Test
    public void testPlus1() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset("1").plus("四").getResult(), is("5"));
        assertThat(c.offset("1").plus("四").plus("four").getResult(), is("9"));
        assertThat(c.offset("999999999999999999999999999999999999999991").plus("四").getResult(), is("999999999999999999999999999999999999999995"));
        c.reset();
        assertThat(c.plus("999999999999999999999999999999999999999991").getResult(), is("999999999999999999999999999999999999999991"));
    }

    @Test
    public void testPlus2() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.plus(cal -> cal.offset(2).multiply(3).getBigDecimalResult()).getResult(), is("6"));
        assertThat(c.offset(8).plus(cal -> cal.offset(4).multiply(3).getBigDecimalResult()).getResult(), is("2E+1"));
        assertThat(c.offset("四").plus(cal -> cal.offset(3).multiply(10).divide(2).getBigDecimalResult()).getResult(), is("19"));
        assertThat(c.offset("四").plus(cal -> cal.offset(3).multiply(10).divide(2).getBigDecimalResult()).plus(2).getResult(), is("21"));
    }

    @Test
    public void testPlusOfMixing() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.plus(1).plus("四").plus(7).plus("four").getResult(), is("16"));
    }

    @Test
    public void testMinus() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.minus(1).getResult(), is("-1"));
        assertThat(c.offset(5).minus(2).minus(1).getResult(), is("2"));
    }

    @Test
    public void testMinus1() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.minus("四").getResult(), is("-4"));
        assertThat(c.offset("1").minus("四").getResult(), is("-3"));
        assertThat(c.offset("90").minus("四").minus("four").getResult(), is("82"));
    }

    @Test
    public void testMinus2() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.minus(cal -> cal.offset(6).divide(3).getBigDecimalResult()).getResult(), is("-2"));
        assertThat(c.offset("四").minus(cal -> cal.offset(2).multiply(4).getBigDecimalResult()).getResult(), is("-4"));
        assertThat(c.offset("9999999999999999999999999999999999999999999991").minus(cal -> cal.offset(2).multiply(1).getBigDecimalResult()).minus("four").getResult(), is("9999999999999999999999999999999999999999999985"));
    }

    @Test
    public void testMinusOfMixing() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset(123).minus("1").minus("四").minus("four").minus("23").getResult(), is("91"));
    }

    @Test
    public void testMultiply() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset(3).multiply(4).getResult(), is("12"));
        assertThat(c.offset(2).multiply(5).multiply(6).getResult(), is("6E+1"));
    }

    @Test
    public void testMultiply1() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset("四").multiply("four").getResult(), is("16"));
        assertThat(c.offset("12").multiply("four").multiply("四").getResult(), is("192"));
    }

    @Test
    public void testMultiply2() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset(12).multiply(cal -> cal.offset(4).plus(2).getBigDecimalResult()).getResult(), is("72"));
        assertThat(c.offset("四").multiply(cal -> cal.offset(3).minus(1).getBigDecimalResult()).getResult(), is("8"));
        assertThat(c.offset("four").multiply(cal -> cal.offset(2).plus(3).getBigDecimalResult()).multiply("3").getResult(), is("6E+1"));
    }

    @Test
    public void testMultiplyOfMixing() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset(2).multiply("3").multiply("四").multiply("1000000000000000000000000000000000000000000000").getResult(), is("2.4E+46"));
    }

    @Test
    public void testDivide() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset(8).divide(2).getResult(), is("4"));
        assertThat(c.offset(16).divide(4).divide(3).getResult(), is("1.333"));
    }

    @Test
    public void testDivide1() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset("four").divide("2").getResult(), is("2"));
        assertThat(c.offset("四").divide("3").divide("2").getResult(), is("0.666"));
    }

    @Test
    public void testDivide2() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset(18).divide(cal -> cal.offset(2).plus("4").getBigDecimalResult()).getResult(), is("3"));
        assertThat(c.offset("four").divide(cal -> cal.offset(5).minus(3).getBigDecimalResult()).getResult(), is("2"));
        assertThat(c.offset("四").divide(cal -> cal.offset(3).plus(2).getBigDecimalResult()).divide("2").getResult(), is("0.4"));
    }

    @Test
    public void testDivideOfMixing() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset("9").divide("four").divide(2).getResult(), is("1.125"));
    }

    @Test
    public void testGetBigDecimalResult() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset("四").plus(5).getBigDecimalResult(), equalTo(new BigDecimal(9)));
        assertThat(c.offset("four").plus(2).getBigDecimalResult(), equalTo(new BigDecimal(6)));
        assertThat(c.offset("2").minus("1").getBigDecimalResult(), equalTo(new BigDecimal(1)));
        assertThat(c.offset("four").plus(cal -> cal.offset(1d).multiply("2").multiply("four").getBigDecimalResult()).getBigDecimalResult(), equalTo(new BigDecimal(12)));
        assertThat(c.offset("four").divide(2).getBigDecimalResult(), equalTo(new BigDecimal(2)));
        assertThat(c.offset(12).divide(cal -> cal.offset(1).plus(2).getBigDecimalResult()).multiply(5).getBigDecimalResult(), equalTo(new BigDecimal("2E+1")));

    }

    @Test
    public void testResult() {
        Calculator c = new Calculator(numberRegistry);
        assertThat(c.offset("四").plus(5).getResult(), is("9"));
        assertThat(c.offset("four").plus(2).getResult(), is("6"));
        assertThat(c.offset("2").minus("1").getResult(), is("1"));
        assertThat(c.offset("four").plus(cal -> cal.offset(1d).multiply("2").multiply("four").getBigDecimalResult()).getResult(), is("12"));
        assertThat(c.offset("four").divide(2).getResult(), is("2"));
        assertThat(c.offset(12).divide(cal -> cal.offset(1).plus(2).getBigDecimalResult()).multiply(5).getResult(), is("2E+1"));
    }

    @Test
    public void testReset() {
        Calculator c = new Calculator(numberRegistry);
        c.offset(12);
        c.reset();
        assertThat(c.getResult(), is("0"));
        c.offset(3).minus(3).plus(4).divide(9);
        c.reset();
        assertThat(c.getResult(), is("0"));
    }
}
