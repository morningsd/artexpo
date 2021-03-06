package edu.demian.wp.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionLocaleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getParameter("language") != null) {
            request.getSession().setAttribute("lang", request.getParameter("language"));
        }
        chain.doFilter(request, response);
    }
}
