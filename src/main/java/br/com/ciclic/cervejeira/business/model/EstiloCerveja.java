package br.com.ciclic.cervejeira.business.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beerStyle")
@Data
@Builder
public class EstiloCerveja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "temperatura_minima")
    private Long temperaturaMinima;

    @Column(name = "temperatura_maxima")
    private Long temperaturaMaxima;
}
