package br.com.ciclic.cervejeira.resource.mapper;

import br.com.ciclic.cervejeira.business.model.EstiloCerveja;
import br.com.ciclic.cervejeira.business.service.response.EstiloCervejaServiceReponse;
import br.com.ciclic.cervejeira.business.service.response.FaixaServiceResponse;
import br.com.ciclic.cervejeira.business.service.response.PlayListServiceResponse;
import br.com.ciclic.cervejeira.resource.dto.request.CervejaRequest;
import br.com.ciclic.cervejeira.resource.dto.response.EstiloCervejaComPlayListResponse;
import br.com.ciclic.cervejeira.resource.dto.response.EstiloCervejaResponse;
import br.com.ciclic.cervejeira.resource.dto.response.FaixaResponse;
import br.com.ciclic.cervejeira.resource.dto.response.PlayListResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CervejaMapper {

    public EstiloCerveja toService(CervejaRequest request) {
       return EstiloCerveja
                .builder()
                    .nome(request.getNome())
                    .temperaturaMaxima(request.getTemperaturaMaxima())
                    .temperaturaMinima(request.getTemperaturaMinima())
                .build();
    }

    public EstiloCerveja toService(CervejaRequest request, String cervejaId) {
        EstiloCerveja estiloCerveja = toService(request);
        estiloCerveja.setId(NumberUtils.parseNumber(cervejaId,Long.class));
        return estiloCerveja;
    }

    public EstiloCervejaComPlayListResponse toApi(EstiloCervejaServiceReponse cervejaService) {
        return EstiloCervejaComPlayListResponse
                        .builder()
                            .estiloCerveja(cervejaService.getEstiloCerveja())
                            .playList(toApi(cervejaService.getPlayList()))
                        .build();
    }

    public PlayListResponse toApi(PlayListServiceResponse playListServiceResponse){
        return PlayListResponse
                .builder()
                    .nome(playListServiceResponse.getNome())
                    .faixas(playListServiceResponse.getFaixas().stream()
                                                                    .map(this::toApi)
                                                                .collect(Collectors.toList()))
                .build();
    }


    public FaixaResponse toApi(FaixaServiceResponse faixa) {
        return FaixaResponse
                    .builder()
                        .artista(faixa.getArtista())
                        .link(faixa.getLink())
                        .nome(faixa.getNome())
                    .build();
    }

    public List<EstiloCervejaResponse> toApi(List<EstiloCerveja> cervejas) {
        return cervejas.stream().map(this::toApi).collect(Collectors.toList());
    }

    public EstiloCervejaResponse toApi(EstiloCerveja cerveja){
        EstiloCervejaResponse estiloCerveja = new EstiloCervejaResponse();
        estiloCerveja.setId(cerveja.getId());
        estiloCerveja.setNome(cerveja.getNome());
        estiloCerveja.setTemperaturaMaxima(cerveja.getTemperaturaMaxima());
        estiloCerveja.setTemperaturaMinima(cerveja.getTemperaturaMinima());
        estiloCerveja.setTemperaturaIdeal(cerveja.temperaturaPerfeita());
        return estiloCerveja;
    }
}
