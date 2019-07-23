package br.com.ciclic.cervejeira.business.service;

import br.com.ciclic.cervejeira.business.exception.BeerStyleNotFoundException;
import br.com.ciclic.cervejeira.business.model.EstiloCerveja;
import br.com.ciclic.cervejeira.business.repository.EstiloCervejaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstiloCervejaService {

    private EstiloCervejaRepository cervejaRepository;

    public EstiloCervejaService(EstiloCervejaRepository cervejaRepository) {
        this.cervejaRepository = cervejaRepository;
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
                .orElseThrow(() -> new BeerStyleNotFoundException("Cerveja com id:" + cerveja.getId() + " não foi encontrado"));
        oldbeer.setNome(Optional.ofNullable(cerveja.getNome()).orElse(oldbeer.getNome()));
        oldbeer.setTemperaturaMaxima(Optional.ofNullable(cerveja.getTemperaturaMaxima()).orElse(oldbeer.getTemperaturaMaxima()));
        oldbeer.setTemperaturaMinima(Optional.ofNullable(cerveja.getTemperaturaMinima()).orElse(oldbeer.getTemperaturaMinima()));
        cervejaRepository.save(oldbeer);
    }

    public void deletar(Long cervejaId) throws BeerStyleNotFoundException {
        cervejaRepository
                .delete(cervejaRepository
                            .findById(cervejaId)
                                    .orElseThrow(() -> new BeerStyleNotFoundException("Cerveja com id:" + cervejaId + " não foi encontrado")));
    }
}
