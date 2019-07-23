package br.com.ciclic.cervejeira.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstiloCervejaResponse {
    @JsonProperty("beerStyle")
    private String estiloCerveja;

    @JsonProperty("playlist")
    private PlayListResponse playList;
}
