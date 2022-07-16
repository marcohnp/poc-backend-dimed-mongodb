package com.marcohnp.dimed.backend.buslines.impl.lines.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "buslinesLocation")
public class BusLineLocation {

    @Id
    private String id;
    private String idOnibus;
    private PositionBus ultimaPosicao;
    private LocalDateTime horario;



}
