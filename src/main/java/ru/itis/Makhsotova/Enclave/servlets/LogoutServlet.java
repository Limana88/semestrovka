package ru.itis.Makhsotova.Enclave.servlets;

import ru.itis.Makhsotova.Enclave.services.LogoutService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private ServletContext servletContext;
    private LogoutService logoutService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        logoutService = (LogoutService) servletContext.getAttribute("logoutService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logoutService.logout(req.getSession());
       servletContext.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
    }
}
