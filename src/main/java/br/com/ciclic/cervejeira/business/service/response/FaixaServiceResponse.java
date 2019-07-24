package br.com.ciclic.cervejeira.business.service.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FaixaServiceResponse {

    private String nome;
    private String artista;
    private String link;
}
