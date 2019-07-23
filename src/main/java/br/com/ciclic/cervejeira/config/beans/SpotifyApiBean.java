package br.com.ciclic.cervejeira.config.beans;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

@Configuration
public class SpotifyApiBean {

    @Value("${spotify.registry.developer.clientid}")
    private String clientId;

    @Value("${spotify.registry.developer.clientsecret}")
    private String clientSecret;

    @Value("${spotify.registry.developer.redirecturi}")
    private String redirecturi;

    @Bean
    public SpotifyApi spotifyApi() throws IOException, SpotifyWebApiException {

        SpotifyApi spotifyApi =  new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(URI.create(redirecturi))
                .build();
        spotifyApi.setAccessToken(spotifyApi
                                    .clientCredentials()
                                    .build()
                                    .execute()
                                    .getAccessToken());
        return spotifyApi;

    }
}
