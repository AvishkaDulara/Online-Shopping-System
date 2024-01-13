public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(String id,String name, double price,int qty, String size, String color) {
        super(id,name,qty,price);
        this.size = size;
        this.color = color;
    }

    // Getters and Setters for size
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
       this.size = size;
    }

    // Getters and Setters for color
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getProductCategory() {
        return "Clothing";
    }
}
