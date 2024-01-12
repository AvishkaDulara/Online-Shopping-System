import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class ShoppingCart {
    private List<Product> productList;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    // Methods to add, remove, and calculate total cost
    public void addProductToCart(product product) {
        productList.add(product);
    }

    public void removeProductFromCart(product product) {
        productList.remove(product);
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product product : productList) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    // Getter for the product list
    public List<Product> getProductList() {
        return productList;
    }

    public static void main(String[] args) {
        // Example usage
        Product product1 = new Product("Product 1", 10.99);
        Product product2 = new Product("Product 2", 5.99);

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);

        double totalCost = shoppingCart.calculateTotalCost();
        System.out.println("Total cost: $" + totalCost);

        shoppingCart.removeProductFromCart(product1);

        double updatedTotalCost = shoppingCart.calculateTotalCost();
        System.out.println("Updated total cost: $" + updatedTotalCost);
    }
}
