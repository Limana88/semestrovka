package ru.itis.Makhsotova.Enclave.Exceptions;

public class InvalidDataForDBException extends RuntimeException{

    public InvalidDataForDBException() {
    }

    public InvalidDataForDBException(String message) {
        super(message);
    }

    public InvalidDataForDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataForDBException(Throwable cause) {
        super(cause);
    }
}

