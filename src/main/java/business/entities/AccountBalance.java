package business.entities;

import business.services.AccountBalanceFacade;

import java.sql.Timestamp;

public class AccountBalance {
    private int id;
    private int userId;
    private double balance;
    private Timestamp timestamp;


    public AccountBalance(int id, int userId, double balance, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.timestamp = timestamp;
    }

    public AccountBalance(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AccountBalance{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", timestamp=" + timestamp +
                '}';
    }
}
