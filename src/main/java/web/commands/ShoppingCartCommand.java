package web.commands;

import business.entities.Bottom;
import business.entities.CartItem;
import business.entities.ShoppingCart;
import business.entities.Topping;
import business.exceptions.UserException;
import business.services.CupcakeFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * CREATED BY Janus @ 2021-04-19 - 15:50
 **/
public class ShoppingCartCommand extends CommandUnprotectedPage {
    CupcakeFacade cupcakeFacade = new CupcakeFacade(database);

    public ShoppingCartCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        //public CartItem(int quantity, Topping topping, Bottom bottom, double price) {
        HttpSession session = request.getSession();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");    

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }

        int toppingId = Integer.parseInt(request.getParameter("topping"));
        int bottomId = Integer.parseInt(request.getParameter("bottom"));
        Topping topping = cupcakeFacade.getToppingById(toppingId);
        Bottom bottom = cupcakeFacade.getBottomById(bottomId);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = quantity * (topping.getPrice() + bottom.getPrice());

        shoppingCart.addToCart(new CartItem(quantity, topping, bottom, price));

        int shoppingCartSize = shoppingCart.getCartItems().size();

        session.setAttribute("cart",shoppingCart);
        session.setAttribute("cartItems", shoppingCart.getCartItems());
        session.setAttribute("cartItemSize", shoppingCartSize);
        return pageToShow;
    }
}
