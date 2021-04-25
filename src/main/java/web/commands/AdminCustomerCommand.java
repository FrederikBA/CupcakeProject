package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

class AdminCustomerCommand extends CommandProtectedPage {

    private final UserFacade userFacade;
    private OrderFacade orderFacade;

    public AdminCustomerCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        orderFacade = new OrderFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        List<User> users = userFacade.getAllUsers();
        if (request.getParameter("update") != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            double balance = Double.parseDouble(request.getParameter("balance"));
            if (userFacade.changeBalance(userId, balance)) {
                users = userFacade.getAllUsers();


            } else {
                throw new UserException("Kunne ikke blive opdateret.");
            }
        }

        //Show order content from specific order
        if (request.getParameter("userorders") != null) {
            int userId = Integer.parseInt(request.getParameter("userorders"));
            request.setAttribute("userId", userId);
            List<Order> userOrders = orderFacade.getOrdersByUserId(userId);

            request.setAttribute("userOrders", userOrders);
            return "userorderpage";
        }

        //Return from order content page.
        if (request.getParameter("return") != null) {
            return pageToShow;
        }


        session.setAttribute("users", users);
        return pageToShow;
    }
}
