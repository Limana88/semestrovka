package ru.itis.Makhsotova.Enclave.services;

import ru.itis.Makhsotova.Enclave.models.Hotel;
import ru.itis.Makhsotova.Enclave.models.User;

import java.sql.Date;
import java.util.List;

public interface HotelService {

    List<Hotel> displayAllHotels();
    Hotel getHotelById(Integer id);
    Hotel reservationRoomHotel(User user, Hotel hotel, Date checkInDate, Date checkOutDate);
    Hotel updateNumberFreeRooms(Hotel hotel);
}
