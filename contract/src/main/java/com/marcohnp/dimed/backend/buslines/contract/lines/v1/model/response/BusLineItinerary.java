package com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusLineItinerary {

    @JsonProperty("id")
    private String id;
    @JsonProperty("codigo")
    private String code;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("coordenadas")
    private List<Coordinates> coordinates;
}
