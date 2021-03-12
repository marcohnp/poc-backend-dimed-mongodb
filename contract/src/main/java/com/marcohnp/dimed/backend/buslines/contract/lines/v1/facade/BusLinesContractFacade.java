package com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;
import com.marcohnp.dimed.backend.buslines.impl.lines.facade.BusLineFacadeImpl;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.mapper.BusLineMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BusLinesContractFacade {

    private final BusLineFacadeImpl busLineFacadeImpl;
    private final BusLineMapper busLineMapper;

    public List<BusLineResponse> findAll(){
        return busLineFacadeImpl.findAllBusLines().stream()
                .map(busLineMapper::mapToResponse)
                .collect(Collectors.toList());
    }

}
