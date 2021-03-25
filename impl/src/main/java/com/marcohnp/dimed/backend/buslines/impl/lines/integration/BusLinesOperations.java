package com.marcohnp.dimed.backend.buslines.impl.lines.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import com.marcohnp.dimed.backend.buslines.impl.lines.model.Coordinates;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusLinesOperations {


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



//    public List<LinhaOnibus> createListLinhaOnibus() {
//        return getResponseLinhaOnibus().stream()
//                .map(dto ->
//                        LinhaOnibus
//                                .builder()
//                                .id(Long.parseLong(dto.getId()))
//                                .nome(dto.getNome())
//                                .codigo(dto.getCodigo())
//                                .build())
//                .collect(Collectors.toList());
//    }
//
//    public List<LinhaOnibus> getListLinhaOnibusByName(String name) {
//        return getResponseLinhaOnibus().stream()
//                .map(dto -> LinhaOnibus
//                        .builder()
//                        .id(Long.parseLong(dto.getId()))
//                        .nome(dto.getNome())
//                        .codigo(dto.getCodigo())
//                        .build())
//                .filter(linhaOnibus -> linhaOnibus.getNome().contains(name.toUpperCase()))
//                .collect(Collectors.toList());
//    }
//
//
//    private String getReponseItinerario(String uri) {
//        RestTemplate rest = getRestTemplate();
//        ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, null, String.class);
//        return response.getBody();
//    }
//
//    private RestTemplate getRestTemplate() {
//        RestTemplate rest = new RestTemplate();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
//        rest.getMessageConverters().add(converter);
//        return rest;
//    }
//
//    private JsonNode getJsonNode(String uri) {
//        try {
//            String string = getReponseItinerario(uri);
//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.readTree(string);
//        } catch (Exception e) {
//            throw new JsonParseException(e);
//        }
//
//    }
//
//    public Itinerario populateItinerario(String uri) {
//        try {
//            JsonNode actualObj = getJsonNode(uri);
//            JsonNode jnIdLinha = actualObj.get("idlinha");
//            JsonNode jnNome = actualObj.get("nome");
//            JsonNode jnCodigo = actualObj.get("codigo");
//
//            Long idLinha = jnIdLinha.asLong();
//            String nome = jnNome.asText();
//            String codigo = jnCodigo.asText();
//
//            return new Itinerario(idLinha, nome, codigo, null);
//        } catch (Exception e) {
//            throw new JsonParseException(e);
//        }
//    }
//
//    public Itinerario getItinerario(String uri) {
//        JsonNode actualObj = getJsonNode(uri);
//        Itinerario itinerario = populateItinerario(uri);
//
//        List<Coordendas> listCoord = new ArrayList<>();
//
//        for (int i = 0; i < (actualObj.size() - 3); i++) {
//            Coordendas coord = new Coordendas();
//            JsonNode lat = actualObj.get("" + i + "").path("lat");
//            JsonNode lng = actualObj.get("" + i + "").path("lng");
//            double latitude = lat.asDouble();
//            double longitude = lng.asDouble();
//            coord.setLatitude(latitude);
//            coord.setLongitude(longitude);
//            Itinerario it = new Itinerario();
//            it.setIdlinha(itinerario.getIdlinha());
//            coord.setItinerario(it);
//            listCoord.add(coord);
//        }
//        itinerario.setCoordendas(listCoord);
//        return itinerario;
//    }
//
//    public List<LinhaOnibus> linhasPorRaio(double lat, double lng, double raio) {
//        List<LinhaOnibus> linhas = createListLinhaOnibus();
//
//        return linhas.stream().filter(linha -> {
//            Itinerario itinerario = getItinerario(catchUriItinerario(linha.getId().toString()));
//            setThread();
//            return itinerario.getCoordendas().stream().anyMatch(coordenadas -> {
//                double result = distance(lat, coordenadas.getLatitude(),
//                        lng, coordenadas.getLongitude(), 0.0, 0.0);
//                return result <= raio;
//            });
//        }).collect(Collectors.toList());
//    }
//
//    private void setThread() {
//        try {
//            Thread.sleep(109);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static double distance(double lat1, double lat2, double lon1,
//                                  double lon2, double el1, double el2) {
//
//        final int R = 6371;
//
//        double latDistance = Math.toRadians(lat2 - lat1);
//        double lonDistance = Math.toRadians(lon2 - lon1);
//        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
//                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
//                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        double distance = R * c * 1000;
//
//        double height = el1 - el2;
//
//        distance = Math.pow(distance, 2) + Math.pow(height, 2);
//
//        return Math.sqrt(distance);
//    }


}

