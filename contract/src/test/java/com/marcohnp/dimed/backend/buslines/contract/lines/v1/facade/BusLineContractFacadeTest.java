package com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub.BusLineResponseStub;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub.BusLineStub;
import com.marcohnp.dimed.backend.buslines.impl.lines.facade.BusLineImplFacade;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusLineContractFacadeTest {

    @Mock
    private BusLineImplFacade busLineImplFacade;

    @InjectMocks
    private BusLineContractFacade busLineContractFacade;

    private final Pageable pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.asc("name")));

    @Test
    void findAll(){
        when(busLineImplFacade.findAllBusLines(pageable))
                .thenReturn(BusLineStub.createListBusLine());
        Assertions.assertEquals(BusLineResponseStub.createListBusLineResponse().getContent(),
                busLineContractFacade.findAll(pageable));
        verify(busLineImplFacade, times(1)).findAllBusLines(pageable);
    }
}