package br.com.ciclic.cervejeira.resource.dto.request;

import br.com.ciclic.cervejeira.resource.dto.EstiloCervejaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CervejaRequest extends EstiloCervejaDTO {
}
