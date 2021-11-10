package ru.itis.Makhsotova.Enclave.repositories;

import ru.itis.Makhsotova.Enclave.models.Hotel;
import ru.itis.Makhsotova.Enclave.models.Order;
import ru.itis.Makhsotova.Enclave.models.User;

import java.sql.Date;
import java.util.List;

public interface ReservationRoomHotelRepository {

    public final String STATUS_ACTIVE = "active";
    public final String STATUS_INACTIVE = "inactive";

    Integer reservationRoom(User user, Hotel hotel, Date checkInDate, Date checkOutDate);
    List<Order> findAllUserReservation(User user);
    void changeStatusReservation(Integer orderId, String status);
    void updateNumberFreeRooms(Hotel hotel);
}
