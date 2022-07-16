package com.marcohnp.dimed.backend.buslines.impl.lines.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PositionBus {

    private Integer posicao;
    private Coordinates coordenadas;
}
