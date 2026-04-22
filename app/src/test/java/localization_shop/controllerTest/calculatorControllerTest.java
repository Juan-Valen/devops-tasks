package localization_shop.controllerTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import localization_shop.controller.CalculatorController;

class CalculatorTest {

    @Test
    void testInitialization() {
        CalculatorController calc = new CalculatorController();
        assertTrue(calc instanceof CalculatorController);
    }
}
