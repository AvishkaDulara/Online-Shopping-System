public class Clothing extends product {
    private String size;
    private String color;

    public Clothing(String size, String color, int price, int qty, String s, String string) {
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
}
