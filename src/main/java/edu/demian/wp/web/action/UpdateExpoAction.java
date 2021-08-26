package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.ExpositionDAO;
import edu.demian.wp.model.dao.HallDAO;
import edu.demian.wp.model.dto.Exposition;
import edu.demian.wp.model.dto.Hall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UpdateExpoAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        if (request.getMethod().equals("GET")) {
            String expositionId = request.getParameter("exposition_id");
            Exposition exposition = new ExpositionDAO().findById(Long.parseLong(expositionId));
            HallDAO hallDAO = new HallDAO();
            List<Hall> hallList = hallDAO.findAll();
            List<Hall> freeHallList = hallDAO.findFreeHalls();

            request.setAttribute("exposition", exposition);
            request.setAttribute("hallList", hallList);
            request.setAttribute("freeHallList", freeHallList);

            return "/moderator/updateExpo";
        }

        String id = request.getParameter("id");
        String topic = request.getParameter("topic");
        String description = request.getParameter("description");
        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");
        String startTime = request.getParameter("start_time");
        String endTime = request.getParameter("end_time");
        String categoryId = request.getParameter("category");
        String price = request.getParameter("price");
        String[] halls = request.getParameterValues("halls");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        Exposition exposition = new Exposition();
        exposition.setId(Long.parseLong(id));
        exposition.setTopic(topic);
        exposition.setDescription(description);
        exposition.setStartDate(LocalDate.parse(startDate, formatter));
        exposition.setEndDate(LocalDate.parse(endDate, formatter));
        exposition.setStartWorkTime(LocalTime.parse(startTime));
        exposition.setEndWorkTime(LocalTime.parse(endTime));
        exposition.setCategoryId(Long.parseLong(categoryId));
        exposition.setPrice(new BigDecimal(price));

        exposition = new ExpositionDAO().update(exposition);
        HallDAO hallDAO = new HallDAO();

        List<Hall> expositionHallList = hallDAO.expositionHallList(exposition.getId());

        for (String hallStr : halls) {
            Hall hall = hallDAO.findById(Long.parseLong(hallStr));
            if (expositionHallList.contains(hall)) {
                expositionHallList.remove(hall);
            } else {
                hall.setExpositionId(exposition.getId());
                hallDAO.update(hall);
            }
        }

        for (Hall hall : expositionHallList) {
            hall.setExpositionId(0);
            hallDAO.update(hall);
        }

        return "redirect:/expos";
    }
}
