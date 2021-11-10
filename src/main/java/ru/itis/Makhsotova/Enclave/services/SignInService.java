package ru.itis.Makhsotova.Enclave.services;

import ru.itis.Makhsotova.Enclave.dto.SignInForm;
import ru.itis.Makhsotova.Enclave.models.User;
import ru.itis.Makhsotova.Enclave.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public interface SignInService  extends Service{

    boolean signIn(SignInForm signInForm, HttpSession session);
    UUID getUUID(User user);
    boolean  isAuthenticated(HttpServletRequest request, HttpSession session);

}
