package br.com.ciclic.cervejeira.business.service;

import br.com.ciclic.cervejeira.business.exception.BeerStyleNotFoundException;
import br.com.ciclic.cervejeira.business.exception.PlayListNotFoundException;
import br.com.ciclic.cervejeira.business.model.EstiloCerveja;
import br.com.ciclic.cervejeira.business.repository.EstiloCervejaRepository;
import br.com.ciclic.cervejeira.business.service.response.EstiloCervejaServiceReponse;
import br.com.ciclic.cervejeira.business.service.response.PlayListServiceResponse;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstiloCervejaService {

    private EstiloCervejaRepository cervejaRepository;
    private SpotifyService spotifyService;

    public EstiloCervejaService(EstiloCervejaRepository cervejaRepository, SpotifyService spotifyService) {
        this.cervejaRepository = cervejaRepository;
        this.spotifyService = spotifyService;
    }

    public List<EstiloCerveja> buscarCervejas(){
        return cervejaRepository.findAll();
    }

    public void cadastrar(EstiloCerveja cerveja) {
        cervejaRepository.save(cerveja);
    }

    public void atulizar(EstiloCerveja cerveja) throws BeerStyleNotFoundException {
        EstiloCerveja oldbeer = cervejaRepository.findById(cerveja
                                                                .getId())
                                                                .orElseThrow(() -> new BeerStyleNotFoundException(cerveja.getId()));
        oldbeer.setNome(Optional.ofNullable(cerveja.getNome()).orElse(oldbeer.getNome()));
        oldbeer.setTemperaturaMaxima(Optional.ofNullable(cerveja.getTemperaturaMaxima()).orElse(oldbeer.getTemperaturaMaxima()));
        oldbeer.setTemperaturaMinima(Optional.ofNullable(cerveja.getTemperaturaMinima()).orElse(oldbeer.getTemperaturaMinima()));
        cervejaRepository.save(oldbeer);
    }

    public void deletar(Long cervejaId) throws BeerStyleNotFoundException {
        cervejaRepository
                .delete(cervejaRepository
                            .findById(cervejaId)
                                    .orElseThrow(() -> new BeerStyleNotFoundException(cervejaId)));
    }

    public EstiloCervejaServiceReponse buscarCervejaPorTemperatura(Long temeratura) throws PlayListNotFoundException, SpotifyWebApiException {
        EstiloCerveja estiloCerveja = buscarCervejas().stream()
                .sorted((cerveja, proximaGarrafa) -> selecionarCervejaAdequada(cerveja, proximaGarrafa, temeratura)).findFirst().get();


        final PlayListServiceResponse playlist = spotifyService.buscaPlaylist(estiloCerveja.getNome());

        return EstiloCervejaServiceReponse.builder()
                                                .estiloCerveja(estiloCerveja.getNome())
                                                .playList(playlist)
                                          .build();

    }

    private Integer selecionarCervejaAdequada(EstiloCerveja cerveja, EstiloCerveja proximaGarrafa, Long temeratura) {
        Long diffCerveja = calcularDiferensa(cerveja, temeratura);
        Long diffProximaGarrafa = calcularDiferensa(proximaGarrafa, temeratura);
        Integer compare = Long.compare(diffCerveja, diffProximaGarrafa);

        return compare == 0 ?  cerveja.getNome().toLowerCase().compareTo(proximaGarrafa.getNome().toLowerCase()) : compare;
    }

    private long calcularDiferensa(EstiloCerveja cerveja, Long temeratura) {

        return Math.abs(Math.max(temeratura,cerveja.temperaturaPerfeita()) - Math.min(cerveja.temperaturaPerfeita(),temeratura));
    }

    public EstiloCerveja buscarCervejaPorId(Long id) throws BeerStyleNotFoundException {
        return cervejaRepository.findById(id).orElseThrow(() -> new BeerStyleNotFoundException(id));
    }
}
