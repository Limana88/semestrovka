package ru.itis.Makhsotova.Enclave.Exceptions;

public class OccupiedEmailException extends RuntimeException{
    public OccupiedEmailException() {
    }

    public OccupiedEmailException(String message) {
        super(message);
    }

    public OccupiedEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public OccupiedEmailException(Throwable cause) {
        super(cause);
    }
}
