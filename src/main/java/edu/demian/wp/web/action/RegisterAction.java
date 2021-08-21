package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.AccountDAO;
import edu.demian.wp.model.dto.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegisterAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        if (request.getMethod().equals("GET")) {
            return "/register";
        }

        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);

        account = new AccountDAO(DBManager.getInstance().getConnection()).create(account, password);

        return "redirect:/login";
    }

}
