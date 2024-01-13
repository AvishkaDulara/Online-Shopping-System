import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class WestminsterOnlineShoppingSystemGUI {
    private ShoppingManager manager;
    private ShoppingCart cart;

    private JFrame mainFrame;
    private JComboBox<String> categories;
    private JTable productsTable;
    private JTextArea detailsArea;
    private JButton addCartButton, viewCartButton;
    private JLabel priceLabel;

    private JFrame cartFrame;
    private JTable cartTable;

    private JLabel totalLabel;
    private JLabel firstPurchaseDiscountLabel;
    private JLabel categoryDiscountLabel;

    private JLabel finalTotalLabel;

    public WestminsterOnlineShoppingSystemGUI(ShoppingManager manager, ShoppingCart cart, List<Product> productList) {
        this.manager = manager;
        this.cart = cart;

        // Setup Main Frame
        setupMainFrame();

        // Components
        categories = new JComboBox<>(new String[]{"All", "Clothing", "Electronics"});
        productsTable = new JTable();
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        addCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");
        priceLabel = new JLabel();

        // Cart Frame
        setupViewCartFrame();

        // Add Components to Main Frame
        mainFrame.add(setupTopPanel(), BorderLayout.NORTH);
        mainFrame.add(setupMainPanel(), BorderLayout.CENTER);
        mainFrame.add(setupCartPanel(), BorderLayout.SOUTH);

        mainFrame.setVisible(true);

//        populateProductsTable(productList);

        productsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = productsTable.getSelectedRow();
                    if (row != -1) {
                        String id = productsTable.getValueAt(row, 0).toString();
                        showProductDetails(id);
                    }
                }
            }
        });
    }

    private void setupViewCartFrame() {
        cartFrame = new JFrame("Cart");
        cartFrame.setSize(400, 300);
        cartFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        cartFrame.setLayout(new BorderLayout());

        cartTable = new JTable(new DefaultTableModel(
                new Object[]{"Product", "Quantity", "Price"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        cartTable.setEnabled(false);
        cartFrame.add(new JScrollPane(cartTable), BorderLayout.CENTER);

        // Summary panel setup
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(4, 2));  // 4 rows, 2 columns


        // Total

        summaryPanel.add(new JLabel("Total: "));
        totalLabel=new JLabel();
        summaryPanel.add(totalLabel);

        // First Purchase Discount
//        firstPurchaseDiscountLabel = new JLabel("First Purchase Discount (10%): -8.58 €");
//        summaryPanel.add(new JLabel("Total: "));
//        summaryPanel.add(firstPurchaseDiscountLabel);

        // Category Discount

        summaryPanel.add(new JLabel("Three Items in same Category Discount (20%):"));
        categoryDiscountLabel=new JLabel();
        summaryPanel.add(categoryDiscountLabel);

        // Final Total

        summaryPanel.add(new JLabel("Final Total:"));
        finalTotalLabel=new JLabel();
        summaryPanel.add(finalTotalLabel);

        cartFrame.add(summaryPanel, BorderLayout.SOUTH);

        cartFrame.setVisible(true);
    }

    private void setupMainFrame() {
        mainFrame = new JFrame("Shopping App");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
    }

    private JPanel setupTopPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Choose Category:"));
        panel.add(categories);
        panel.add(viewCartButton);

        categories.addActionListener(e -> filterProducts());

        return panel;
    }

    private JPanel setupMainPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        // Products Panel
        JPanel productsPanel = new JPanel(new BorderLayout());
        productsPanel.add(new JScrollPane(productsTable), BorderLayout.CENTER);

        // Details Panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.add(new JLabel("Product Details:"), BorderLayout.NORTH);
        detailsPanel.add(detailsArea, BorderLayout.CENTER);
        detailsPanel.add(addCartButton, BorderLayout.SOUTH);

        panel.add(productsPanel);
        panel.add(detailsPanel);

        setupProductsTable();

        addCartButton.addActionListener(e -> addToCart());

        viewCartButton.addActionListener(e -> showCart());

        return panel;
    }

    private void setupProductsTable() {
        productsTable.setModel(new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Price", "Info"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    private JPanel setupCartPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Total Price: $"));
        panel.add(priceLabel);
        return panel;
    }

    private void populateProductsTable(List<Product> productList) {
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        model.setRowCount(0);
        productList.forEach(p -> model.addRow(new Object[]{p.getId(), p.getName(), p.getProductCategory(), p.getPrice(), getDetails(p)}));
    }

    private String getDetails(Product product) {
        if (product instanceof Clothing) {
            Clothing c = (Clothing) product;
            return "Size: " + c.getSize() + ", Color: " + c.getColor();
        } else if (product instanceof Electronics) {
            Electronics e = (Electronics) product;
            return "Brand: " + e.getBrand() + ", Warranty: " + e.getWarrantyPeriod() + " months";
        }
        return "";
    }

    private void showProductDetails(String id) {
        Product p = manager.getProductByID(id);
        if (p != null) {
            detailsArea.setText("sssss");
            detailsArea.setText("ID: " + p.getId() + "\nName: " + p.getName() + "\nCategory: " + p.getProductCategory() + "\nPrice: " + p.getPrice() + "\n" + getDetails(p));
        }
    }

    private void addToCart() {
        int row = productsTable.getSelectedRow();
        if (row != -1) {
            String id = productsTable.getValueAt(row, 0).toString();
            Product p = manager.getProductByID(id);
            if (p != null) {
                cart.addItem(p);
                JOptionPane.showMessageDialog(mainFrame, "Added to cart!");
            }
        }
    }

    private void showCart() {
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0);
        List<Product> itemList=cart.getItems();
        cart.getItems().forEach(p -> model.addRow(new Object[]{p.getName(), 1, p.getPrice()}));
        priceLabel.setText(String.format("%.2f", cart.getFinalTotal()));
        totalLabel.setText(String.format("%.2f", cart.getTotalCost()));
        categoryDiscountLabel.setText(String.format("%.2f", cart.getCategoryDiscount()));
        finalTotalLabel.setText(String.format("%.2f",cart.getTotalCost()- cart.getCategoryDiscount()));

        cartFrame.setVisible(true);
    }

    private void filterProducts() {
        String category = categories.getSelectedItem().toString();
        List<Product> filtered = manager.getProductsByType(category);
        populateProductsTable(filtered);
    }

    public static void main(String[] args) {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        ShoppingCart cart = new ShoppingCart();
        manager.addProduct(new Electronics("T001", "Laptop", 999.99, 24,"Dell",12));
        manager.addProduct(new Clothing("C001", "Shirt", 29.99, 69, "XL","Black"));
        manager.addProduct(new Electronics("T002", "Phone", 599.99,  12,"Apple",24));

        new WestminsterOnlineShoppingSystemGUI(manager, cart, manager.getProductList());
    }
}
