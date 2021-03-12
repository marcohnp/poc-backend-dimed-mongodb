package com.marcohnp.dimed.backend.buslines.contract.lines.v1.mapper;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusLineMapper {

    public static BusLineResponse mapToResponse(BusLine busLine){
        return BusLineResponse.builder()
                .id(busLine.getId())
                .code(busLine.getCode())
                .name(busLine.getName())
                .build();
    }
}
