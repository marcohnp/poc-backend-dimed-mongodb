package com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.mapper.BusLineMapper;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;

public class BusLineStub {

    public static BusLine createBusLine(){
        return BusLine.builder()
                .id("1000")
                .code("1000-1")
                .name("Linha Mil")
                .build();
    }
}
