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


	public double calculatePriceWithTax(double taxRate) {
		if(taxRate > 1.00 || taxRate < 0.00) {
			System.out.println("Invalid tax rate");
			return -1;
		}
		return price * (1.00 + taxRate);
	}
}

