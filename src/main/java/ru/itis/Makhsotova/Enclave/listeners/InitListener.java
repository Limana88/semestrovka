package ru.itis.Makhsotova.Enclave.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.Makhsotova.Enclave.repositories.*;
import ru.itis.Makhsotova.Enclave.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class InitListener implements ServletContextListener {

    private  ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        context = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(context.getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));
        hikariConfig.setUsername(properties.getProperty("db.user"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver"));

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        HotelRepository hotelRepository = new HotelRepositoryJdbcImpl(dataSource);
        ReservationRoomHotelRepository roomHotelRepository = new ReservationRoomHotelRepositoryJdbcImpl(dataSource);
        SignUpService signUpService = new SignUpServiceImpl(userRepository);
        SignInService signInService = new SignInServiceImpl(userRepository);
        HotelService hotelService = new HotelServiceImpl(hotelRepository, roomHotelRepository);
        LogoutService logoutService = new LogoutServiceImpl();
        OrderService orderService = new OrderServiceImpl(roomHotelRepository);

        context.setAttribute("signUpService", signUpService);
        context.setAttribute("dataSource", dataSource);
        context.setAttribute("userRepository", userRepository);
        context.setAttribute("hotelRepository", hotelRepository);
        context.setAttribute("roomHotelRepository", roomHotelRepository);
        context.setAttribute("signInService", signInService);
        context.setAttribute("hotelService", hotelService);
        context.setAttribute("logoutService", logoutService);
        context.setAttribute("orderService", orderService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HikariDataSource hikariDataSource = (HikariDataSource) context.getAttribute("dataSource");
        hikariDataSource.close();
    }
}
