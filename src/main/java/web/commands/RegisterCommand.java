package web.commands;

import business.entities.User;
import business.persistence.Database;
import business.services.AccountBalanceFacade;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class RegisterCommand extends CommandUnprotectedPage {
    private UserFacade userFacade;
    private AccountBalanceFacade accountBalanceFacade;

    public RegisterCommand(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
        accountBalanceFacade = new AccountBalanceFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");


        if (password1.equals(password2)) {
            User user = userFacade.createUser(email, password1);
            HttpSession session = request.getSession();

            try {
                accountBalanceFacade.changeBalance(user.getId(), 500);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            session.setAttribute("email", email);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            return user.getRole() + "page";
        } else {
            request.setAttribute("error", "the two passwords did not match");
            return "registerpage";
        }
    }

}
