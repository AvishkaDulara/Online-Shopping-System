public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(String id, String name,double price, int qty, String brand, int warrantyPeriod){
        super(id,name,qty,price);
        this.brand= brand;
        this.warrantyPeriod=warrantyPeriod;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String getProductCategory() {
        return "Electronics";
    }
}
