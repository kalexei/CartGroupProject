import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShoppingCartSystem {

	// GUI components
	private JFrame frame;
	private JTextField itemNameField;
	private JTextField itemPriceField;
	private JTextArea cartArea;
	private JLabel totalLabel;
	private JButton addButton;
	private JButton calculateButton;
	private JButton applyTaxButton;
	private JButton clearCartButton; // New clear cart button

	// List to hold items in the cart
	private ArrayList<String> cart;
	private double totalPrice;

	public ShoppingCartSystem() {
		cart = new ArrayList<>();
		totalPrice = 0.0;

		frame = new JFrame("Shopping Cart System");
		frame.setSize(600, 400); // Increased the frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		// Step 2: Add components
		JLabel itemNameLabel = new JLabel("Item Name:");
		itemNameLabel.setBounds(20, 20, 100, 25);
		frame.add(itemNameLabel);

		itemNameField = new JTextField();
		itemNameField.setBounds(120, 20, 150, 25);
		frame.add(itemNameField);

		JLabel itemPriceLabel = new JLabel("Item Price:");
		itemPriceLabel.setBounds(20, 60, 100, 25);
		frame.add(itemPriceLabel);

		itemPriceField = new JTextField();
		itemPriceField.setBounds(120, 60, 150, 25);
		frame.add(itemPriceField);

		cartArea = new JTextArea();
		cartArea.setBounds(20, 100, 440, 150);
		cartArea.setEditable(false);
		frame.add(cartArea);

		totalLabel = new JLabel("Total: $0.00");
		totalLabel.setBounds(20, 260, 150, 25);
		frame.add(totalLabel);

		addButton = new JButton("Add Item");
		addButton.setBounds(300, 20, 150, 25);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cart.addItem(new Item(itemNameField.getText(), Integer.parseInt(itemPriceField.getText())), 1);
			}
		});
		frame.add(addButton);

		calculateButton = new JButton("Calculate Total");
		calculateButton.setBounds(300, 60, 150, 25);
		calculateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// calculateTotal(); goes here
			}
		});
		frame.add(calculateButton);

		// Apply Tax Button
		applyTaxButton = new JButton("Apply Tax (5%)");
		applyTaxButton.setBounds(450, 20, 150, 25); // Adjusted position
		applyTaxButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//applyTax(); Method goes to this
			}
		});
		frame.add(applyTaxButton); // Make sure it's added to the frame

		// Clear Cart Button
		clearCartButton = new JButton("Clear Cart");
		clearCartButton.setBounds(450, 60, 150, 25); // Adjusted position
		clearCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// clearCart(); method goes here
			}
		});
		frame.add(clearCartButton);

		frame.setVisible(true);
	}


	// Main method to run the ShoppingCartSystem
	public static void main(String[] args) {
		new ShoppingCartSystem();
	}
}
