package ru.itis.Makhsotova.Enclave.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ru.itis.Makhsotova.Enclave.Exceptions.InvalidDataForDBException;
import ru.itis.Makhsotova.Enclave.Exceptions.NoSuchHotelException;
import ru.itis.Makhsotova.Enclave.models.Hotel;
import ru.itis.Makhsotova.Enclave.models.User;
import ru.itis.Makhsotova.Enclave.repositories.HotelRepository;
import ru.itis.Makhsotova.Enclave.repositories.ReservationRoomHotelRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class HotelServiceImpl  implements HotelService {

    private HotelRepository hotelRepository;
    private ReservationRoomHotelRepository roomHotelRepository;
    private Hotel hotel;

    public HotelServiceImpl(HotelRepository hotelRepository, ReservationRoomHotelRepository reservationRoomHotelRepository) {
        this. hotelRepository = hotelRepository;
        this.roomHotelRepository = reservationRoomHotelRepository;
    }
    @Override
    public List<Hotel> displayAllHotels() {
        return hotelRepository.findAllHotel();
    }

    @Override
    public Hotel reservationRoomHotel(User user, Hotel hotel, Date checkInDate, Date checkIOutDate) {

        try {
            roomHotelRepository.reservationRoom(user, hotel, checkInDate, checkIOutDate);
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataForDBException(ex);
        }
        return updateNumberFreeRooms(hotel);
    }

    @Override
    public Hotel getHotelById(Integer id) {
        Optional<Hotel> hotelOptional = hotelRepository.getHotelById(id);

        if (!hotelOptional.isPresent()) {
            throw new NoSuchHotelException();
        }

        hotel = hotelOptional.get();

        return hotel;
    }

    @Override
    public Hotel updateNumberFreeRooms(Hotel hotel) {
        roomHotelRepository.updateNumberFreeRooms(hotel);
        hotel.setNumberFreeRooms(hotel.getNumberFreeRooms() - 1);
        return hotel;
    }
}
