package ru.itis.Makhsotova.Enclave.services;

import ru.itis.Makhsotova.Enclave.models.Order;
import ru.itis.Makhsotova.Enclave.models.User;
import ru.itis.Makhsotova.Enclave.repositories.ReservationRoomHotelRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderServiceImpl  implements OrderService{

    private ReservationRoomHotelRepository roomHotelRepository;

    public OrderServiceImpl(ReservationRoomHotelRepository roomHotelRepository) {
        this.roomHotelRepository = roomHotelRepository;
    }

    @Override
    public List<Order> findAllUserReservation(User user) {
        return roomHotelRepository.findAllUserReservation(user);
    }

    @Override
    public List<Order> cancelReservation(Integer orderId, HttpSession session) {
        Order currentOrder = new Order();
        Order or = new Order();
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                currentOrder = order;
                or = order;
                break;
            }
        }
        if (currentOrder.getReservationStatus().equals("active")) {
            currentOrder.setReservationStatus("inactive");
        } else {
            currentOrder.setReservationStatus("active");
        }
        roomHotelRepository.changeStatusReservation(orderId, currentOrder.getReservationStatus());
        orders.set(orders.indexOf(or),currentOrder);

        return orders;
    }
}
