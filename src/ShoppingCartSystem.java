import javax.swing.*;
import java.awt.event.*;

public class ShoppingCartSystem {
	// GUI components
	private JFrame frame;
	private JTextField itemNameField;
	private JTextField itemPriceField;
	private JTextField taxRateField;
	private JTextArea cartArea;
	private JLabel itemNameLabel;
	private JLabel itemPriceLabel;
	private JLabel totalLabel;
	private JLabel taxRateLabel;
	private JButton addButton;
	private JButton calculateButton;
	private JButton applyTaxButton;
	private JButton clearCartButton; // New clear cart button

	private void updateCartArea(Cart cart) {
		StringBuilder cartOutput = new StringBuilder();
		for (Item item: cart.getCartItems()) {
			if(item == null) continue;
			cartOutput.append(item).append("\n");
		}
		cartArea.setText(cartOutput.toString());
	}

	double taxRate = 0.05;
	boolean withTax = false;
	public ShoppingCartSystem() {
		Cart cart = new Cart();

		// Step 1: Create window
		frame = new JFrame("Shopping Cart System");
		frame.setSize(600, 400); // Increased the frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		// Step 2: Add components

		// Label and field for item name
		itemNameLabel = new JLabel("Item Name:");
		itemNameLabel.setBounds(20, 20, 100, 25);
		frame.add(itemNameLabel);
		itemNameField = new JTextField();
		itemNameField.setBounds(120, 20, 150, 25);
		frame.add(itemNameField);

		// Label and field for item price
		itemPriceLabel = new JLabel("Item Price:");
		itemPriceLabel.setBounds(20, 60, 100, 25);
		frame.add(itemPriceLabel);
		itemPriceField = new JTextField();
		itemPriceField.setBounds(120, 60, 150, 25);
		frame.add(itemPriceField);

		// Area for cart items
		cartArea = new JTextArea();
		cartArea.setBounds(20, 140, 440, 150);
		cartArea.setEditable(false);
		frame.add(cartArea);

		// User-applied tax
		taxRateLabel = new JLabel("Tax Rate (%): ");
		taxRateLabel.setBounds(20, 100, 100, 25);
		frame.add(taxRateLabel);
		taxRateField = new JTextField();
		taxRateField.setBounds(120, 100, 150, 25);
		frame.add(taxRateField);

		// Label for the cart total
		totalLabel = new JLabel("Total: $0.00");
		totalLabel.setBounds(20, 300, 150, 25);
		frame.add(totalLabel);

		// Button to add an item to cart
		addButton = new JButton("Add Item");
		addButton.setBounds(300, 20, 150, 25);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cart.addItem(new Item(itemNameField.getText(), Double.parseDouble(itemPriceField.getText()), withTax));
				updateCartArea(cart);
				itemNameField.setText("");
				itemPriceField.setText("");
				withTax = false;
			}
		});
		frame.add(addButton);

		calculateButton = new JButton("Calculate Total");
		calculateButton.setBounds(300, 60, 150, 25);
		calculateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				TaxCalculator.calculateTotalPriceWithTax(cart);
			}
		});
		frame.add(calculateButton);

		// Apply Tax Button
		applyTaxButton = new JButton("Apply Tax (5%)");
		applyTaxButton.setBounds(450, 20, 150, 25); // Adjusted position
		applyTaxButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(withTax) return;
				double currentPrice = Double.parseDouble(itemPriceField.getText());
				currentPrice *= (1 + taxRate);
				itemPriceField.setText(currentPrice + "");
				withTax = true;
			}
		});
		frame.add(applyTaxButton);

		// Clear Cart Button
		clearCartButton = new JButton("Clear Cart");
		clearCartButton.setBounds(450, 60, 150, 25); // Adjusted position
		clearCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cart.clearCart();
				updateCartArea(cart);
			}
		});
		frame.add(clearCartButton);

		frame.setVisible(true);
	}
}
