package business.persistence;

import business.entities.AccountBalance;
import business.exceptions.UserException;
import business.entities.User;
import business.services.AccountBalanceFacade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper
{
    private Database database;
    private AccountBalanceFacade accountBalanceFacade;

    public UserMapper(Database database)
    {
        this.database = database;
        accountBalanceFacade = new AccountBalanceFacade(database);
    }

    public void createUser(User user) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO user (email, password, role) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public User login(String email, String password) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT id, role FROM user WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String role = rs.getString("role");
                    int id = rs.getInt("id");
                    User user = new User(email, password, role);
                    user.setId(id);
                    return user;
                } else
                {
                    throw new UserException("Could not validate user");
                }
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<User> getAllUsers() throws UserException {
        List<User> users = new ArrayList<>();

        try (Connection connection = database.connect())
        {
            System.out.println("inside connection");
            String sql = "SELECT * FROM user ORDER BY id";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                System.out.println("inside ps");
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    System.out.println("inside rs");
                    User tmpUser = null;
                    String role = rs.getString("role");
                    System.out.println("role: " + role);
                    int id = rs.getInt("id");
                    System.out.println("id: " + id);
                    String email = rs.getString("email");
                    System.out.println("email: " + email);


                    AccountBalance accountBalance = null;

                    accountBalance = accountBalanceFacade.getBalanceByUserId(id);

                    tmpUser = new User(id, email, role, accountBalance);

                    System.out.println("temp user: " + tmpUser);

                   users.add(tmpUser);
                }
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException | UserException ex)
        {
            throw new UserException("Connection to database could not be established");
        }
        return users;
    }
}
