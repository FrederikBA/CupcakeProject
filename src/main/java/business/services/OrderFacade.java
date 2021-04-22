package business.services;


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

    public void insertIntoOrders(int userId, double price) throws UserException {
        orderMapper.insertIntoOrders(userId, price);
    }
}
