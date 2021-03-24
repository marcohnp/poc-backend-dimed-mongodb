package com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub.BusLineResponseStub;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub.BusLineStub;
import com.marcohnp.dimed.backend.buslines.impl.lines.facade.BusLineImplFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusLineContractFacadeTest {

    @Mock
    private BusLineImplFacade busLineImplFacade;

    @InjectMocks
    private BusLineContractFacade busLineContractFacade;


    @Test
    void findAll(){
        when(busLineImplFacade.findAllBusLines()).thenReturn(BusLineStub.createListBusLine());
        Assertions.assertEquals(BusLineResponseStub.createListBusLineResponse(),
                busLineContractFacade.findAll());
        verify(busLineImplFacade, times(1)).findAllBusLines();
    }
}