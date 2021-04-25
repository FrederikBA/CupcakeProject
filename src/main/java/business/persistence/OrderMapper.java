package business.persistence;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CupcakeFacade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private Database database;
    CupcakeFacade cupcakeFacade;

    public OrderMapper(Database database) {
        this.database = database;
        this.cupcakeFacade = new CupcakeFacade(database);
    }

    public List<Order> getAllOrders() throws UserException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    int userId = rs.getInt("user_id");
                    double price = rs.getDouble("order_price");
                    Timestamp timestamp = rs.getTimestamp("timestamp");

                    Order tmpOrder = new Order(orderId, userId, price, timestamp);
                    orderList.add(tmpOrder);
                }
                return orderList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<Order> getOrdersByUserId(int id) throws UserException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders WHERE user_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    int userId = rs.getInt("user_id");
                    double price = rs.getDouble("order_price");
                    Timestamp timestamp = rs.getTimestamp("timestamp");

                    Order tmpOrder = new Order(orderId, userId, price, timestamp);
                    orderList.add(tmpOrder);
                }
                return orderList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public int getOrderIdByTimestamp() throws UserException {
        int id = 0;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders ORDER BY timestamp DESC";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("order_id");
                }
                return id;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }

    }


    public List<CartItem> getOrderContentByOrderId(int orderId) throws UserException {
        List<CartItem> items = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM order_content WHERE order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int toppingId = rs.getInt(2);
                    int bottomId = rs.getInt(3);
                    int quantity = rs.getInt(4);
                    double price = rs.getDouble(5);
                    Topping topping = cupcakeFacade.getToppingById(toppingId);
                    Bottom bottom = cupcakeFacade.getBottomById(bottomId);

                    CartItem cartItem = new CartItem(quantity, bottom, topping, price);
                    items.add(cartItem);
                }
                return items;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }


    public int deleteOrder(int orderId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM orders WHERE order_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public int deleteOrderContentByOrderId(int orderId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM order_content WHERE order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void insertIntoOrders(int userId, List<CartItem> items) throws UserException {
        double order_price = 0.0;
        int orderId = 0;
        for (CartItem c : items) {
            order_price += c.getPrice();
        }

        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO orders(user_id,order_price) VALUES (?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, userId);
                ps.setDouble(2, order_price);
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                orderId = ids.getInt(1);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

            for (CartItem cartItem : items) {
                insertIntoOrderContent(orderId, cartItem);
            }

        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void insertIntoOrderContent(int orderId, CartItem item) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO order_content(topping_id,bottom_id,quantity,price,order_id) VALUES (?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, item.getTopping().getId());
                ps.setInt(2, item.getBottom().getId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getPrice());
                ps.setInt(5, orderId);
                ps.executeUpdate();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}
