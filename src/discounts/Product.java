package discounts;

public class Product {

    private String categoryId;
    private String productId;
    private double price;
    private int numUnitsPurchased;


    public Product(String categoryId, String productId, double price) {
        this.categoryId = categoryId;
        this.productId = productId;
        this.price = price;
        this.numUnitsPurchased=0;
    }


    public String getCategoryId() {
        return categoryId;
    }


    public String getProductId() {
        return productId;
    }


    public double getPrice() {
        return price;
    }


    public int getNumUnitsPurchased() {
        return numUnitsPurchased;
    }

    public void addNumUnitPurchased(int n){
        this.numUnitsPurchased+=n;
    }

    

    

}
