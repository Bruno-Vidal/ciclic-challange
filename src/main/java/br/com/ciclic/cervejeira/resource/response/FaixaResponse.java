package br.com.ciclic.cervejeira.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FaixaResponse {
    @JsonProperty("name")
    private String nome;

    @JsonProperty("artist")
    private String artista;

    @JsonProperty("artist")
    private String link;
}
