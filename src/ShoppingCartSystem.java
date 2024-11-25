//import javax.swing.*;
//import java.awt.event.*;
//
//public class ShoppingCartSystem {
//	// Define GUI components
//	private JFrame frame;
//	private JTextField itemNameField;
//	private JTextField itemPriceField;
//	private JTextField taxRateForProductField;
//	private JTextField taxRateForCartField;
//	private JTextArea cartArea;
//	private JLabel itemNameLabel;
//	private JLabel itemPriceLabel;
//	private JLabel totalLabel;
//	private JLabel totalWithTaxLabel;
//	private JLabel taxRateForProductLabel;
//	private JLabel taxRateForCartLabel;
//	private JLabel errorLabel;
//	private JButton addButton;
//	private JButton applyTaxForProductButton;
//	private JButton applyTaxForCartButton;
//	private JButton clearCartButton;
//	private JScrollPane cartAreaScrollPane;
//
//	// Method to update the display of the list of added products
//	private void updateCartArea(Cart cart) {
//		StringBuilder cartOutput = new StringBuilder();
//		for (CartItem item: cart.getCartItems()) {
//			if(item == null) continue;
//			cartOutput.append(item).append("\n");
//		}
//		cartArea.setText(cartOutput.toString());
//	}
//
//	// Method to calculate the total price of the cart
//	private double updateTotalPrice(Cart cart) {
//		return cart.calculateTotal();
//	}
//
//	// Overload previous method to calculate the total price of the cart with tax
//	private double updateTotalPrice(Cart cart, double taxRate) {
//		return TaxCalculator.calculateTotalPriceWithTax(cart, taxRate);
//	}
//
//	private double productTaxRate = 0;
//	private double totalTaxRate = 0;
//	private boolean withTax = false;
//	public ShoppingCartSystem() {
//		Cart cart = new Cart();
//
//		// Step 1: Create window
//		frame = new JFrame("Shopping Cart System");
//		frame.setSize(680, 600); // Increased the frame size
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLayout(null);
//
//		// Step 2: Add components
//
//		// Label and field for item name
//		itemNameLabel = new JLabel("Item Name:");
//		itemNameLabel.setBounds(20, 20, 170, 25);
//
//		itemNameField = new JTextField();
//		itemNameField.setBounds(200, 20, 170, 25);
//
//		// Label and field for item price
//		itemPriceLabel = new JLabel("Item Price:");
//		itemPriceLabel.setBounds(20, 60, 170, 25);
//
//		itemPriceField = new JTextField();
//		itemPriceField.setBounds(200, 60, 170, 25);
//
//		// User-applied tax for the product
//		taxRateForProductLabel = new JLabel("Tax Rate for product (%): ");
//		taxRateForProductLabel.setBounds(20, 100, 170, 25);
//
//		taxRateForProductField = new JTextField();
//		taxRateForProductField.setBounds(200, 100, 170, 25);
//
//		// Area for cart items
//		cartArea = new JTextArea();
//		cartArea.setBounds(20, 140, 640, 200);
//		cartArea.setEditable(false);
//		cartAreaScrollPane = new JScrollPane(cartArea);
//		cartAreaScrollPane.setBounds(20, 140, 640, 200);
//
//		// Apply tax for total button
//		applyTaxForCartButton = new JButton("Apply Tax for Cart");
//		applyTaxForCartButton.setBounds(450, 360, 170, 25);
//		applyTaxForCartButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				errorLabel.setVisible(false);
//				totalTaxRate = Double.parseDouble(taxRateForCartField.getText());
//				try {
//					double updatedTotalPriceWithTax = updateTotalPrice(cart, totalTaxRate);
//					totalWithTaxLabel.setText("Total with tax: $" + updatedTotalPriceWithTax);
//				} catch (Exception ex) {
//					errorLabel.setVisible(true);
//					errorLabel.setText(ex.getMessage());
//				}
//			}
//		});
//
//		// User-applied tax for the total of the cart
//		taxRateForCartLabel = new JLabel("Tax Rate for total (%): ");
//		taxRateForCartLabel.setBounds(20, 360, 170, 25);
//
//		taxRateForCartField = new JTextField();
//		taxRateForCartField.setBounds(200, 360, 170, 25);
//
//		// Label for the cart total
//		totalLabel = new JLabel("Total: $0.00");
//		totalLabel.setBounds(20, 400, 400, 25);
//
//		// Label for the cart total with tax
//		totalWithTaxLabel = new JLabel("Total with tax: $0.00");
//		totalWithTaxLabel.setBounds(20, 440, 400, 25);
//
//		// Button to add an item to cart
//		addButton = new JButton("Add Item");
//		addButton.setBounds(450, 20, 170, 25);
//		addButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				errorLabel.setVisible(false);
//				try {
//					cart.addItem(new CartItem(itemNameField.getText(), Double.parseDouble(itemPriceField.getText()), withTax));
//					updateCartArea(cart);
//					itemNameField.setText("");
//					itemPriceField.setText("");
//					double updatedTotalPrice = updateTotalPrice(cart);
//					double updatedTotalPriceWithTax = updateTotalPrice(cart, totalTaxRate);
//					totalLabel.setText("Total: $" + updatedTotalPrice);
//					totalWithTaxLabel.setText("Total with tax: $" + updatedTotalPriceWithTax);
//					withTax = false;
//				} catch (Exception ex) {
//					errorLabel.setVisible(true);
//					errorLabel.setText(ex.getMessage());
//				}
//			}
//		});
//
//		// Apply Tax Button
//		applyTaxForProductButton = new JButton("Apply Tax");
//		applyTaxForProductButton.setBounds(450, 100, 170, 25); // Adjusted position
//		applyTaxForProductButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(withTax) return;
//				errorLabel.setVisible(false);
//				try {
//					productTaxRate = Double.parseDouble(taxRateForProductField.getText());
//					if(productTaxRate < 0 || productTaxRate > 100) throw new Exception("Tax rate must be between 0 and 100");
//
//					double currentPrice = Double.parseDouble(itemPriceField.getText());
//					currentPrice *= (1 + productTaxRate / 100);
//					itemPriceField.setText(currentPrice + "");
//					withTax = true;
//				} catch (Exception ex) {
//					errorLabel.setVisible(true);
//					errorLabel.setText(ex.getMessage());
//				}
//			}
//		});
//
//		// Initialize the Clear Cart Button
//		clearCartButton = new JButton("Clear Cart");
//		clearCartButton.setBounds(450, 390, 170, 25); // Adjusted position
//		clearCartButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				errorLabel.setVisible(false);
//				try {
//					cart.clearCart();
//					updateCartArea(cart);
//					totalLabel.setText("Total: $0.00");
//					totalWithTaxLabel.setText("Total with tax: $0.00");
//				} catch (Exception ex) {
//					errorLabel.setVisible(false);
//					errorLabel.setText(ex.getMessage());
//				}
//			}
//		});
//
//		// Initialize the error text
//		errorLabel = new JLabel("");
//		errorLabel.setBounds(20, 450, 600, 50);
//
//		// Add all the UI items to the window frame
//		frame.add(itemNameLabel);
//		frame.add(itemNameField);
//		frame.add(itemPriceLabel);
//		frame.add(itemPriceField);
//		frame.add(taxRateForProductLabel);
//		frame.add(taxRateForProductField);
//		frame.add(addButton);
//		frame.add(applyTaxForProductButton);
//		frame.add(applyTaxForCartButton);
//		frame.add(clearCartButton);
//		frame.add(cartAreaScrollPane);
//		frame.add(totalLabel);
//		frame.add(totalWithTaxLabel);
//		frame.add(taxRateForCartLabel);
//		frame.add(taxRateForCartField);
//		frame.add(errorLabel);
//
//		frame.setVisible(true);
//	}
//}
