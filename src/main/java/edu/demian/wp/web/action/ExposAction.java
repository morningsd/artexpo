package edu.demian.wp.web.action;

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
        HttpSession session = request.getSession();
        session.setAttribute("numOfRecords", 5);

        if (session.getAttribute("search") != null && session.getAttribute("search").equals("true")) {
            session.setAttribute("search", "false");
        } else {
            ExpositionDAO expositionDAO = new ExpositionDAO();

            long currentPage = 1L;
            if (request.getParameter("page") != null) {
                currentPage = Long.parseLong(request.getParameter("page"));
            }
            session.setAttribute("currentPage", currentPage);

            int limit = (int) session.getAttribute("numOfRecords");
            long offset = Exposition.getOffset(currentPage, limit);

            List<Exposition> expositionList = expositionDAO.findAll("DESC", "ASC", limit, offset);
            List<Category> categoryList = new CategoryDAO().findAll();

            long numOfRows = expositionDAO.getNumOfRowsFindAll();
            session.setAttribute("maxPage", Math.ceil((double) numOfRows / limit));

            session.setAttribute("expositionList", expositionList);
            session.setAttribute("categoryList", categoryList);
            session.setAttribute("checkedCategoryList", categoryList);
            session.setAttribute("dateOrder", "DESC");
            session.setAttribute("priceOrder", "ASC");
        }
        return "/expos";
    }
}
