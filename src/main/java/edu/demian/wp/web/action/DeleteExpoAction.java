package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.ExpositionDAO;
import edu.demian.wp.model.dao.HallDAO;
import edu.demian.wp.model.dto.Exposition;
import edu.demian.wp.model.dto.Hall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteExpoAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String expositionId = request.getParameter("exposition_id");

        Exposition exposition = new ExpositionDAO(DBManager.getInstance().getConnection()).findById(Long.parseLong(expositionId));
        for (Hall hall : exposition.getHallList()) {
            hall.setExpositionId(0);
            new HallDAO(DBManager.getInstance().getConnection()).update(hall);
        }

        new ExpositionDAO(DBManager.getInstance().getConnection()).delete(exposition.getId());

        return "redirect:/expos";
    }
}
