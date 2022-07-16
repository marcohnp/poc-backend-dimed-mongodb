package com.marcohnp.dimed.backend.buslines.impl.lines.exception.handler;

import com.marcohnp.dimed.backend.buslines.impl.lines.exception.error.StandardError;
import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineExistException;
import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineLocationFarFromItineraryException;
import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineLocationNotFoundException;
import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;


@ControllerAdvice
public class Handler {

    @ExceptionHandler(BusLineNotFoundException.class)
    public ResponseEntity<StandardError> linhasOnibusNotFound(BusLineNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardError.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("Request not completed.")
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusLineLocationNotFoundException.class)
    public ResponseEntity<StandardError> busLineLocationNotFound(BusLineLocationNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardError.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("Request not completed.")
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusLineExistException.class)
    public ResponseEntity<StandardError> linhasOnibusNotCreated(BusLineExistException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardError.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Request not completed.")
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusLineLocationFarFromItineraryException.class)
    public ResponseEntity<StandardError> busLineLocationFarFromItinerary(BusLineLocationFarFromItineraryException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardError.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Request not completed.")
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
