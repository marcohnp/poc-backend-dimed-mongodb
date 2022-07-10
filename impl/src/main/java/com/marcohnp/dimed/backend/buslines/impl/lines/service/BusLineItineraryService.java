package com.marcohnp.dimed.backend.buslines.impl.lines.service;

import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineNotFoundException;
import com.marcohnp.dimed.backend.buslines.impl.lines.integration.BusLinesOperations;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.repository.BusLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusLineItineraryService {

    private final BusLineRepository repository;
    private final BusLinesOperations operations;

    public Page<BusLine> findAllBusLinesWithItinerary(Pageable pageable){
        Page<BusLine> buslines = repository.findAll(pageable);
        if (buslines == null || buslines.isEmpty()) throw new BusLineNotFoundException();
        return buslines;
    }

    public List<BusLine> findByCoordinates(double lat, double lng) {
        List<BusLine> buslines = repository.findAll();
        return operations.linhasPorRaio(buslines, lat, lng);
    }
}
