package com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BusLineResponse {

    private String id;
    private String code;
    private String name;

}
