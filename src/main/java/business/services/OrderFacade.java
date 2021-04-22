package business.services;


import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.util.List;

public class OrderFacade {
    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        this.orderMapper = new OrderMapper(database);
    }

    public List<Order> getAllorders() throws UserException {
        return orderMapper.getAllorders();
    }

    public int deleteOrder(int orderId) throws UserException {
        return deleteOrder(1);
    }

}
