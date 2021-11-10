package ru.itis.Makhsotova.Enclave.servlets;

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


@WebServlet("/chosenHotel")
public class ChosenHotelServlet extends HttpServlet {

    private ServletContext servletContext;
    private HotelService hotelService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        hotelService = (HotelService) servletContext.getAttribute("hotelService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        servletContext.getRequestDispatcher("/WEB-INF/jsp/chooseHotel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idHotel");

        if (id == null) {
            servletContext.getRequestDispatcher("/WEB-INF/jsp/chooseHotel.jsp").forward(req, resp);
        } else {
            Hotel hotel = hotelService.getHotelById(Integer.parseInt(id));
            HttpSession session = req.getSession();
            session.setAttribute("hotel", hotel);
            servletContext.getRequestDispatcher("/WEB-INF/jsp/hotel.jsp").forward(req, resp);
        }
    }
}
