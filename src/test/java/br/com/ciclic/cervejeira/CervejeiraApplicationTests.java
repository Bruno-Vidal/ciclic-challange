package br.com.ciclic.cervejeira;

import br.com.ciclic.cervejeira.business.exception.PlayListNotFoundException;
import br.com.ciclic.cervejeira.business.service.EstiloCervejaService;
import br.com.ciclic.cervejeira.business.service.SpotifyService;
import br.com.ciclic.cervejeira.business.service.response.SpotifyServiceResponse;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CervejeiraApplicationTests {

    @Autowired
    private SpotifyService spotifyService;

    @Autowired
    private EstiloCervejaService estiloCervejaService;

    @Test
    public void contextLoads() {
        try {
            spotifyService.buscaPlaylist("casa");
        } catch (PlayListNotFoundException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBuscarCervejaPorTemperatura() throws PlayListNotFoundException, SpotifyWebApiException {
        estiloCervejaService.buscarCervejaPorTemperatura(0l);
    }

}
