package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.ExpositionDAO;
import edu.demian.wp.model.dao.TicketDAO;
import edu.demian.wp.model.dto.Exposition;
import edu.demian.wp.model.dto.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class StatisticsExpoAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String expositionId = request.getParameter("exposition_id");

        List<Ticket> ticketList = new TicketDAO().findByExpositionId(Long.parseLong(expositionId));
        ticketList = Ticket.sort(ticketList);

        Exposition exposition = new ExpositionDAO().findById(Long.parseLong(expositionId));

        HttpSession session = request.getSession();
        session.setAttribute("ticketListStatistics", ticketList);
        session.setAttribute("exposition", exposition);

        return "/moderator/statsExpo";
    }
}
