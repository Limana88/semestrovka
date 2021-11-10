package ru.itis.Makhsotova.Enclave.repositories;

import ru.itis.Makhsotova.Enclave.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(String email) ;
    int addUUID(User user, UUID uuid);
   User getProfileByUUID(UUID uuid);
}
