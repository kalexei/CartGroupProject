import javax.swing.*;
import java.awt.event.*;

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
        return this.name + " at $" + Math.round(this.price * 100) / 100.0;
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

class CartItem extends Item implements ItemInterface {
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

class Cart extends CartTemplate {}

class TaxCalculator {
    public static double calculateTotalPriceWithTax(Cart cart, double taxRate) {
        if(taxRate > 100 || taxRate < 0) {
            throw new IllegalArgumentException("Tax rate must be between 0 and 100");
        }

        return cart.calculateTotal(taxRate);
    }
}

class ShoppingCartSystem {
    // Define GUI components
    private JFrame frame;
    private JTextField itemNameField;
    private JTextField itemPriceField;
    private JTextField taxRateForProductField;
    private JTextField taxRateForCartField;
    private JTextArea cartArea;
    private JLabel itemNameLabel;
    private JLabel itemPriceLabel;
    private JLabel totalLabel;
    private JLabel totalWithTaxLabel;
    private JLabel taxRateForProductLabel;
    private JLabel taxRateForCartLabel;
    private JLabel errorLabel;
    private JButton addButton;
    private JButton applyTaxForProductButton;
    private JButton applyTaxForCartButton;
    private JButton clearCartButton;
    private JScrollPane cartAreaScrollPane;

    // Method to update the display of the list of added products
    private void updateCartArea(Cart cart) {
        StringBuilder cartOutput = new StringBuilder();
        for (CartItem item: cart.getCartItems()) {
            if(item == null) continue;
            cartOutput.append(item).append("\n");
        }
        cartArea.setText(cartOutput.toString());
    }

    // Method to calculate the total price of the cart
    private double updateTotalPrice(Cart cart) {
        return cart.calculateTotal();
    }

    // Overload previous method to calculate the total price of the cart with tax
    private double updateTotalPrice(Cart cart, double taxRate) {
        return TaxCalculator.calculateTotalPriceWithTax(cart, taxRate);
    }

