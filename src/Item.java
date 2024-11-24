public class Item {
	private String name;
	private double price;
	private boolean withTax;

	Item(String name, double price, boolean withTax) {
		this.name = name;
		this.price = price;
		this.withTax = withTax;
	}

	public String toString() {
		return this.name + " at $" + this.price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public boolean hasBeenTaxed() {
		return withTax;
	}

	public double calculatePriceWithTax(double taxRate) {
		if(this.hasBeenTaxed()) {
			return price;
		}
		if(taxRate > 100 || taxRate < 0) {
			throw new IllegalArgumentException("Tax rate must be between 100 and 0");
		}
		return price * (1.00 + taxRate / 100);
	}
}

