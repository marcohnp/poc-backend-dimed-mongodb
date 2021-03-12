package com.marcohnp.dimed.backend.buslines.impl.lines.facade;

import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.service.BusLineServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BusLineFacadeImpl {

    private final BusLineServiceImpl busLineService;

    public Page<BusLine> findAllBusLines(Pageable pageable){
        return busLineService.findAllBusLines(pageable);
    }

}
