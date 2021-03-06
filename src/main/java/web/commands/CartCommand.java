package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.AccountBalanceFacade;
import business.services.CupcakeFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class CartCommand extends CommandUnprotectedPage {
    CupcakeFacade cupcakeFacade;
    OrderFacade orderFacade;
    AccountBalanceFacade accountBalanceFacade;


    public CartCommand(String pageToShow) {
        super(pageToShow);
        this.orderFacade = new OrderFacade(database);
        this.cupcakeFacade = new CupcakeFacade(database);
        this.accountBalanceFacade = new AccountBalanceFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        User user;
        int userId = 1;

        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
        }


        double currentBalance = accountBalanceFacade.getBalanceByUserId(userId).getBalance();
        session.setAttribute("currentBalance", currentBalance);
        double newBalance = 0;

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }

        if (request.getParameter("topping") != null || request.getParameter("bottom") != null) {
            int toppingId = Integer.parseInt(request.getParameter("topping"));
            int bottomId = Integer.parseInt(request.getParameter("bottom"));
            Topping topping = cupcakeFacade.getToppingById(toppingId);
            Bottom bottom = cupcakeFacade.getBottomById(bottomId);

            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = quantity * (topping.getPrice() + bottom.getPrice());
            shoppingCart.addToCart(new CartItem(quantity, bottom, topping, price));
            System.out.println(shoppingCart.getCartItems());
        }
        double totalPrice = cupcakeFacade.calcTotalPrice(shoppingCart);


        //Delete section
        if (request.getParameter("delete") != null) {
            int deleteId = Integer.parseInt(request.getParameter("delete"));
            shoppingCart.getCartItems().remove(deleteId);
            totalPrice = cupcakeFacade.calcTotalPrice(shoppingCart);
        }


        if (shoppingCart.getCartItems().size() >= 1) {
            int shoppingCartSize = shoppingCart.getCartItems().size();
            session.setAttribute("cartItemSize", shoppingCartSize);
        }

        //Buy section
        if (session.getAttribute("user") == null && request.getParameter("buy") != null) {

            throw new UserException("Du skal v??re logged in");

        } else if (request.getParameter("buy") != null && shoppingCart.getCartItems().size() > 0) {
            newBalance = currentBalance - totalPrice;
            if (newBalance >= 0) {
                try {
                    orderFacade.insertIntoOrders(userId, shoppingCart.getCartItems());
                    accountBalanceFacade.changeBalance(userId, newBalance);

                    //Receipt
                    int orderId = orderFacade.getOrderIdByTimestamp();
                    List<CartItem> receiptList = orderFacade.getOrderContentByOrderId(orderId);
                    session.setAttribute("receiptList", receiptList);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                throw new UserException("Du har ikke nok penge");
            }

            shoppingCart.getCartItems().clear();
            return "receiptpage";
        }


        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("cart", shoppingCart);


        return pageToShow;
    }
}
