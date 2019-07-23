package br.com.ciclic.cervejeira.resource.controller;

import br.com.ciclic.cervejeira.business.exception.BeerStyleNotFoundException;
import br.com.ciclic.cervejeira.business.service.EstiloCervejaService;
import br.com.ciclic.cervejeira.resource.api.CervejeiraControllerAPI;
import br.com.ciclic.cervejeira.resource.mapper.CervejaMapper;
import br.com.ciclic.cervejeira.resource.request.CervejaRequest;
import br.com.ciclic.cervejeira.resource.response.EstiloCervejaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CervejeiraController implements CervejeiraControllerAPI {

    private EstiloCervejaService service;
    private CervejaMapper mapper;

    public CervejeiraController(EstiloCervejaService service, CervejaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public EstiloCervejaResponse buscarCervejaPorTemperatura(String temeratura) {
        return null;
    }

    @Override
    public ResponseEntity<Void> inserirCerveja(CervejaRequest request) {
        service.cadastrar(mapper.toService(request));
        return ResponseEntity.status(202).build();
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
