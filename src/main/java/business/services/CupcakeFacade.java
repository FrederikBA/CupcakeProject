package business.services;

import business.entities.Bottom;
import business.entities.Topping;
import business.exceptions.UserException;
import business.persistence.BottomMapper;
import business.persistence.ToppingMapper;

import java.util.List;

public class CupcakeFacade {
    BottomMapper bottomMapper;
    ToppingMapper toppingMapper;


    public List<Bottom> getAllBottoms() throws UserException {
        return bottomMapper.getAllBottoms();
    }

    public List<Topping> getAllToppings() throws UserException {
        return toppingMapper.getAllToppings();
    }
}
