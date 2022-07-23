package com.marcohnp.dimed.backend.buslines.impl.lines.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.Coordinates;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusLinesOperations {

    private static final double RAIO = 1000;
    private static final double DISTANCIA = 50;

    public static String uriLinhaOnibus() {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.poatransporte.com.br")
                .path("php/facades/process.php")
                .queryParam("a", "nc")
                .queryParam("p", "%")
                .queryParam("t", "o")
                .build()
                .toString();
    }

    public static String uriItinerario(String id) {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.poatransporte.com.br")
                .path("php/facades/process.php")
                .queryParam("a", "il")
                .queryParam("p", "" + id + "")
                .build()
                .toString();
    }

    public List<BusLine> getAllItinerary(RestTemplate rest, ObjectMapper mapper) {
        return getAllBusLines(rest, mapper).stream()
                .peek(line -> populateBusLineWithCoordinates(rest, line))
                .collect(Collectors.toList());
    }

    public List<BusLine> getAllBusLines(RestTemplate rest, ObjectMapper mapper) {
        String busLinesString = rest.getForObject(uriLinhaOnibus(), String.class);
        try {
            return Arrays.asList(mapper.readValue(busLinesString, BusLine[].class));
        } catch (IOException e) {
            log.trace("{0}", e);
        }
        return Collections.emptyList();
    }

    public void populateBusLineWithCoordinates(RestTemplate rest, BusLine busLine){
        Map<Integer, Coordinates> busLineCoordinates = listBusLineCoordinates(busLine.getId(), rest);
        List<Coordinates> coordinates = new ArrayList<>();
        busLineCoordinates.forEach((i, c) -> coordinates.add(new Coordinates(c.getLat(), c.getLng())));
        busLine.setCoordinates(coordinates);

    }

    private Map<Integer, Coordinates> listBusLineCoordinates(String id, RestTemplate rest) {
        try {
            Thread.sleep(60);
            String coordinates = getStringCoordinates(Objects.requireNonNull(
                    rest.getForObject(uriItinerario(id), String.class)));
            return new ObjectMapper()
                    .readValue(coordinates, new TypeReference<Map<Integer, Coordinates>>() {
                    });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

    public String getStringCoordinates(String busLinesWithCoordinates) {
        String[] stringCoordinatesArray = busLinesWithCoordinates.split(",");
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        for (int i = 3; i <= stringCoordinatesArray.length - 1; i++) {
            builder.append(stringCoordinatesArray[i]);
            builder.append(",");
        }

        builder.deleteCharAt(builder.lastIndexOf(","));

        return builder.toString();
    }


    public List<BusLine> linhasPorRaio(List<BusLine> linhasComItininerario, double lat, double lng) {
        List<BusLine> linhas = linhasComItininerario;

        return linhas.stream().filter(linha -> {
            return linha.getCoordinates().stream().anyMatch(coordenadas -> {
                double result = distance(lat, coordenadas.getLat(),
                        lng, coordenadas.getLng(), 0.0, 0.0);
                return result <= RAIO;
            });
        }).collect(Collectors.toList());
    }

    public Integer encontrarPosicaoPorCoordenadas(BusLine busline,double raio, double lat, double lng) {
        double result;
        for (Coordinates coordenadas : busline.getCoordinates()) {
            result = distance(lat, coordenadas.getLat(),
                    lng, coordenadas.getLng(), 0.0, 0.0);
            if (result <= raio) {
                return busline.getCoordinates().indexOf(coordenadas);
            }
        }
        return null;
    }

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}

