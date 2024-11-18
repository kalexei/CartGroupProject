public class TaxCalculator {
    public static double calculateTotalPriceWithTax(Cart cart, double taxRate) {
        if(taxRate > 1.00 || taxRate < 0.00) {
            System.out.println("Invalid tax rate");
            return 0;
        }
        double total = 0;

        for (Item item: cart.getCartItems()) {
            total += item.calculatePriceWithTax(taxRate);
        }
        return total;
    }
}
