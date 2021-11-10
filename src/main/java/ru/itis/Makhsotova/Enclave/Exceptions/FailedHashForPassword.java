package ru.itis.Makhsotova.Enclave.Exceptions;

public class FailedHashForPassword extends RuntimeException{
    public FailedHashForPassword() {
    }

    public FailedHashForPassword(String message) {
        super(message);
    }

    public FailedHashForPassword(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedHashForPassword(Throwable cause) {
        super(cause);
    }
}
