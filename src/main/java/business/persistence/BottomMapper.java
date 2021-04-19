package business.persistence;

import business.entities.Bottom;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BottomMapper {

    private Database database;

    public BottomMapper(Database database) {
        this.database = database;
    }

    public List<Bottom> getAllBottoms() throws UserException {
        List<Bottom> bottomList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM bottom";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("bottom_id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");

                    Bottom tmpSport = new Bottom(id, name, price);
                    bottomList.add(tmpSport);
                }
                return bottomList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
