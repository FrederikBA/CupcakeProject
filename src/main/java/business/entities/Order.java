package business.entities;


import java.sql.Timestamp;

public class Order {
   private int orderId;
   private int userId;
   private double price;
   private Timestamp timestamp;

    public Order(int orderId, int userId, double price, Timestamp timestamp) {
        this.orderId = orderId;
        this.userId = userId;
        this.price = price;
        this.timestamp = timestamp;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}
