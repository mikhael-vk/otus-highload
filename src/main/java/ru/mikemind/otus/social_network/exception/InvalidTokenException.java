package ru.mikemind.otus.social_network.exception;

public class InvalidTokenException extends UserNotAuthenticatedException {

    public InvalidTokenException(Throwable cause) {
        super(cause);
    }
}
