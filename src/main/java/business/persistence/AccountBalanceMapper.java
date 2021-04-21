package business.persistence;


import business.entities.AccountBalance;
import business.entities.Topping;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATED BY Janus @ 2021-04-20 - 13:25
 **/
public class AccountBalanceMapper {

    private Database database;

    public AccountBalanceMapper(Database database){
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

    public void setKredit(int id, double credit) throws SQLException {
        try  (Connection connection = database.connect()){
            String sql = "UPDATE account_balance SET balance = (?) WHERE id = (?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, credit);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }


    public AccountBalance getAccountBalanceByUserId(int id) throws UserException {
        String query = "SELECT a.* FROM account_balance a WHERE a.timestamp = (SELECT MAX(a1.timestamp) FROM account_balance a1 WHERE a1.user_id = a.user_id) AND a.user_id = ?";

        System.out.println("inside get account balance by user id: " + id);
        try (Connection connection = database.connect()) {
            System.out.println("inside connection");
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                System.out.println("inside ps");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("inside rs");
                    int aid = rs.getInt("id");
                    System.out.println("balance id: " +aid);
                    int uid = rs.getInt("user_id");
                    System.out.println("user id: " + uid);
                    double balance =  rs.getDouble("balance");
                    System.out.println("balance: " + balance);
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    System.out.println("timestamp: "+ timestamp.toString());

                    return new AccountBalance(aid,uid,balance,timestamp);

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


