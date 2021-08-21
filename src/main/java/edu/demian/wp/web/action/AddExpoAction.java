package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.CategoryDAO;
import edu.demian.wp.model.dao.ExpositionDAO;
import edu.demian.wp.model.dao.HallDAO;
import edu.demian.wp.model.dto.Category;
import edu.demian.wp.model.dto.Exposition;
import edu.demian.wp.model.dto.Hall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddExpoAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        if (request.getMethod().equals("GET")) {
            List<Exposition> expositionList = new ExpositionDAO(DBManager.getInstance().getConnection()).findAll();
            List<Category> categoryList = new CategoryDAO(DBManager.getInstance().getConnection()).findAll();
            List<Hall> freeHallList = new HallDAO(DBManager.getInstance().getConnection()).findFreeHalls();

            HttpSession session = request.getSession();
            session.setAttribute("expositionList", expositionList);
            session.setAttribute("categoryList", categoryList);
            session.setAttribute("freeHallList", freeHallList);

            return "/moderator/addExpo";
        }

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
        exposition.setTopic(topic);
        exposition.setDescription(description);
        exposition.setStartDate(LocalDate.parse(startDate, formatter));
        exposition.setEndDate(LocalDate.parse(endDate, formatter));
        exposition.setStartWorkTime(LocalTime.parse(startTime));
        exposition.setEndWorkTime(LocalTime.parse(endTime));
        exposition.setCategoryId(Long.parseLong(categoryId));
        exposition.setPrice(new BigDecimal(price));

        exposition = new ExpositionDAO(DBManager.getInstance().getConnection()).create(exposition);

        for (String hallStr : halls) {
            Hall hall = new HallDAO(DBManager.getInstance().getConnection()).findById(Long.parseLong(hallStr));
            hall.setExpositionId(exposition.getId());
            new HallDAO(DBManager.getInstance().getConnection()).update(hall);
        }

        return "redirect:/home";
    }
}
