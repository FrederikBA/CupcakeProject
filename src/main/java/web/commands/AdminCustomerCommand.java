package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.AccountBalanceMapper;
import business.persistence.UserMapper;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * CREATED BY Janus @ 2021-04-20 - 11:15
 **/
public class AdminCustomerCommand extends CommandProtectedPage {

    private final UserFacade userFacade;

    public AdminCustomerCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade =  new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        List<User> userList = userFacade.getAllUsers();
        request.setAttribute("users", userList);


        String action = request.getParameter("action");

    if (request.getParameter("update") != null) {
        int userId = Integer.parseInt(request.getParameter("id"));
        double balance = Double.parseDouble(request.getParameter("credit"));
        userFacade.changeBalance(userId, balance);
    }
       /* switch (action){
            case "changebalance":

                userFacade.changeBalance(userId, balance);
            case "deleteuser":
                //metode
        }
*/
        return pageToShow;

    }
}
