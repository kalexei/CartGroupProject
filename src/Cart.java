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

	public double calculateTotal() {
		double total = 0;

		for (Item item: this.getCartItems()) {
			total += item.getPrice();
		}

		return total;
	}
}
