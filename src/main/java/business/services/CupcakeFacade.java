package business.services;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.CupcakeMapper;
import business.persistence.Database;

import java.util.List;

public class CupcakeFacade {
    CupcakeMapper cupcakeMapper;

    public CupcakeFacade(Database database) {
        this.cupcakeMapper = new CupcakeMapper(database);
    }


    public List<Bottom> getAllBottoms() throws UserException {
        return cupcakeMapper.getAllBottoms();
    }

    public List<Topping> getAllToppings() throws UserException {
        return cupcakeMapper.getAllToppings();
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

    public void removeCartitems(){
        

    }
}
