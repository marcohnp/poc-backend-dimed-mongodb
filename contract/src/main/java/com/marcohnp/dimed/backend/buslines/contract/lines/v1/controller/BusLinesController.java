package com.marcohnp.dimed.backend.buslines.contract.lines.v1.controller;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.facade.BusLineContractFacade;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineItinerary;
import com.marcohnp.dimed.backend.buslines.contract.lines.v1.model.response.BusLineResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @ApiOperation(value = "Retorna uma lista de linhas de onibus presentes na API PoaTransporte " +
            "e que foram salvas no MongoDB. Endpoint páginado.")
    @GetMapping(value="linhas")
    public List<BusLineResponse> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                         @RequestParam(required = false, defaultValue = "50") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("name")));
        return busLineContractFacade.findAll(pageable);
    }

    @ApiOperation(value = "Retorna uma lista de linhas de onibus e seus itinerarios presentes na API PoaTransporte " +
            "e que foram salvas no MongoDB. Endpoint páginado.")
    @GetMapping(value="itinerarios")
    public List<BusLineItinerary> findAllBusLineWithItinerary(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                              @RequestParam(required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("name")));
        return busLineContractFacade.findAllBusLineWithItinerary(pageable);
    }

    @ApiOperation(value = "Retorna as linha de onibus da API PoaTransporte a partir das coordendas passadas dentro " +
            "de um raio de 1km. Parâmetros: Latitude(lat), Longitude(lng).")
    @GetMapping(value = "/coord")
    public List<BusLineResponse> getLinhasOnibusByCoordinates(@RequestParam double lat, @RequestParam double lng) {
        return busLineContractFacade.findByCoordinates(lat, lng);
    }
}
