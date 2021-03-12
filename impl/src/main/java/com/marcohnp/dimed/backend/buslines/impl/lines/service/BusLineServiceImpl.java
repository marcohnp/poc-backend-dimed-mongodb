package com.marcohnp.dimed.backend.buslines.impl.lines.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcohnp.dimed.backend.buslines.impl.lines.integration.BusLinesOperations;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.repository.BusLinesRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BusLineServiceImpl {

    private final BusLinesOperations operations;
    private final BusLinesRepository repository;
    private final ObjectMapper objectMapper;
    private final RestTemplateBuilder restTemplateBuilder;


    public Page<BusLine> findAllBusLines(Pageable pageable){
        //Page<BusLine> busLines = new ArrayList<>(operations.getAllBusLines(restTemplateBuilder.build(), objectMapper));
       // busLines.forEach(repository::save);
        return repository.findAll(pageable);
    }
}
