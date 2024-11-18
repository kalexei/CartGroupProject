public class Cart {
	private static CartItem[] items = new CartItem[20];

	public static CartItem addItem(Item newItem, int quantity) {
		for (int i = 0; i < items.length; i++) {
			if(items[i] != null) continue;
			items[i] = (CartItem) newItem;
			items[i].quantity = quantity;
			return items[i];
		}
		return null;
	}

	public int incrementQuantity(Item item) {
		for (int i = 0; i < items.length; i++) {
			if(items[i].equalsItem(item)) {
				return items[i].incrementItemQuantity();
			}
		}
		return 0;
	}

	public int decrementQuantity(Item item) {
		for (int i = 0; i < items.length; i++) {
			if(items[i].equalsItem(item)) {
				return items[i].decrementItemQuantity();
			}
		}
		return 0;
	}
}
