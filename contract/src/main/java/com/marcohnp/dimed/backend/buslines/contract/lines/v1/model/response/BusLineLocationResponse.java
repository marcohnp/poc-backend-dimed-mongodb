package com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response;

import com.marcohnp.dimed.backend.buslines.impl.lines.model.PositionBus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BusLineLocationResponse {

    private String id;
    private String idOnibus;
    private String idLinha;
    private String nomeLinha;
    private PositionBus ultimaPosicao;
    private LocalDateTime horario;
}
