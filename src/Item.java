public class Item {
	private String name;
	private int price;

	Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public double calculatePriceWithTax(double taxRate) {
		if(taxRate > 1.00 || taxRate < 0.00) {
			System.out.println("Invalid tax rate");
			return price;
		}
		return price * (1.00 + taxRate);
	}

	public boolean equalsItem(Item item) {
		return item.getName().equals(this.name) && item.getPrice() == this.price;
	}
}

