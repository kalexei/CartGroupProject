interface ItemInterface {
	String getName();
	void setName(String name);
	double getPrice();
	void setPrice(double price);
}

abstract class Item {
	private String name;
	private double price;

	Item(String name, double price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.name + " at $" + this.price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

public class CartItem extends Item implements ItemInterface {
	private boolean withTax;

	CartItem(String name, double price, boolean withTax) {
		super(name, price);
		this.withTax = withTax;
	}

	public void setWithTax(boolean withTax) {
		this.withTax = withTax;
	}

	public boolean hasBeenTaxed() {
		return withTax;
	}

	public double calculatePriceWithTax(double taxRate) {
		if(this.hasBeenTaxed()) {
			return this.getPrice();
		}
		if(taxRate > 100 || taxRate < 0) {
			throw new IllegalArgumentException("Tax rate must be between 100 and 0");
		}
		return this.getPrice() * (1.00 + taxRate / 100);
	}
}

