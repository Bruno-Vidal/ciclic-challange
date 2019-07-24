package br.com.ciclic.cervejeira.business.service.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlayListServiceResponse {

    private String nome;
    private List<FaixaServiceResponse> faixas;
}
