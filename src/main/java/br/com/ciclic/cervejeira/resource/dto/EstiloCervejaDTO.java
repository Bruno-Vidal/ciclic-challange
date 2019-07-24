package br.com.ciclic.cervejeira.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class EstiloCervejaDTO {
    @JsonProperty("name")
    private String nome;

    @JsonProperty("max_temperature")
    private Long temperaturaMaxima;

    @JsonProperty("min_temperature")
    private Long temperaturaMinima;
}
