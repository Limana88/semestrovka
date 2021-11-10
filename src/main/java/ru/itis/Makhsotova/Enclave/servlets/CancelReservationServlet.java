package ru.itis.Makhsotova.Enclave.servlets;

import ru.itis.Makhsotova.Enclave.models.Order;
import ru.itis.Makhsotova.Enclave.services.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/cancelReservation")
public class CancelReservationServlet extends HttpServlet {

    private ServletContext servletContext;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        orderService = (OrderService) servletContext.getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idOrder");
        System.out.println(id);
        HttpSession session = req.getSession();
        if (id == null) {
            servletContext.getRequestDispatcher("/WEB-INF/jsp/orders.jsp");
        } else {
            List<Order> newOrders = orderService.cancelReservation(Integer.parseInt(id), session);
            session.removeAttribute("orders");
            session.setAttribute("orders", newOrders);
            req.getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(req, resp);
        }
    }
}
