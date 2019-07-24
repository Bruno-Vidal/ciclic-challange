package br.com.ciclic.cervejeira.resource.dto.response;

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

    @JsonProperty("link")
    private String link;
}
