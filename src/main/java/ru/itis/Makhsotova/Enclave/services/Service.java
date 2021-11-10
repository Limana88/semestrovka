package ru.itis.Makhsotova.Enclave.services;

import ru.itis.Makhsotova.Enclave.models.User;

public interface Service {
    public static final String SIGN_UP_COOKIE_NAME = "user_uuid";

    User getUser();

}
