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

/**
 * CREATED BY Janus @ 2021-04-20 - 14:06
 **/
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

    public void changeBalance(int userId, double balance) throws SQLException {
        accountBalanceMapper.updateBalance(userId, balance);
    }



}


