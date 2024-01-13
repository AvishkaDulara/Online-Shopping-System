import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Assuming the Product class exists with fields 'name' and 'price', along with appropriate constructors and getters

public class ShoppingCart {
    private List<Product> itemListInCart;

    public ShoppingCart() {
        itemListInCart = new ArrayList<>();
    }

    public void addItem(Product item) {
        itemListInCart.add(item);
    }

    public void removeItem(Product item) {
        itemListInCart.remove(item);
    }

    public double getFinalTotal() {
        double totalCost = 0.0;
        Map<String, Integer> categoryCount = new HashMap<>();

        for (Product product : itemListInCart) {
            String category = product.getProductCategory();
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }

        for (Product product : itemListInCart) {
            double price = product.getPrice();

            if (categoryCount.get(product.getProductCategory()) >= 3) {
                price =price * 0.8;
            }

            totalCost += price;
        }

        return totalCost;
    }

    public double getTotalCost() {
        double totalCost = 0.0;

        for (Product product : itemListInCart) {
            double price = product.getPrice();
            totalCost += price;
        }

        return totalCost;
    }

    public double getCategoryDiscount() {
        double discount = 0.0;
        Map<String, Integer> categoryCount = new HashMap<>();

        for (Product product : itemListInCart) {
            String category = product.getProductCategory();
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }

        for (Product product : itemListInCart) {
            double price = product.getPrice();

            if (categoryCount.get(product.getProductCategory()) >= 3) {
                discount=discount + (price * 0.2);

            }

        }

        return discount;
    }

//    public double getSummarizedCartDetails() {
//        double totalCost = 0.0;
//        Map<String, Integer> itemCount = new HashMap<>();
//
//        for (Product product : itemListInCart) {
//            String itemId = product.getId();
//            itemMap.put(itemId, itemMap.getOrDefault(itemId, 0) + 1);
//        }
//
//        for (Product product : itemListInCart) {
//            double price = product.getPrice();
//
//            if (categoryCount.get(product.getProductCategory()) >= 3) {
//                price =price * 0.8;
//            }
//
//            totalCost += price;
//        }
//
//        return totalCost;
//    }


    public List<Product> getItems() {
        return new ArrayList<>(itemListInCart);
    }

//    public static void main(String[] args) {
//        // Demonstration of CartManager functionality
//        Product firstProduct = new Product("Gadget", 20.50);
//        Product secondProduct = new Product("Accessory", 9.75);
//
//        ShoppingCart cart = new ShoppingCart();
//
//        cart.addItem(firstProduct);
//        cart.addItem(secondProduct);
//
//        System.out.println("Initial Total: $" + cart.getTotalCost());
//
//        cart.removeItem(firstProduct);
//
//        System.out.println("New Total after removal: $" + cart.getTotalCost());
//    }
}
