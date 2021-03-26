package com.marcohnp.dimed.backend.buslines.impl.lines.stub;

import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;

public class BusLineStub {

    public static BusLine createBusLine(){
        return BusLine.builder()
                .id("1000")
                .code("1000-1")
                .name("Linha 1000")
                .build();
    }

    public static Page<BusLine> createListBusLine() {
        var listBusLine = new ArrayList<BusLine>();
        listBusLine.add(createBusLine());
        return new PageImpl<>(listBusLine);
    }
}
