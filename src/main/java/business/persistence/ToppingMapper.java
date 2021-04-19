package business.persistence;

import business.entities.Bottom;
import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToppingMapper {

    private Database database;

    public ToppingMapper(Database database) {
        this.database = database;
    }

    public List<Topping> getAllToppings() throws UserException {
        List<Topping> toppingList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM topping";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("topping_id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");

                    Topping tmpTopping = new Topping(id, name, price);
                    toppingList.add(tmpTopping);
                }
                return toppingList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
