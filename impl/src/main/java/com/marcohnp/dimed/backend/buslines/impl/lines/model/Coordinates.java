package com.marcohnp.dimed.backend.buslines.impl.lines.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates implements Serializable {
    private double lat;
    private double lng;
}
