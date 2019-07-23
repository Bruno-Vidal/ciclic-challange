package br.com.ciclic.cervejeira.resource.api;


import br.com.ciclic.cervejeira.business.exception.BeerStyleNotFoundException;
import br.com.ciclic.cervejeira.business.model.EstiloCerveja;
import br.com.ciclic.cervejeira.resource.request.CervejaRequest;
import br.com.ciclic.cervejeira.resource.response.EstiloCervejaResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("estilo-cerveja")
public interface CervejeiraControllerAPI {

    @ApiOperation("Busca estilo de cerveja mais adequada á temperatura pesquisada.")
    @GetMapping("/temperatura/{temperatura}")
    EstiloCervejaResponse buscarCervejaPorTemperatura(@PathVariable("temperatura") String temeratura);

    @PostMapping
    @ApiOperation("Inserir um estilo de cerveja")
    ResponseEntity<Void> inserirCerveja(@RequestBody CervejaRequest request);

    @PutMapping("/{cerveja_id}")
    ResponseEntity<Void> atulizarCerveja(@PathVariable("cerveja_id") String cervejaId, @RequestBody CervejaRequest request) throws BeerStyleNotFoundException;

    @DeleteMapping("/{cerveja_id}")
    ResponseEntity<Void> deletarCerveja(@PathVariable("cerveja_id") Long cervejaId) throws BeerStyleNotFoundException;

}
