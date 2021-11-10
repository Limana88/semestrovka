package ru.itis.Makhsotova.Enclave.services;

import org.springframework.util.DigestUtils;
import ru.itis.Makhsotova.Enclave.Exceptions.FailedHashForPassword;
import ru.itis.Makhsotova.Enclave.Exceptions.InvalidEmailException;
import ru.itis.Makhsotova.Enclave.Exceptions.OccupiedEmailException;
import ru.itis.Makhsotova.Enclave.Exceptions.WeakPasswordException;
import ru.itis.Makhsotova.Enclave.dto.SignUpForm;
import ru.itis.Makhsotova.Enclave.models.User;
import ru.itis.Makhsotova.Enclave.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private User user;

    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UUID signUp(SignUpForm signUpForm, HttpSession session)  {
        user = User.builder()
                .firstName(signUpForm.getFirstName())
                .secondName(signUpForm.getLastName())
                .email(signUpForm.getEmail())
                .password(signUpForm.getPassword())
                .build();

        validateUser(user);
        if (user.getPassword().length() < 6) throw new WeakPasswordException("Password too short");
        try {
            String hashPassword = DigestUtils.md5DigestAsHex(new ByteArrayInputStream(user.getPassword().getBytes(StandardCharsets.UTF_8)));
            user.setPassword(hashPassword);
        } catch (IOException ex) {
            throw new FailedHashForPassword(ex);
        }
        UUID uuid = UUID.randomUUID();
         userRepository.save(user);
         userRepository.addUUID(user, uuid);
         session.setAttribute("user", user);
         return uuid;
    }

    public void validateUser(User user) {
        Matcher matcher = Pattern.compile(EMAIL_REGEX).matcher(user.getEmail());
            if(!matcher.matches()){
                throw new InvalidEmailException(user.getEmail() + " is not email");
            }
            if(userRepository.findByEmail(user.getEmail()).isPresent()){
                throw new OccupiedEmailException(user.getEmail() + " is occupied");
            }
    }

    @Override
    public User getUser() {
        return user;
    }
}
