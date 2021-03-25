package com.marcohnp.dimed.backend.buslines.impl.lines.facade;

import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.service.BusLineItineraryService;
import com.marcohnp.dimed.backend.buslines.impl.lines.service.BusLineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BusLineImplFacade {

    private final BusLineService busLineService;
    private final BusLineItineraryService busLineItineraryService;

    public Page<BusLine> findAllBusLines(Pageable pageable){
        return busLineService.findAllBusLines(pageable);
    }

    public Page<BusLine> findAllBusLinesWithItinerary(Pageable pageable){
        return busLineItineraryService.findAllBusLinesWithItinerary(pageable);
    }

}
