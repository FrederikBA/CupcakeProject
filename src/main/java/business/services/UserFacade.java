package business.services;

import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;

import java.sql.SQLException;
import java.util.List;

public class UserFacade
{
    UserMapper userMapper;
    Database database;

    public UserFacade(Database database)
    {
        this.database = database;
        userMapper = new UserMapper(database);
    }

    public List<User> getAllUsers() throws UserException {
        return userMapper.getAllUsers();
    }

    public User login(String email, String password) throws UserException
    {
        return userMapper.login(email, password);
    }

    public User createUser(String email, String password) throws UserException
    {
        User user = new User(email, password, "customer");
        userMapper.createUser(user);
        return user;
    }

    public boolean changeBalance(int userId, double balance) throws UserException {
        AccountBalanceFacade abf = new AccountBalanceFacade(database);

        try {
            return abf.changeBalance(userId, balance);
        } catch (SQLException ex) {
            throw new UserException("Could not update balance");
        }
    }
}
