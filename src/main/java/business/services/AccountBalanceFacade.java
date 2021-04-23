package business.services;

import business.entities.AccountBalance;
import business.exceptions.UserException;
import business.persistence.AccountBalanceMapper;
import business.persistence.Database;
import business.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountBalanceFacade {
    AccountBalanceMapper accountBalanceMapper;
    private Database database;

    public AccountBalanceFacade(Database database) {
        this.database = database;
        this.accountBalanceMapper = new AccountBalanceMapper(database);
    }

    public AccountBalance getBalanceByUserId(int id) throws UserException {
        return accountBalanceMapper.getAccountBalanceByUserId(id);
    }

    public boolean changeBalance(int userId, double balance) throws SQLException {
        return accountBalanceMapper.updateBalance(userId, balance);
    }
}


