package localization_shop.model;

public class Calculator {
    private double total = 0;
    private double currentValue = -1;

    public record PriceResult(boolean isValid, boolean isZero) {}

    public record QuantityResult(boolean isValid, boolean isZero) {}

    public PriceResult getCurrentItemPrice(String input) {
        try {
            double enteredValue = Double.parseDouble(input);
            if (enteredValue > 0) {
                currentValue = enteredValue;
            }
            return new PriceResult(enteredValue >= 0, enteredValue == 0);
        } catch (NumberFormatException e) {
            return new PriceResult(false, false);
        }
    }

    public QuantityResult getCurrentItemQuantity(String input) {
        try {
            int enteredValue = Integer.parseInt(input);
            if (enteredValue > 0) {
                total += currentValue * enteredValue;
                currentValue = 0;
            }
            return new QuantityResult(enteredValue >= 0, enteredValue == 0);
        } catch (NumberFormatException e) {
            return new QuantityResult(false, false);
        }
    }

    public double getTotal() {
        return total;
    }
}
