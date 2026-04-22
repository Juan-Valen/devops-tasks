package localization_shop.modelTest;

import org.junit.jupiter.api.Test;

import localization_shop.model.Calculator;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testInitialization() {
        Calculator calc = new Calculator();
        assertTrue(calc instanceof Calculator);
    }

    @Test
    void testValidPriceInput() {
        Calculator calc = new Calculator();
        Calculator.PriceResult result = calc.getCurrentItemPrice("12.5");

        assertTrue(result.isValid());
        assertFalse(result.isZero());
    }

    @Test
    void testZeroPriceInput() {
        Calculator calc = new Calculator();
        Calculator.PriceResult result = calc.getCurrentItemPrice("0");

        assertTrue(result.isValid());
        assertTrue(result.isZero());

    }

    @Test
    void testInvalidPriceInput() {
        Calculator calc = new Calculator();
        Calculator.PriceResult result = calc.getCurrentItemPrice("abc");

        assertFalse(result.isValid());
        assertFalse(result.isZero());

    }

    @Test
    void testValidQuantityUpdatesTotal() {
        Calculator calc = new Calculator();
        calc.getCurrentItemPrice("10.0");
        Calculator.QuantityResult result = calc.getCurrentItemQuantity("3");

        assertTrue(result.isValid());
        assertFalse(result.isZero());
        assertEquals(30.0, calc.getTotal());
    }

    @Test
    void testZeroQuantity() {
        Calculator calc = new Calculator();
        calc.getCurrentItemPrice("5.0");
        Calculator.QuantityResult result = calc.getCurrentItemQuantity("0");

        assertTrue(result.isValid());
        assertTrue(result.isZero());
        assertEquals(0.0, calc.getTotal());

    }

    @Test
    void testInvalidQuantityInput() {
        Calculator calc = new Calculator();
        Calculator.QuantityResult result = calc.getCurrentItemQuantity("xyz");

        assertFalse(result.isValid());
        assertFalse(result.isZero());
        assertEquals(0.0, calc.getTotal());
    }

    @Test
    void testMultipleItemsAccumulateTotal() {
        Calculator calc = new Calculator();
        calc.getCurrentItemPrice("10");
        calc.getCurrentItemQuantity("2");

        calc.getCurrentItemPrice("4.5");
        calc.getCurrentItemQuantity("3");

        assertEquals(33.5, calc.getTotal());
    }

}
