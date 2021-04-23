package web.commands;

import business.entities.CartItem;
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

        //Show list of orders
        List<Order> orderList = orderFacade.getAllOrders();
        request.setAttribute("orderList", orderList);


        //Show order content from specific order
        if (request.getParameter("content") != null) {
            int orderId = Integer.parseInt(request.getParameter("content"));
            request.setAttribute("orderId", orderId);
            List<CartItem> orderContent = orderFacade.getOrderContentByOrderId(orderId);

            request.setAttribute("orderContent", orderContent);
            return "ordercontentpage";
        }
        //Return from order content page.
        if (request.getParameter("return") != null) {
            return pageToShow;
        }


        //Delete orders
        if (request.getParameter("delete") != null) {
            String deleteId = request.getParameter("delete");
            orderFacade.deleteOrder(Integer.parseInt(deleteId));
        }

        return pageToShow;
    }
}
