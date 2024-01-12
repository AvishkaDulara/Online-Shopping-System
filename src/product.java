public class product extends Product {
    private static String id;
    public static String name;
    private int price;
    public int qty;

    public product(String name, double price) {
        super(name, price);
    }

    public static String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setId(String id) {
        product.id = id;
    }

    public void setName(String name) {
        product.name = name;
    }

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
