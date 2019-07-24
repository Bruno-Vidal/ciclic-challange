package br.com.ciclic.cervejeira;


import br.com.ciclic.cervejeira.business.model.EstiloCerveja;
import br.com.ciclic.cervejeira.business.repository.EstiloCervejaRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EstilocervejaRespositoryTest {

    @Autowired
    private EstiloCervejaRepository repository;

    @Test
    public void createBeerStyle(){
        EstiloCerveja kitoBeer = repository.save(EstiloCerveja
                                                    .builder()
                                                    .temperaturaMaxima(5l)
                                                    .temperaturaMinima(-5l)
                                                    .nome("KitoBeer")
                                                    .build());

        Assertions.assertThat(kitoBeer.getId()).isNotNull();
        Assertions.assertThat(kitoBeer.getNome()).isEqualTo("KitoBeer");
        Assertions.assertThat(kitoBeer.getTemperaturaMaxima()).isEqualTo(5l);
        Assertions.assertThat(kitoBeer.getTemperaturaMinima()).isEqualTo(-5l);
        Assertions.assertThat(kitoBeer.temperaturaPerfeita()).isEqualTo(0l);


    }

    @Test
    public void updateBeerStyle() {
        EstiloCerveja updateBeer = repository.findById(1l).get();
        updateBeer.setNome("Update Beer");
        updateBeer = repository.save(updateBeer);

        Assertions.assertThat(updateBeer.getId()).isEqualTo(1l);
        Assertions.assertThat(updateBeer.getNome()).isEqualTo("Update Beer");


    }
    @Test
    public void deleteBeerStyle(){
        EstiloCerveja deleteBeer = repository.findById(1l).get();
        Assertions.assertThat(deleteBeer.getId()).isNotNull();
        repository.delete(deleteBeer);
        EstiloCerveja posDelete = repository.findById(deleteBeer.getId()).orElse(null);
        Assertions.assertThat(posDelete).isNull();
    }
    @Test
    public void readOneBeerStyle(){
        EstiloCerveja findBeer = repository.findById(1l).orElse(null);
        Assertions.assertThat(findBeer.getId()).isNotNull();
        Assertions.assertThat(findBeer.getNome()).isEqualTo("Weissbier");
        Assertions.assertThat(findBeer.getTemperaturaMaxima()).isEqualTo(-1l);
        Assertions.assertThat(findBeer.getTemperaturaMinima()).isEqualTo(-3l);
        Assertions.assertThat(findBeer.temperaturaPerfeita()).isEqualTo(-2l);

    }
    @Test
    public void readAllBeersStyles(){
        List<EstiloCerveja> happyHour = repository.findAll();
        Assertions.assertThat(happyHour.size()).isEqualTo(9);
    }

}
