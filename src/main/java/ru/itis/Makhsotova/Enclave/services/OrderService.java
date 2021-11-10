package ru.itis.Makhsotova.Enclave.services;

import ru.itis.Makhsotova.Enclave.models.Order;
import ru.itis.Makhsotova.Enclave.models.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {

        List<Order> findAllUserReservation(User user);
        List<Order> cancelReservation(Integer orderId, HttpSession session);
}
