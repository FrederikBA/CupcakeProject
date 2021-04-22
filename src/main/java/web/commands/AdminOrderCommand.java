package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class AdminOrderCommand extends CommandProtectedPage {
    private OrderFacade orderFacade = new OrderFacade(database);

    public AdminOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        List<Order> orderList = orderFacade.getAllorders();

        request.setAttribute("orderList",orderList);

        return pageToShow;
    }
}
