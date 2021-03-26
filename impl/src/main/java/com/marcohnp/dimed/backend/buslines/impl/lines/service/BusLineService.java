package com.marcohnp.dimed.backend.buslines.impl.lines.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineNotFoundException;
import com.marcohnp.dimed.backend.buslines.impl.lines.integration.BusLinesOperations;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.repository.BusLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BusLineService {

    private final BusLinesOperations operations;
    private final BusLineRepository repository;
    private final ObjectMapper objectMapper;
    private final RestTemplateBuilder restTemplateBuilder;


    public Page<BusLine> findAllBusLines(Pageable pageable){
//        List<BusLine> busLines = new ArrayList<>(operations.getAllItinerary(restTemplateBuilder.build(), objectMapper));
//        busLines.forEach(repository::save);
        Page<BusLine> buslines = repository.findAll(pageable);
        if (buslines == null || buslines.isEmpty()) throw new BusLineNotFoundException();
        return buslines;
    }
}
