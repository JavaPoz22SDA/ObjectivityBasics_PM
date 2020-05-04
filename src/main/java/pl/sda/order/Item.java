package pl.sda.order;



/**
 * @author Paweł Matyaszczyk
 */
public class Item {
    private String productName;
    private int quantity;
    private double price;

    public Item() {
    }

    public Item(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double calculateValue() {
        return quantity*price;
    }

    public double calculateValueWithDiscount() {
        double value= quantity*price;
        if (quantity >= 5 && quantity <=10 ){
            value = quantity*price - 0.05*quantity*price;
        }
        if (quantity > 10 && quantity <=20 ){
            value = quantity*price - 0.1*quantity*price;
        }
        if (quantity > 20 ){
            value = quantity*price - 0.05*quantity*price;
        }
        return value;
    }

    @Override
    public String toString() {
        return String.format("%-20s %10.2f zł %4d szt. %10.2f zł",productName,price,quantity,calculateValue());
    }
}
