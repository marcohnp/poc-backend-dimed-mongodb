package com.marcohnp.dimed.backend.buslines.impl.lines.repository;

import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLineLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BusLineLocationRepository extends MongoRepository<BusLineLocation, String> {

    Optional<BusLineLocation> findByIdOnibus(String idOnibus);
}
