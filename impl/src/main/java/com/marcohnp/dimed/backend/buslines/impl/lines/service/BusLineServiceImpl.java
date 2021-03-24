package com.marcohnp.dimed.backend.buslines.impl.lines.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcohnp.dimed.backend.buslines.impl.lines.integration.BusLinesOperations;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.repository.BusLinesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusLineServiceImpl {

    private final BusLinesOperations operations;
    private final BusLinesRepository repository;
    private final ObjectMapper objectMapper;
    private final RestTemplateBuilder restTemplateBuilder;


    public List<BusLine> findAllBusLines(){
        //Page<BusLine> busLines = new ArrayList<>(operations.getAllBusLines(restTemplateBuilder.build(), objectMapper));
       // busLines.forEach(repository::save);
        return repository.findAll();
    }
}
