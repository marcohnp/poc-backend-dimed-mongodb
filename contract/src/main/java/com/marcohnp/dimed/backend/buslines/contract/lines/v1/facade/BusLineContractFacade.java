package com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.mapper.BusLineMapper;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineItinerary;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;
import com.marcohnp.dimed.backend.buslines.impl.lines.facade.BusLineImplFacade;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class BusLineContractFacade {

    private final BusLineImplFacade busLineImplFacade;

    public List<BusLineResponse> findAll(Pageable pageable){
        return busLineImplFacade.findAllBusLines(pageable).stream()
                .map(BusLineMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<BusLineItinerary> findAllBusLineWithItinerary(Pageable pageable) {
        return busLineImplFacade.findAllBusLinesWithItinerary(pageable).stream()
                .map(BusLineMapper::mapToBusLineItinerary)
                .collect(Collectors.toList());
    }
}
