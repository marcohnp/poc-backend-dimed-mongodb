package com.marcohnp.dimed.backend.buslines.impl.lines.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcohnp.dimed.backend.buslines.impl.lines.integration.BusLinesOperations;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.repository.BusLinesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusLineItineraryService {

    private final BusLinesRepository repository;

    public Page<BusLine> findAllBusLinesWithItinerary(Pageable pageable){
        return repository.findAll(pageable);
    }
}
