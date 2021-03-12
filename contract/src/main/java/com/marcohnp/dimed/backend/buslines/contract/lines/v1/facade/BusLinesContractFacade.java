package com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.mapper.BusLineMapper;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;
import com.marcohnp.dimed.backend.buslines.impl.lines.facade.BusLineFacadeImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class BusLinesContractFacade {

    private final BusLineFacadeImpl busLineFacadeImpl;
    private final BusLineMapper busLineMapper;

    public List<BusLineResponse> findAll(Pageable pageable){
        return busLineFacadeImpl.findAllBusLines(pageable).stream()
                .map(BusLineMapper::mapToResponse)
                .collect(Collectors.toList());
    }

}
