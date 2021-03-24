package com.marcohnp.dimed.backend.buslines.impl.lines.facade;

import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.service.BusLineServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BusLineImplFacade {

    private final BusLineServiceImpl busLineService;

    public List<BusLine> findAllBusLines(){
        return busLineService.findAllBusLines();
    }

}
