package ru.itis.Makhsotova.Enclave.servlets;

import ru.itis.Makhsotova.Enclave.Exceptions.InvalidDataForDBException;
import ru.itis.Makhsotova.Enclave.models.Hotel;
import ru.itis.Makhsotova.Enclave.models.User;
import ru.itis.Makhsotova.Enclave.services.HotelService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/hotel")
public class HotelServlet extends HttpServlet {

    private ServletContext servletContext;
    private HotelService hotelService;
    private HttpSession session;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        hotelService = (HotelService) servletContext.getAttribute("hotelService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletContext.getRequestDispatcher("/WEB-INF/jsp/chooseHotel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession(false);
        Hotel hotel = (Hotel) session.getAttribute("hotel");
        User user = (User) session.getAttribute("user");

        Date checkInDate = Date.valueOf(req.getParameter("checkInDate"));
        Date checkOutDate = Date.valueOf(req.getParameter("checkOutDate"));

        try {
            Hotel updatedHotel = hotelService.reservationRoomHotel(user, hotel, checkInDate, checkOutDate);
            session.removeAttribute("hotel");
            session.setAttribute("hotel", updatedHotel);
        } catch (InvalidDataForDBException ex) {
            req.setAttribute("message", "The date is entered incorrectly, " +
                    "please select the check-out date no earlier than the check-in date\n");
        }
        servletContext.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);

    }

}
