package com.marcohnp.dimed.backend.buslines.contract.lines.v1.controller;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade.BusLineContractFacade;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub.BusLineResponseStub;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
class BusLinesControllerTest {

    @Mock
    private BusLineContractFacade busLineContractFacade;

    @InjectMocks
    private BusLinesController busLinesController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(busLinesController).build();
    }

    private final Pageable pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.asc("name")));

    @Test
    void findall() throws Exception {
        when(this.busLineContractFacade.findAll(pageable))
                .thenReturn(Lists.newArrayList(BusLineResponseStub.createListBusLineResponse()));
        this.mockMvc.perform(get("/v1/linhas"))
                .andExpect(status().isOk());

    }
}