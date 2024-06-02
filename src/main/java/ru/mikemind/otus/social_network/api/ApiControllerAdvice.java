package ru.mikemind.otus.social_network.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mikemind.otus.social_network.exception.ResourceNotFoundException;
import ru.mikemind.otus.social_network.exception.UserNotAuthenticatedException;

@ControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler
    protected ResponseEntity<String> handleConflict(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<String> handleConflict(UserNotAuthenticatedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
