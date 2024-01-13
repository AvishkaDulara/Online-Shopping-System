public abstract class Product {
    private String id;
    private String name;
    private int availableQuantity;
    private double price;

    public Product(String id,String name,int availableQty, double price) {
        this.price=price;
        this.name=name;
        this.id=id;
        this.availableQuantity=availableQty;
    }

    // assume id is unique
    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQty() {
        return availableQuantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableQty(int qty) {
        this.availableQuantity = qty;
    }

    public abstract String getProductCategory();
}
