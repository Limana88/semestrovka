package ru.itis.Makhsotova.Enclave.Exceptions;

public class NoSuchHotelException  extends RuntimeException{

    public NoSuchHotelException() {
    }

    public NoSuchHotelException(String message) {
        super(message);
    }

    public NoSuchHotelException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchHotelException(Throwable cause) {
        super(cause);
    }
}
