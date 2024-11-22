public class TaxCalculator {
    public static double calculateTotalPriceWithTax(Cart cart, double taxRate) {
        if(taxRate > 1.00 || taxRate < 0.00) {
            System.out.println("Invalid tax rate");
            return -1;
        }
        return 0;
    }
}
