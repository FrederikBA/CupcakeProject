package business.persistence;


import business.entities.AccountBalance;
import business.entities.Topping;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountBalanceMapper {

    private Database database;

    public AccountBalanceMapper(Database database) {
        this.database = database;
    }

    public List<AccountBalance> getAccountBalance() throws UserException {
        List<AccountBalance> accountBalanceList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM account_balance";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    double price = rs.getDouble("balance");
                    Timestamp timestamp = rs.getTimestamp("timestamp");

                    AccountBalance tmpAccount = new AccountBalance(id, userId, price, timestamp);
                    accountBalanceList.add(tmpAccount);
                }
                return accountBalanceList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public void updateBalance(int userId, double balance) throws SQLException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO account_balance(user_id,balance) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setDouble(2, balance);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }


    public AccountBalance getAccountBalanceByUserId(int id) throws UserException {
        String query = "SELECT a.* FROM account_balance a WHERE a.timestamp = (SELECT MAX(a1.timestamp) FROM account_balance a1 WHERE a1.user_id = a.user_id) AND a.user_id = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int aid = rs.getInt("id");
                    int uid = rs.getInt("user_id");
                    double balance = rs.getDouble("balance");
                    Timestamp timestamp = rs.getTimestamp("timestamp");

                    return new AccountBalance(aid, uid, balance, timestamp);

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return null;
    }
}

