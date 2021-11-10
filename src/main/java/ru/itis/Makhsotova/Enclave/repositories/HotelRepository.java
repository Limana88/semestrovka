package ru.itis.Makhsotova.Enclave.repositories;

import ru.itis.Makhsotova.Enclave.models.Hotel;
import ru.itis.Makhsotova.Enclave.models.User;

import java.util.List;
import java.util.Optional;

public interface HotelRepository {

    List<Hotel>  findAllHotel();
    Optional<Hotel> getHotelById(Integer id);
}
