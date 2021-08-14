package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.AccountDAO;
import edu.demian.wp.model.dto.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        if (request.getMethod().equals("GET")) {
            return "login";
        }

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Account account = new AccountDAO(DBManager.getInstance().getConnection()).findByEmailAndPassword(email, password);

        if (account == null) {
            String errorMessage = "No user with such email address";
            return "error";
        }

        session.setAttribute("account", account);

        return "home";
    }
}
