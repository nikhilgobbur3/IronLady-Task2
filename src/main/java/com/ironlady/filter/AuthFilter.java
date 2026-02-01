package com.ironlady.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginURI = req.getContextPath() + "/login";
        String cssURI = req.getContextPath() + "/css";
        String jsURI = req.getContextPath() + "/js";

        boolean loggedIn = (session != null && session.getAttribute("admin") != null);
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean resourceRequest = req.getRequestURI().startsWith(cssURI) || req.getRequestURI().startsWith(jsURI);

        if (loggedIn || loginRequest || resourceRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
    }
}
