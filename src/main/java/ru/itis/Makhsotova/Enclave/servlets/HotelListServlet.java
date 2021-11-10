package ru.itis.Makhsotova.Enclave.servlets;

import ru.itis.Makhsotova.Enclave.models.Hotel;
import ru.itis.Makhsotova.Enclave.services.HotelService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hotelList")
public class HotelListServlet extends HttpServlet {

    private ServletContext servletContext;
    private HotelService hotelService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        hotelService = (HotelService) servletContext.getAttribute("hotelService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Hotel> hotelList = hotelService.displayAllHotels();
        req.setAttribute("hotelsForJSP", hotelList);
        servletContext.getRequestDispatcher("/WEB-INF/jsp/chooseHotel.jsp").forward(req, resp);
    }
}
