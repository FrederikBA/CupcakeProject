package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CREATED BY Janus @ 2021-04-19 - 15:50
 **/
public class ShoppingCartCommand extends CommandUnprotectedPage{

    public ShoppingCartCommand(String pageToShow) {

        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        return pageToShow;
    }
}
