import java.util.List;

public interface ShoppingManager {
    void addProduct(Product product);

    void deleteProduct(String productId);

    void printProductList();

    void getProductsFromStore();

    void saveToFile();

    Product getProductByID(String productId);

    List<Product> getProductsByType(String var1);
}
