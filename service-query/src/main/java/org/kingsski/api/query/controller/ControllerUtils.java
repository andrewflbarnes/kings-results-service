package org.kingsski.api.query.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerUtils {

    private ControllerUtils() {}

    public static <T> ResponseEntity<T> asResponseEntityOk(T body) {
        return asResponseEntity(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> asResponseEntity(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    public static <T> ResponseEntity<T> asResponseEntityOk(Class<T> type, T body) {
        return asResponseEntity(type, body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> asResponseEntity(Class<T> type, T body, HttpStatus status) {
        return new ResponseEntity<>(type.cast(body), status);
    }
}
