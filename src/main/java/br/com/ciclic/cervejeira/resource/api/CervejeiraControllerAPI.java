package br.com.ciclic.cervejeira.resource.api;


import br.com.ciclic.cervejeira.business.exception.BeerStyleNotFoundException;
import br.com.ciclic.cervejeira.business.exception.PlayListNotFoundException;
import br.com.ciclic.cervejeira.resource.dto.request.CervejaRequest;
import br.com.ciclic.cervejeira.resource.dto.response.EstiloCervejaComPlayListResponse;
import br.com.ciclic.cervejeira.resource.dto.response.EstiloCervejaResponse;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("estilo-cerveja")
public interface CervejeiraControllerAPI {

    @ResponseBody
    @ApiOperation("Buscar todos os estilos de cervejas cadastrados")
    @GetMapping
    List<EstiloCervejaResponse> buscarTodosEstilos();

    @ResponseBody
    @ApiOperation("Buscar estilo de cerveja pelo identificador")
    @GetMapping("/{id}")
    EstiloCervejaResponse buscarEstiloPorId(@PathVariable("id") Long id) throws BeerStyleNotFoundException;


    @ApiOperation("Busca estilo de cerveja mais adequada á temperatura pesquisada.")
    @GetMapping("/temperatura/{temperatura}")
    @ResponseBody
    EstiloCervejaComPlayListResponse buscarCervejaPorTemperatura(@PathVariable("temperatura") Long temeratura) throws PlayListNotFoundException, SpotifyWebApiException;

    @PostMapping
    @ApiOperation("Inserir um estilo de cerveja")
    @ResponseBody ResponseEntity<Void> inserirCerveja(@RequestBody CervejaRequest request);

    @PutMapping("/{cerveja_id}")
    @ApiOperation("Alterar um estilo de cerveja podendo enviar somente os parametros a serem atualizados")
    @ResponseBody ResponseEntity<Void> atulizarCerveja(@PathVariable("cerveja_id") String cervejaId, @RequestBody CervejaRequest request) throws BeerStyleNotFoundException;

    @DeleteMapping("/{cerveja_id}")
    @ApiOperation("Deletar um estilo de cerveja atraves do id")
    @ResponseBody ResponseEntity<Void> deletarCerveja(@PathVariable("cerveja_id") Long cervejaId) throws BeerStyleNotFoundException;

}
