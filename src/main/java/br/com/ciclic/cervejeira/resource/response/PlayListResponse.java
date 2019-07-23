package br.com.ciclic.cervejeira.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PlayListResponse {

    @JsonProperty("name")
    private String nome;

    @JsonProperty("tracks")
    private Set<FaixaResponse> faixas;
}
