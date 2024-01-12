public class Electronics extends product{
    private String brand;
    private int warrantyPeriod;

    public Electronics(String brand, String name, int warrantyPeriod, int qty, String s, int period){
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
}
