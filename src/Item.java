public class Item {
	private String name;
	private double price;
	private int quantity;

	Item(String name, double price) {
		this.name = name;
		this.price = price;
		this.quantity = 1;
	}

	public String toString() {
		return this.name + " at $" + this.price + " x" + this.quantity;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public int incrementQuantity() {
		return quantity++;
	}

	public int decrementQuantity() {
		if(quantity == 1) return 0;
		return quantity--;
	}

	public double calculatePriceWithTax(double taxRate) {
		if(taxRate > 1.00 || taxRate < 0.00) {
			System.out.println("Invalid tax rate");
			return price;
		}
		return price * (1.00 + taxRate);
	}
}

