package br.com.ciclic.cervejeira.resource.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstiloCervejaComPlayListResponse {
    @JsonProperty("beerStyle")
    private String estiloCerveja;

    @JsonProperty("playlist")
    private PlayListResponse playList;
}
