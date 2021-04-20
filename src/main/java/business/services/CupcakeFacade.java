package business.services;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.BottomMapper;
import business.persistence.Database;
import business.persistence.ToppingMapper;

import java.util.List;

public class CupcakeFacade {
    BottomMapper bottomMapper;
    ToppingMapper toppingMapper;

    public CupcakeFacade(Database database) {
        this.bottomMapper = new BottomMapper(database);
        this.toppingMapper = new ToppingMapper(database);
    }


    public List<Bottom> getAllBottoms() throws UserException {
        return bottomMapper.getAllBottoms();
    }

    public List<Topping> getAllToppings() throws UserException {
        return toppingMapper.getAllToppings();
    }

    public Topping getToppingById(int id) throws UserException {
        Topping topping = null;
        for (Topping t : getAllToppings()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return topping;
    }

    public Bottom getBottomById(int id) throws UserException {
        Bottom bottom = null;
        for (Bottom b : getAllBottoms()) {
            if (b.getId() == id) {
                return b;
            }
        }
        return bottom;
    }

    public double calcTotalPrice(ShoppingCart shoppingCart) {
        double totalPrice = 0;
        for (CartItem c : shoppingCart.getCartItems()) {
            totalPrice += c.getPrice();
        }
        return totalPrice;
    }
}
