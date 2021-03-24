package com.marcohnp.dimed.backend.buslines.impl.lines.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "buslines")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusLine implements Serializable {


    private String id;
    @JsonProperty("codigo")
    private String code;
    @JsonProperty("nome")
    private String name;
    private List<Coordinates> coordinates;
}
