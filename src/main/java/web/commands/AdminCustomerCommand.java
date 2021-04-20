package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * CREATED BY Janus @ 2021-04-20 - 11:15
 **/
public class AdminCustomerCommand extends CommandProtectedPage {

    public AdminCustomerCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return pageToShow;
    }

}
