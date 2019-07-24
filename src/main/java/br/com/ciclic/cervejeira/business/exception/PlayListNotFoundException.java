package br.com.ciclic.cervejeira.business.exception;

public class PlayListNotFoundException extends BaseNotFoundException {
    public PlayListNotFoundException(String beerName) {
        super("Bebida selecionada \""+beerName+"\", porém não foi encontrado uma playlist para acompanhar seu drink");
    }
}
