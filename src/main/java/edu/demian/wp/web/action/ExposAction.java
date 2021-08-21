package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.CategoryDAO;
import edu.demian.wp.model.dao.ExpositionDAO;
import edu.demian.wp.model.dto.Category;
import edu.demian.wp.model.dto.Exposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ExposAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        List<Category> categoryList = new CategoryDAO(DBManager.getInstance().getConnection()).findAll();

        HttpSession session = request.getSession();
        session.setAttribute("categoryList", categoryList);

        if (session.getAttribute("search") != null && session.getAttribute("search").equals("true")) {
            session.setAttribute("search", "false");
        } else {
            List<Exposition> expositionList = new ExpositionDAO(DBManager.getInstance().getConnection()).findAll();
            session.setAttribute("expositionList", expositionList);
        }

        return "/expos";
    }
}
