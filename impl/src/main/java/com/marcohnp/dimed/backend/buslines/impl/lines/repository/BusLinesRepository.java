package com.marcohnp.dimed.backend.buslines.impl.lines.repository;

import com.marcohnp.dimed.backend.buslines.impl.lines.model.BusLine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusLinesRepository extends MongoRepository<BusLine, String> {

}
