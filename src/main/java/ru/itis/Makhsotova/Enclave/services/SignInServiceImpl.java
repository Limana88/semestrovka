package ru.itis.Makhsotova.Enclave.services;

import org.springframework.util.DigestUtils;
import ru.itis.Makhsotova.Enclave.Exceptions.FailedHashForPassword;
import ru.itis.Makhsotova.Enclave.Exceptions.NoSuchEmailException;
import ru.itis.Makhsotova.Enclave.dto.SignInForm;
import ru.itis.Makhsotova.Enclave.models.User;
import ru.itis.Makhsotova.Enclave.repositories.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

public class SignInServiceImpl implements SignInService{

    private UserRepository userRepository;
    private User user;


    public SignInServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signIn(SignInForm signInForm, HttpSession session) {
        Optional<User> userOptional = userRepository.findByEmail(signInForm.getEmail());

        if (!userOptional.isPresent()) {
           throw new NoSuchEmailException();
        }
       user = userOptional.get();
        try {
            String hashPassword = DigestUtils.md5DigestAsHex(new ByteArrayInputStream(signInForm.getPassword().getBytes(StandardCharsets.UTF_8)));
            signInForm.setPassword(hashPassword);
        } catch (IOException ex) {
            throw new FailedHashForPassword(ex);
        }

        session.setAttribute("user", user);
        return user.getPassword().equals(signInForm.getPassword());
    }

    @Override
    public UUID getUUID(User user) {
        UUID uuid = UUID.randomUUID();
        userRepository.addUUID(user, uuid);
        return uuid;
    }

    @Override
    public User getUser() {
            return user;
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("user") != null){
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals(SIGN_UP_COOKIE_NAME)){
                    User user = userRepository.getProfileByUUID(UUID.fromString(cookie.getValue()));
                    if(user != null){
                        session.setAttribute("user", user);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

