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
        return orderMapper.getAllorders();
    }

    public int deleteOrder(int orderId) throws UserException {
        return orderMapper.deleteOrder(orderId);
    }

    public void insertIntoOrders(int userId, double price) throws UserException {
        orderMapper.insertIntoOrders(userId, price);
    }

    public void insertIntoOrderContent(int toppingId, int bottomId, int quantity, double price, int order_id) throws UserException {
        orderMapper.insertIntoOrderContent(toppingId, bottomId, quantity, price, order_id);
    }
}
