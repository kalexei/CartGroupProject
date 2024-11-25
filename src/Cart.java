abstract class CartTemplate {
	private CartItem[] items = new CartItem[20];

	CartTemplate() {}

	public CartItem[] getCartItems() {
		return items;
	}

	public CartItem addItem(CartItem newItem) {
		for (int i = 0; i < items.length; i++) {
			if(items[i] != null) continue;
			items[i] = newItem;
			return items[i];
		}
		throw new ArrayIndexOutOfBoundsException("Cart is full");
	}

	public void clearCart() {
		items = new CartItem[20];
	}

	public double calculateTotal() {
		double total = 0;

		for (CartItem item: this.getCartItems()) {
			if(item == null) continue;
			total += item.getPrice();
		}

		return total;
	}

	public double calculateTotal(double taxRate) {
		double total = 0;

		for (CartItem item: this.getCartItems()) {
			if(item == null) continue;
			total += item.calculatePriceWithTax(taxRate);
		}

		return total;
	}
}

public class Cart extends CartTemplate {}
