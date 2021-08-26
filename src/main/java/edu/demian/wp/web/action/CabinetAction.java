package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.TicketDAO;
import edu.demian.wp.model.dto.Account;
import edu.demian.wp.model.dto.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CabinetAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        Account account = (Account) session.getAttribute("account");

        List<Ticket> ticketList = new TicketDAO().findByAccountId(account.getId());

        ticketList = Ticket.sort(ticketList);

        session.setAttribute("ticketList", ticketList);

        return "/client/cabinet";
    }
}
