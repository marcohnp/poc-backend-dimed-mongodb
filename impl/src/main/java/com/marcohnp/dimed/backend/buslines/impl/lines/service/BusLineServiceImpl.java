package com.marcohnp.dimed.backend.buslines.impl.lines.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcohnp.dimed.backend.buslines.impl.lines.integration.BusLinesOperations;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BusLineServiceImpl {

    private BusLinesOperations operations;
    private final ObjectMapper objectMapper;
    private final RestTemplateBuilder restTemplateBuilder;

    public List<BusLine> findAllBusLines(){
        return operations.getAllBusLines(restTemplateBuilder.build(), objectMapper);
    }
}
