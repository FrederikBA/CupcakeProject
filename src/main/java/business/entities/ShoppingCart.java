package business.entities;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> cartItems;
    private double totalAmount;

    public ShoppingCart() {
        this.cartItems = new ArrayList<CartItem>();
        this.totalAmount = totalAmount;
    }

    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
