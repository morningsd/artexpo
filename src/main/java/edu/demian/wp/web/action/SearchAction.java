package edu.demian.wp.web.action;

import edu.demian.wp.model.dao.CategoryDAO;
import edu.demian.wp.model.dao.ExpositionDAO;
import edu.demian.wp.model.dto.Category;
import edu.demian.wp.model.dto.Exposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        List<Category> checkedCategoryList = new ArrayList<>();
        ExpositionDAO expositionDAO = new ExpositionDAO();

        long currentPage = 1L;
        session.setAttribute("currentPage", currentPage);

        int limit = (int) session.getAttribute("numOfRecords");
        long offset = Exposition.getOffset(currentPage, limit);
        long numOfRows = 0;

        if (searchData.isBlank() || searchData.isEmpty()) {
            if (checkedCategories == null) {
                numOfRows = 0;
            } else {
                if (categoryList.size() == checkedCategories.length) {
                    expositionList = expositionDAO.findAll(dateOrder, priceOrder, limit, offset);
                    numOfRows = expositionDAO.getNumOfRowsFindAll();
                } else {
                    long[] checkedCategoriesId = Stream.of(checkedCategories)
                            .mapToLong(Long::parseLong).toArray();
                    expositionList = expositionDAO.findByCategory(checkedCategoriesId, dateOrder, priceOrder, limit, offset);

                    numOfRows = expositionDAO.getNumOfRowsFindByCategory(checkedCategoriesId);
                }
            }
        } else {
            if (checkedCategories == null) {
                numOfRows = 0;
            } else {
                if (categoryList.size() == checkedCategories.length) {
                    expositionList = expositionDAO.findByTopic(searchData, dateOrder, priceOrder, limit, offset);

                    numOfRows = expositionDAO.getNumOfRowsFindByTopic(searchData);
                } else {
                    long[] checkedCategoriesId = Stream.of(checkedCategories)
                            .mapToLong(Long::parseLong).toArray();
                    expositionList = expositionDAO.findByTopicAndCategory(searchData, checkedCategoriesId, dateOrder, priceOrder, limit, offset);

                    numOfRows = expositionDAO.getNumOfRowsFindByTopicAndCategory(searchData, checkedCategoriesId);
                }
            }
        }

        session.setAttribute("maxPage", Math.ceil((double) numOfRows / limit));

        if (checkedCategories != null) {
            for (String category : checkedCategories) {
                checkedCategoryList.add(new CategoryDAO().findById(Long.parseLong(category)));
            }
        }

        session.setAttribute("expositionList", expositionList);
        session.setAttribute("checkedCategoryList", checkedCategoryList);
        session.setAttribute("priceOrder", priceOrder);
        session.setAttribute("dateOrder", dateOrder);
        session.setAttribute("searchData", searchData);
        session.setAttribute("search", "true");

        return "redirect:/expos";
    }
}
