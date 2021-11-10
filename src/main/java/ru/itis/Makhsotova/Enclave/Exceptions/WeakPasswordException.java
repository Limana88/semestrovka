package ru.itis.Makhsotova.Enclave.Exceptions;

public class WeakPasswordException extends RuntimeException {

    public WeakPasswordException() {
    }

    public WeakPasswordException(String message) {
        super(message);
    }

    public WeakPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeakPasswordException(Throwable cause) {
        super(cause);
    }
}