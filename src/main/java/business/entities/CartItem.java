package business.entities;

public class CartItem {
    private int quantity;
    private Topping topping;
    private Bottom bottom;
    private double price;

    public CartItem(int quantity, Topping topping, Bottom bottom, double price) {
        this.quantity = quantity;
        this.topping = topping;
        this.bottom = bottom;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Topping getTopping() {
        return topping;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public double getPrice() {
        return price;
    }

}
