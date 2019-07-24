package br.com.ciclic.cervejeira;

import br.com.ciclic.cervejeira.business.repository.EstiloCervejaRepository;
import br.com.ciclic.cervejeira.resource.dto.request.CervejaRequest;
import br.com.ciclic.cervejeira.resource.dto.response.EstiloCervejaComPlayListResponse;
import br.com.ciclic.cervejeira.resource.dto.response.EstiloCervejaResponse;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CervejeiraControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @Rollback
    public void inserirCerveja(){

        CervejaRequest request = new CervejaRequest();
        request.setNome("KitoBeer");
        request.setTemperaturaMaxima(5l);
        request.setTemperaturaMinima(-5l);
        ResponseEntity<Void> response = restTemplate.postForEntity("/estilo-cerveja", request, Void.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void atualizarCerveja(){
        CervejaRequest request = new CervejaRequest();
        request.setNome("KitoBeer");
        request.setTemperaturaMaxima(5l);
        request.setTemperaturaMinima(-5l);
        HttpEntity<CervejaRequest> requestHttpEntity = new HttpEntity<>(request);
        ResponseEntity<Void> exchange = restTemplate.exchange("/estilo-cerveja/2", HttpMethod.PUT, requestHttpEntity, Void.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deletarCerveja(){
        ResponseEntity<Void> exchange = restTemplate.exchange("/estilo-cerveja/3", HttpMethod.DELETE,null, Void.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void buscarUmaCervejaPeloId(){
        ResponseEntity<EstiloCervejaResponse> response = restTemplate.getForEntity("/estilo-cerveja/1", EstiloCervejaResponse.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getId()).isEqualTo(1l);
        Assertions.assertThat(response.getBody().getNome()).isEqualTo("Weissbier");
        Assertions.assertThat(response.getBody().getTemperaturaMaxima()).isEqualTo(-1l);
        Assertions.assertThat(response.getBody().getTemperaturaMinima()).isEqualTo(-3l);
        Assertions.assertThat(response.getBody().getTemperaturaIdeal()).isEqualTo(-2l);
    }

    @Test
    public void buscarTodasCervejas(){
        ResponseEntity<List> response = restTemplate.getForEntity("/estilo-cerveja", List.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().size()).isBetween(9,10);
    }

    @Test
    public void verificarMelhorCervejaDeAcordoComTemperatura(){
        ResponseEntity<EstiloCervejaComPlayListResponse> response = restTemplate.getForEntity("/estilo-cerveja/temperatura/7", EstiloCervejaComPlayListResponse.class);
        Assertions.assertThat(response.getBody().getEstiloCerveja()).isEqualTo("Brown ale");
        Assertions.assertThat(response.getBody().getPlayList()).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
