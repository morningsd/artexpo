package edu.demian.wp.web.filter;

import edu.demian.wp.model.dto.Role;
import edu.demian.wp.web.action.ActionFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComandAccessFilter extends HttpFilter {

    private static Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
    private static List<String> moderator = new ArrayList<>();
    private static List<String> client = new ArrayList<>();
    private static List<String> all = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        moderator.add("addExpo");
        moderator.add("statsExpo");
        moderator.add("updateExpo");

        client.add("cabinet");

        all.add("search");
        all.add("error");
        all.add("expos");
        all.add("home");
        all.add("login");
        all.add("register");

        accessMap.put(Role.MODERATOR, moderator);
        accessMap.put(Role.CLIENT, client);
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (accessAllowed(request)) {
            super.doFilter(request, response, chain);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";
            request.setAttribute("errorMessage", errorMessage);

            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    private boolean accessAllowed(HttpServletRequest request) {
        String path = request.getPathInfo();
        if (path == null) {
            return true;
        }
        String action = path.substring(path.lastIndexOf('/') + 1);


        if (ActionFactory.getAction(request) == null) {
            return false;
        }

        if (all.contains(action)) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        Role accountRole = (Role) session.getAttribute("accountRole");
        if (accountRole == null) {
            return false;
        }

        if (accountRole.equals(Role.MODERATOR)) {
            return true;
        }

        if (accountRole.equals(Role.CLIENT)) {
            if (moderator.contains(action)) {
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
