package br.com.ciclic.cervejeira.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CervejaRequest {

    @JsonProperty("name")
    private String nome;

    @JsonProperty("max_temperature")
    private Long temperaturaMaxima;

    @JsonProperty("min_temperature")
    private Long temperaturaMinima;
}
