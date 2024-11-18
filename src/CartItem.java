public class CartItem extends Item {
	CartItem(String name, int price) {
		super(name, price);
	}

	int quantity = 0;

	public int incrementItemQuantity() {
		return quantity++;
	}

	public int decrementItemQuantity() {
		return quantity--;
	}
}