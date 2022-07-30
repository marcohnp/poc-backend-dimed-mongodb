package com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions;

public class BusLineNotFoundException extends RuntimeException {

    public BusLineNotFoundException(){
        super("Linhas de Ônibus não encontradas.");
    }

    public BusLineNotFoundException(String message) {
        super(message);
    }
}
