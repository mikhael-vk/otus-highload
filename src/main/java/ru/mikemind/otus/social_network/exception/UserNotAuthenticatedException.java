package ru.mikemind.otus.social_network.exception;

public class UserNotAuthenticatedException extends RuntimeException {

    public UserNotAuthenticatedException(Throwable cause) {
        super(cause);
    }

    public UserNotAuthenticatedException() {
        super("User not authenticated: invalid id or password");
    }
}
