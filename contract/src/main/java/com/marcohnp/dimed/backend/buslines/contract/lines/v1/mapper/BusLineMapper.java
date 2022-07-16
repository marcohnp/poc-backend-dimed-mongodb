package com.marcohnp.dimed.backend.buslines.contract.lines.v1.mapper;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineItinerary;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineLocationResponse;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLineLocation;
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

    public static BusLineItinerary mapToBusLineItinerary(BusLine busLine) {
        return BusLineItinerary.builder()
                .name(busLine.getName())
                .id(busLine.getId())
                .code(busLine.getCode())
                .coordinates(busLine.getCoordinates())
                .build();
    }

    public static BusLineLocationResponse mapToBusLineLocationResponse(BusLineLocation busLineLocation) {
        return BusLineLocationResponse.builder()
                .id(busLineLocation.getId())
                .idOnibus(busLineLocation.getIdOnibus())
                .ultimaPosicao(busLineLocation.getUltimaPosicao())
                .horario(busLineLocation.getHorario())
                .build();
    }
}
