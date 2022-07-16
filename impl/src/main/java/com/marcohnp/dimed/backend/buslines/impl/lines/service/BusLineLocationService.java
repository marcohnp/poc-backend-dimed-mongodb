package com.marcohnp.dimed.backend.buslines.impl.lines.service;

import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineLocationFarFromItineraryException;
import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineLocationNotFoundException;
import com.marcohnp.dimed.backend.buslines.impl.lines.exception.exceptions.BusLineNotFoundException;
import com.marcohnp.dimed.backend.buslines.impl.lines.integration.BusLinesOperations;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLineLocation;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.Coordinates;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.PositionBus;
import com.marcohnp.dimed.backend.buslines.impl.lines.repository.BusLineLocationRepository;
import com.marcohnp.dimed.backend.buslines.impl.lines.repository.BusLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusLineLocationService {

    private final BusLineRepository busLineRepository;
    private final BusLineLocationRepository busLineLocationRepository;
    private final BusLinesOperations busLinesOperations;

    private final static String T12 = "5116";

    public List<BusLineLocation> findAllBusLineLocation() {
        return busLineLocationRepository.findAll();
    }


    public BusLineLocation updateBusLineLocation(double lat, double lng, String idOnibus) {
        BusLine onibusT12 = busLineRepository.findById(T12).orElseThrow(BusLineNotFoundException::new);
        Integer posicicaoAtual = busLinesOperations.encontrarPosicaoPorCoordenadas(onibusT12, lat, lng);
        validaPosicaoAtual(posicicaoAtual);
        BusLineLocation busLineLocation = busLineLocationRepository.findByIdOnibus(idOnibus).orElseThrow(BusLineLocationNotFoundException::new);
        updateLocationAndHorario(onibusT12, posicicaoAtual, busLineLocation);
        return busLineLocationRepository.save(busLineLocation);
    }

    private void validaPosicaoAtual(Integer posicicaoAtual) {
        if (posicicaoAtual == null) {
            throw new BusLineLocationFarFromItineraryException();
        }
    }

    private void updateLocationAndHorario(BusLine onibusT12, Integer posicicaoAtual, BusLineLocation busLineLocation) {
        busLineLocation.setUltimaPosicao(buildPositionBus(onibusT12, posicicaoAtual));
        busLineLocation.setHorario(LocalDateTime.now());
    }

    private PositionBus buildPositionBus(BusLine onibusT12, Integer posicicaoAtual) {
        Coordinates coordenadas = onibusT12.getCoordinates().get(posicicaoAtual);
        return PositionBus.builder()
                .posicao(posicicaoAtual)
                .coordenadas(coordenadas)
                .build();
    }

    public BusLineLocation findBusLineLocationByIdOnibus(String idOnibus) {
        return busLineLocationRepository.findByIdOnibus(idOnibus).orElseThrow(BusLineLocationNotFoundException::new);
    }

    public Integer saveBusLineLocation(double lat, double lng, String idOnibus) {
        BusLine onibusT12 = busLineRepository.findById(T12).orElseThrow(BusLineNotFoundException::new);
        Integer posicicao = busLinesOperations.encontrarPosicaoPorCoordenadas(onibusT12, lat, lng);
        Coordinates coordenadas = onibusT12.getCoordinates().get(posicicao);
        PositionBus positionBus = PositionBus.builder()
                .posicao(posicicao)
                .coordenadas(coordenadas)
                .build();
        BusLineLocation busLineLocation = BusLineLocation.builder()
                .idOnibus(idOnibus)
                .ultimaPosicao(positionBus)
                .horario(LocalDateTime.now())
                .build();
        busLineLocationRepository.save(busLineLocation);
        return posicicao;
    }
}
