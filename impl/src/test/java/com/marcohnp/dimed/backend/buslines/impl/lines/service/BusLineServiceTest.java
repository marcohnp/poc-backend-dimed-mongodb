package com.marcohnp.dimed.backend.buslines.impl.lines.service;

import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineNotFoundException;
import com.marcohnp.dimed.backend.buslines.impl.lines.repository.BusLineRepository;
import com.marcohnp.dimed.backend.buslines.impl.lines.stub.BusLineStub;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusLineServiceTest {

    @Mock
    private BusLineRepository busLineRepository;

    @InjectMocks
    private BusLineService busLineService;

    private final Pageable pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.asc("name")));

    @Test
    void findAllBusLines(){
        when(busLineRepository.findAll(pageable))
                .thenReturn(BusLineStub.createListBusLine());
        Assertions.assertEquals(BusLineStub.createListBusLine().getContent(),
                busLineService.findAllBusLines(pageable).getContent());
        verify(busLineRepository, times(1)).findAll(pageable);
    }

    @Test
    void findAllBusLines_whenRepositoryReturnNullThrowsException(){
        when(busLineRepository.findAll(pageable)).thenReturn(null);
        var exception = assertThrows(BusLineNotFoundException.class,
                () -> busLineService.findAllBusLines(pageable));
        assertEquals(new BusLineNotFoundException().getMessage(), exception.getMessage());
    }

    @Test
    void findAllBusLines_whenRepositoryReturnEmptyThrowsException(){
        when(busLineRepository.findAll(pageable)).thenReturn(Page.empty());
        var exception = assertThrows(BusLineNotFoundException.class,
                () -> busLineService.findAllBusLines(pageable));
        assertEquals(new BusLineNotFoundException().getMessage(), exception.getMessage());
    }

}