package br.com.ciclic.cervejeira.business.exception;

public class BeerStyleNotFoundException extends BaseNotFoundException {
    public BeerStyleNotFoundException(Long id) {
        super("Cerveja com id: " + id + " não foi encontrado");
    }
}
