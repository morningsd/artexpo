package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.TicketDAO;
import edu.demian.wp.model.dto.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class VisitExpoAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String expositionId = request.getParameter("exposition_id");
        String accountId = request.getParameter("account_id");
        String quantity = request.getParameter("quantity");
        String expositionPrice = request.getParameter("exposition_price");

        Ticket ticket = new Ticket();
        ticket.setExpositionId(Long.parseLong(expositionId));
        ticket.setAccountId(Long.parseLong(accountId));
        ticket.setQuantity(Integer.parseInt(quantity));
        ticket.setTotalPrice(new BigDecimal(expositionPrice).multiply(new BigDecimal(quantity)));

        ticket = new TicketDAO().create(ticket);

        return "redirect:/expos";
    }
}
