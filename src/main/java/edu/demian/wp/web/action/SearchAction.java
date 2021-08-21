package edu.demian.wp.web.action;

import edu.demian.wp.model.DBManager;
import edu.demian.wp.model.dao.ExpositionDAO;
import edu.demian.wp.model.dto.Category;
import edu.demian.wp.model.dto.Exposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SearchAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String searchData = request.getParameter("search_data");
        String[] checkedCategories = request.getParameterValues("search_categories");
        String priceOrder = request.getParameter("search_price");
        String dateOrder = request.getParameter("search_date");

        HttpSession session = request.getSession();
        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");
        List<Exposition> expositionList = new ArrayList<>();

        if (searchData.isBlank() || searchData.isEmpty()) {
            if (categoryList.size() == checkedCategories.length) {
                expositionList = new ExpositionDAO(DBManager.getInstance().getConnection()).findAll();
            } else {
                for (String category : checkedCategories) {
                    List<Exposition> expositionList1 = new ExpositionDAO(DBManager.getInstance().getConnection()).findByCategory(Long.parseLong(category));
                    if (expositionList1 != null) {
                        expositionList.addAll(expositionList1);
                    }
                }
            }
        } else {
            if (categoryList.size() == checkedCategories.length) {
                expositionList = new ExpositionDAO(DBManager.getInstance().getConnection()).findByTopic(searchData);
            } else {
                for (String category : checkedCategories) {
                    List<Exposition> expositionList1 = new ExpositionDAO(DBManager.getInstance().getConnection()).findByTopicAndCategory(searchData, Long.parseLong(category));
                    if (expositionList1 != null) {
                        expositionList.addAll(expositionList1);
                    }
                }
            }
        }
        expositionList = Exposition.sort(expositionList, priceOrder, dateOrder);

        session.setAttribute("expositionList", expositionList);
        session.setAttribute("search", "true");

        return "redirect:/expos";
    }
}
