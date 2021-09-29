package Zcart;
public class Inventory {
    private String category;
    private String brand;
    private String model;
    private double price;
    private int stock;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return
                " brand:"+ brand + '\t' +
                 "model:" + model + '\t' +
                 "price:" + price +'\t'+
                 "stock:" + stock+'\t';
    }
}
