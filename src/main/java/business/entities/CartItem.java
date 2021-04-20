package business.entities;

public class CartItem {
    private int quantity;
    private Bottom bottom;
    private Topping topping;
    private double price;

    public CartItem(int quantity, Bottom bottom, Topping topping, double price) {
        this.quantity = quantity;
        this.bottom = bottom;
        this.topping = topping;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public Topping getTopping() {
        return topping;
    }

    public double getPrice() {
        return price;
    }
}