    private double productTaxRate = 0;
    private double totalTaxRate = 0;
    private boolean withTax = false;
    public ShoppingCartSystem() {
        Cart cart = new Cart();

        // Step 1: Create window
        frame = new JFrame("Shopping Cart System");
        frame.setSize(680, 600); // Increased the frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Step 2: Add components

        // Label and field for item name
        itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setBounds(20, 20, 170, 25);

        itemNameField = new JTextField();
        itemNameField.setBounds(200, 20, 170, 25);

        // Label and field for item price
        itemPriceLabel = new JLabel("Item Price:");
        itemPriceLabel.setBounds(20, 60, 170, 25);

        itemPriceField = new JTextField();
        itemPriceField.setBounds(200, 60, 170, 25);

        // User-applied tax for the product
        taxRateForProductLabel = new JLabel("Tax Rate for product (%): ");
        taxRateForProductLabel.setBounds(20, 100, 170, 25);

        taxRateForProductField = new JTextField();
        taxRateForProductField.setBounds(200, 100, 170, 25);

        // Area for cart items
        cartArea = new JTextArea();
        cartArea.setBounds(20, 140, 640, 200);
        cartArea.setEditable(false);
        cartAreaScrollPane = new JScrollPane(cartArea);
        cartAreaScrollPane.setBounds(20, 140, 640, 200);

        // Apply tax for total button
        applyTaxForCartButton = new JButton("Apply Tax for Cart");
        applyTaxForCartButton.setBounds(450, 360, 170, 25);
        applyTaxForCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorLabel.setVisible(false);
                totalTaxRate = Double.parseDouble(taxRateForCartField.getText());
                try {
                    double updatedTotalPriceWithTax = updateTotalPrice(cart, totalTaxRate);
                    totalWithTaxLabel.setText("Total with tax: $" + Math.round(updatedTotalPriceWithTax * 100) / 100.0);
                } catch (Exception ex) {
                    errorLabel.setVisible(true);
                    errorLabel.setText(ex.getMessage());
                }
            }
        });

        // User-applied tax for the total of the cart
        taxRateForCartLabel = new JLabel("Tax Rate for total (%): ");
        taxRateForCartLabel.setBounds(20, 360, 170, 25);

        taxRateForCartField = new JTextField();
        taxRateForCartField.setBounds(200, 360, 170, 25);

        // Label for the cart total
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setBounds(20, 400, 400, 25);

        // Label for the cart total with tax
        totalWithTaxLabel = new JLabel("Total with tax: $0.00");
        totalWithTaxLabel.setBounds(20, 440, 400, 25);

        // Button to add an item to cart
        addButton = new JButton("Add Item");
        addButton.setBounds(450, 20, 170, 25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorLabel.setVisible(false);
                try {
                    cart.addItem(new CartItem(itemNameField.getText(), Double.parseDouble(itemPriceField.getText()), withTax));
                    updateCartArea(cart);
                    itemNameField.setText("");
                    itemPriceField.setText("");
                    double updatedTotalPrice = updateTotalPrice(cart);
                    double updatedTotalPriceWithTax = updateTotalPrice(cart, totalTaxRate);
                    totalLabel.setText("Total: $" + Math.round(updatedTotalPrice * 100) / 100.0);
                    totalWithTaxLabel.setText("Total with tax: $" + Math.round(updatedTotalPriceWithTax * 100) / 100.0);
                    withTax = false;
                } catch (Exception ex) {
                    errorLabel.setVisible(true);
                    errorLabel.setText(ex.getMessage());
                }
            }
        });

        // Apply Tax Button
        applyTaxForProductButton = new JButton("Apply Tax");
        applyTaxForProductButton.setBounds(450, 100, 170, 25); // Adjusted position
        applyTaxForProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(withTax) return;
                errorLabel.setVisible(false);
                try {
                    productTaxRate = Double.parseDouble(taxRateForProductField.getText());
                    if(productTaxRate < 0 || productTaxRate > 100) throw new Exception("Tax rate must be between 0 and 100");

                    double currentPrice = Double.parseDouble(itemPriceField.getText());
                    currentPrice *= (1 + productTaxRate / 100);
                    itemPriceField.setText(Math.round(currentPrice * 100) / 100.0 + "");
                    withTax = true;
                } catch (Exception ex) {
                    errorLabel.setVisible(true);
                    errorLabel.setText(ex.getMessage());
                }
            }
        });

        // Initialize the Clear Cart Button
        clearCartButton = new JButton("Clear Cart");
        clearCartButton.setBounds(450, 390, 170, 25); // Adjusted position
        clearCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorLabel.setVisible(false);
                try {
                    cart.clearCart();
                    updateCartArea(cart);
                    totalLabel.setText("Total: $0.00");
                    totalWithTaxLabel.setText("Total with tax: $0.00");
                } catch (Exception ex) {
                    errorLabel.setVisible(false);
                    errorLabel.setText(ex.getMessage());
                }
            }
        });

        // Initialize the error text
        errorLabel = new JLabel("");
        errorLabel.setBounds(20, 450, 600, 50);

        // Add all the UI items to the window frame
        frame.add(itemNameLabel);
        frame.add(itemNameField);
        frame.add(itemPriceLabel);
        frame.add(itemPriceField);
        frame.add(taxRateForProductLabel);
        frame.add(taxRateForProductField);
        frame.add(addButton);
        frame.add(applyTaxForProductButton);
        frame.add(applyTaxForCartButton);
        frame.add(clearCartButton);
        frame.add(cartAreaScrollPane);
        frame.add(totalLabel);
        frame.add(totalWithTaxLabel);
        frame.add(taxRateForCartLabel);
        frame.add(taxRateForCartField);
        frame.add(errorLabel);

        frame.setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        new ShoppingCartSystem();
    }
}
