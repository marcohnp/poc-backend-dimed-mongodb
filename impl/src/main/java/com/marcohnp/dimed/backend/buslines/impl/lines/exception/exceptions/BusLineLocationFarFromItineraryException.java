package com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions;

public class BusLineLocationFarFromItineraryException extends RuntimeException {

    public BusLineLocationFarFromItineraryException(){
        super("Ônibus não está próximo de seu itinerário.");
    }
}
