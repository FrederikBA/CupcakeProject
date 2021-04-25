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

public class AddToCartCommand extends CommandUnprotectedPage {
    CupcakeFacade cupcakeFacade;
    OrderFacade orderFacade;
    AccountBalanceFacade accountBalanceFacade;


    public AddToCartCommand(String pageToShow) {
        super(pageToShow);
        this.orderFacade = new OrderFacade(database);
        this.cupcakeFacade = new CupcakeFacade(database);
        this.accountBalanceFacade = new AccountBalanceFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        User user;
        int quantity = 1;
        int userId = 1;

        //Get user ID
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
        }

        //Create shopping cart
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("cart", shoppingCart);
        }

        //Error messages
        if (request.getParameter("topping") == null || request.getParameter("bottom") == null) {
            throw new UserException("Du mangler at vælge enten bund eller topping til din cupcake.");
        }
        Topping topping = null;
        Bottom bottom = null;

        if (request.getParameter("quantity").equals("")) {
            throw new UserException("Du mangler at vælge antal.");
        }

        //Get topping and bottom objects from id
        if (request.getParameter("topping") != null || request.getParameter("bottom") != null) {
            int toppingId = Integer.parseInt(request.getParameter("topping"));
            int bottomId = Integer.parseInt(request.getParameter("bottom"));
            topping = cupcakeFacade.getToppingById(toppingId);
            bottom = cupcakeFacade.getBottomById(bottomId);
        }


        //Get quantity of selected cupcake.
        if (!request.getParameter("quantity").equals("")) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }


        //Calculate price of cart item
        double price = quantity * (topping.getPrice() + bottom.getPrice());


        //Add to cart section
        shoppingCart.addToCart(new CartItem(quantity, bottom, topping, price));
        System.out.println(shoppingCart.getCartItems());
        double totalPrice = cupcakeFacade.calcTotalPrice(shoppingCart);
        session.setAttribute("totalPrice", totalPrice);


        //Show shopping cart size when cart isn't empty
        if (shoppingCart.getCartItems().size() >= 1) {
            int shoppingCartSize = shoppingCart.getCartItems().size();
            request.setAttribute("cartItemSize", shoppingCartSize);
        }
        return pageToShow;
    }
}