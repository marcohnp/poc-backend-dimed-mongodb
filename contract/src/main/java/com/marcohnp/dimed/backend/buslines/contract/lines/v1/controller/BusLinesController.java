package com.marcohnp.dimed.backend.buslines.contract.lines.v1.controller;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade.BusLineContractFacade;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="poc dimed back-end with mongoDB")
@AllArgsConstructor
@RestController
@RequestMapping("/v1/")
public class BusLinesController {

    private final BusLineContractFacade busLineContractFacade;

    @ApiOperation(value = "Retorna uma lista de linhas de onibus presentes na API PoaTransporte e a " +
            "salva no H2 Database.")
    @GetMapping(value="linhas")
    public List<BusLineResponse> findAll() {
        return busLineContractFacade.findAll();
    }

}
