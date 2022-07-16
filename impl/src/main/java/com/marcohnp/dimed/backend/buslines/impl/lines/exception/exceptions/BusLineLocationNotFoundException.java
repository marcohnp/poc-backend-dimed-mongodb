package com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions;

public class BusLineLocationNotFoundException extends RuntimeException {

    public BusLineLocationNotFoundException(){
        super("Ônibus não encontrado.");
    }
}
