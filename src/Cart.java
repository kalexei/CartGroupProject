public class Cart {
	private Item[] items = new Item[20];

	Cart() {}

	public Item[] getCartItems() {
		return items;
	}

	public Item addItem(Item newItem) {
		for (int i = 0; i < items.length; i++) {
			if(items[i] != null) continue;
			items[i] = newItem;
			return items[i];
		}
		return null;
	}

	public void clearCart() {
		items = new Item[20];
	}

	public int incrementQuantity(Item item) {
		for (int i = 0; i < items.length; i++) {
			if(items[i].equals(item)) {
				return items[i].incrementQuantity();
			}
		}
		return 0;
	}

	public int decrementQuantity(Item item) {
		for (int i = 0; i < items.length; i++) {
			if(items[i].equals(item)) {
				return items[i].decrementQuantity();
			}
		}
		return 0;
	}
}
