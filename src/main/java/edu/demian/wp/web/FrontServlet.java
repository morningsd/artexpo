package edu.demian.wp.web;

import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Action action = ActionFactory.getAction(request);
            String view = action.execute(request, response);

            // Some PRG stuff
            if (view.equals(request.getPathInfo().substring(1))) {
                request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp").forward(request, response);
            } else {
                response.sendRedirect(view); // We'd like to fire redirect in case of a view change as result of the action (PRG pattern).
            }
        } catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
