package business.services;

import business.persistence.AccountBalanceMapper;
import business.persistence.Database;
import business.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * CREATED BY Janus @ 2021-04-20 - 14:06
 **/
public class AccountBalanceFacade {
    AccountBalanceMapper accountBalanceMapper;

         public AccountBalanceMapper(Database database) {
            AccountBalanceMapper = new AccountBalanceMapper(database);
        }

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

    }
    }
