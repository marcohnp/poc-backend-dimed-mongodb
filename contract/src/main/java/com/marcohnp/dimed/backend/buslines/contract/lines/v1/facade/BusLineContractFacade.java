package com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.mapper.BusLineMapper;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;
import com.marcohnp.dimed.backend.buslines.impl.lines.facade.BusLineImplFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class BusLineContractFacade {

    private final BusLineImplFacade busLineImplFacade;

    public List<BusLineResponse> findAll(){
        return busLineImplFacade.findAllBusLines().stream()
                .map(BusLineMapper::mapToResponse)
                .collect(Collectors.toList());
    }

}
