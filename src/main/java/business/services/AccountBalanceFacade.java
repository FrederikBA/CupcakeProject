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
        accountBalanceMapper.setKredit(userId, balance);
    }



    /*public List<AccountBalanceMapper> getAllAcc() throws UserException {
     //   return AccountBalanceMapper.

    public void tmpAcc (){
    }
*/


}


/*
    public void addKredit(int id, double kredit) throws SQLException {
        new AccountBalanceMapper(database).setKredit(id, kredit);
    }

    private void addkredit(HttpServletRequest request) {
        String kundeId = request.getParameter("kunderID");
        String kundeKredit = request.getParameter("KreditToAdd");
        try {
            int parseID = Integer.parseInt(kundeId);
            double parseKredit = Double.parseDouble(kundeKredit);
            loginFacade.addKredit(parseID, parseKredit);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

    }*/
