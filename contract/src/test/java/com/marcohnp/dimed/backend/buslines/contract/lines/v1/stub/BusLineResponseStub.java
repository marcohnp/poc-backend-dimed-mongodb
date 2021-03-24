package com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;

import java.util.ArrayList;
import java.util.List;

public class BusLineResponseStub {

    public static BusLineResponse createBusLineResponse(){
        return BusLineResponse.builder()
                .id("1000")
                .code("1000-1")
                .name("Linha 1000")
                .build();
    }

    public static List<BusLineResponse> createListBusLineResponse(){
        var listBusLineResponse = new ArrayList<BusLineResponse>();
        listBusLineResponse.add(createBusLineResponse());
        return listBusLineResponse;
    }
}
