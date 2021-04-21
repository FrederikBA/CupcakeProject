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

public class AddToCartCommand extends CommandUnprotectedPage {
    CupcakeFacade cupcakeFacade = new CupcakeFacade(database);

    public AddToCartCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
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
        }

        if (shoppingCart.getCartItems().size() >= 1) {
            int shoppingCartSize = shoppingCart.getCartItems().size();
            session.setAttribute("cartItemSize", shoppingCartSize);
        }

        session.setAttribute("totalPrice", cupcakeFacade.calcTotalPrice(shoppingCart));
        session.setAttribute("cart", shoppingCart);
        return pageToShow;
    }
}
