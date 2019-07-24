package br.com.ciclic.cervejeira.resource.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlayListResponse {

    @JsonProperty("name")
    private String nome;

    @JsonProperty("tracks")
    private List<FaixaResponse> faixas;
}
