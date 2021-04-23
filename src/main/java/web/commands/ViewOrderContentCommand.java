package web.commands;

import business.entities.CartItem;
import business.entities.Order;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewOrderContentCommand extends CommandProtectedPage {
    OrderFacade orderFacade;

    public ViewOrderContentCommand(String pageToShow, String role) {
        super(pageToShow, role);
        orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        //List<CartItem> orderList = orderFacade.getOrderContentByOrderId();

        //request.setAttribute("orderList", orderList);


        return pageToShow;
    }
}
