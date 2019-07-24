package br.com.ciclic.cervejeira.resource.dto.response;

import br.com.ciclic.cervejeira.resource.dto.EstiloCervejaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstiloCervejaResponse extends EstiloCervejaDTO {

    private Long id;

    @JsonProperty("average_temperature")
    private Long temperaturaIdeal;

}
