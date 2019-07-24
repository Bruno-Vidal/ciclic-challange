package br.com.ciclic.cervejeira.resource.controller;

import br.com.ciclic.cervejeira.business.exception.BeerStyleNotFoundException;
import br.com.ciclic.cervejeira.business.exception.PlayListNotFoundException;
import br.com.ciclic.cervejeira.business.service.EstiloCervejaService;
import br.com.ciclic.cervejeira.resource.api.CervejeiraControllerAPI;
import br.com.ciclic.cervejeira.resource.mapper.CervejaMapper;
import br.com.ciclic.cervejeira.resource.dto.request.CervejaRequest;
import br.com.ciclic.cervejeira.resource.dto.response.EstiloCervejaComPlayListResponse;
import br.com.ciclic.cervejeira.resource.dto.response.EstiloCervejaResponse;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CervejeiraController implements CervejeiraControllerAPI {

    private EstiloCervejaService service;
    private CervejaMapper mapper;

    public CervejeiraController(EstiloCervejaService service, CervejaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<EstiloCervejaResponse> buscarTodosEstilos() {
        return mapper.toApi(service.buscarCervejas());
    }

    @Override
    public EstiloCervejaResponse buscarEstiloPorId(Long id) throws BeerStyleNotFoundException {
        return mapper.toApi(service.buscarCervejaPorId(id));
    }

    @Override
    public EstiloCervejaComPlayListResponse buscarCervejaPorTemperatura(Long temeratura) throws PlayListNotFoundException, SpotifyWebApiException {
        return mapper.toApi(service.buscarCervejaPorTemperatura(temeratura));
    }

    @Override
    public ResponseEntity<Void> inserirCerveja(CervejaRequest request) {
        service.cadastrar(mapper.toService(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> atulizarCerveja(String cervejaId, CervejaRequest request) throws BeerStyleNotFoundException {
        service.atulizar(mapper.toService(request,cervejaId));
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<Void> deletarCerveja(Long cervejaId) throws BeerStyleNotFoundException {
        service.deletar(cervejaId);
        return ResponseEntity.noContent().build();
    }
}
