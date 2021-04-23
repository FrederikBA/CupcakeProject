package business.services;


import business.entities.CartItem;
import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.sql.Timestamp;
import java.util.List;

public class OrderFacade {
    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        this.orderMapper = new OrderMapper(database);
    }

    public List<Order> getAllOrders() throws UserException {
        return orderMapper.getAllOrders();
    }

    public int deleteOrder(int orderId) throws UserException {
        return orderMapper.deleteOrder(orderId);
    }

    public int deleteOrderContentByOrderId(int orderId) throws UserException {
        return orderMapper.deleteOrderContentByOrderId(orderId);
    }

    public void insertIntoOrders(int userId, List<CartItem> items) throws UserException {
        orderMapper.insertIntoOrders(userId, items);
    }

    public List<CartItem> getOrderContentByOrderId(int orderId) throws UserException {
        return orderMapper.getOrderContentByOrderId(orderId);
    }
}
