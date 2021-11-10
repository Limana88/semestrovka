package ru.itis.Makhsotova.Enclave.services;

import javax.servlet.http.HttpSession;

public interface LogoutService {

    void logout(HttpSession session);
}
