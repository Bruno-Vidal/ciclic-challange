package br.com.ciclic.cervejeira.business.service.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstiloCervejaServiceReponse {
    private String estiloCerveja;
    private PlayListServiceResponse playList;
}
