package com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub;

import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class BusLineStub {

    public static BusLine createBusLine(){
        return BusLine.builder()
                .id("1000")
                .code("1000-1")
                .name("Linha 1000")
                .build();
    }

    public static List<BusLine> createListBusLine() {
        var listBusLine = new ArrayList<BusLine>();
        listBusLine.add(createBusLine());
        return listBusLine;
    }
}
