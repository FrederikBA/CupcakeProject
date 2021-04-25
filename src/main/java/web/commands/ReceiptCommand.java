package web.commands;

import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReceiptCommand extends CommandUnprotectedPage {
    OrderFacade orderFacade;



    public ReceiptCommand(String pageToShow) {
        super(pageToShow);
        this.orderFacade = new OrderFacade(database);



    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();


        int tmpTest = orderFacade.getOrderIdByTimestamp();
        session.setAttribute("orderidbytime", tmpTest);

        return pageToShow;
    }
}
