package com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions;

public class BusLineExistException extends RuntimeException {

    public BusLineExistException(){
        super("Linha de Ônibus já existe.");
    }
}
