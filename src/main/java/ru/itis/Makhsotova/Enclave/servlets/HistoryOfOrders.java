package ru.itis.Makhsotova.Enclave.servlets;

import ru.itis.Makhsotova.Enclave.models.Order;
import ru.itis.Makhsotova.Enclave.models.User;
import ru.itis.Makhsotova.Enclave.repositories.ReservationRoomHotelRepository;
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

@WebServlet("/historyOrders")
public class HistoryOfOrders  extends HttpServlet {

    private ServletContext servletContext;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        orderService = (OrderService) servletContext.getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orderList = orderService.findAllUserReservation(user);
        session.setAttribute("orders", orderList);

        servletContext.getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(req, resp);
    }
}
