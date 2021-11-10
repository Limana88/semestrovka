package ru.itis.Makhsotova.Enclave.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("isAuthenticated").equals(true)) {
            req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(servletContext.getContextPath() + "/signIn");
        }
    }
}
