package br.com.ciclic.cervejeira.business.service;

import br.com.ciclic.cervejeira.business.exception.PlayListNotFoundException;
import br.com.ciclic.cervejeira.business.service.response.FaixaServiceResponse;
import br.com.ciclic.cervejeira.business.service.response.PlayListServiceResponse;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpotifyService {

    private SpotifyApi spotifyApi;

    public SpotifyService(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public PlayListServiceResponse buscaPlaylist(String beerName) throws PlayListNotFoundException, SpotifyWebApiException {

        PlaylistSimplified playList = buscarPlaylist(beerName);
        List<Track> tracks = buscarFaixasDaPlayList(playList);
        List<FaixaServiceResponse> faixas = mapearFaixas(tracks);
        return PlayListServiceResponse.builder()
                                            .nome(playList.getName())
                                            .faixas(faixas)
                                      .build();
    }

    private List<FaixaServiceResponse> mapearFaixas(List<Track> tracks) {
        return tracks.stream().map(track -> FaixaServiceResponse
                                                                        .builder()
                                                                            .artista( Optional
                                                                                            .ofNullable(Arrays.stream(track
                                                                                                                            .getArtists())
                                                                                                                                    .findAny().get()
                                                                                                                                                .getName()).orElse(""))
                                                                            .link(track.getHref())
                                                                            .nome(track.getName())
                                                                        .build()
                                ).collect(Collectors.toList());
    }


    private List<Track> buscarFaixasDaPlayList(PlaylistSimplified playListItem) throws  SpotifyWebApiException {

        try {
            return Arrays.stream(spotifyApi
                    .getPlaylistsTracks(playListItem.getOwner().getId(), playListItem.getId())
                        .limit(10)
                        .offset(0)
                        .build()
                    .execute()
                        .getItems())
                            .map(PlaylistTrack::getTrack)
                            .collect(Collectors.toList());
        }catch (IOException ioe) {
            throw new SpotifyWebApiException(ioe.getMessage(),ioe);
        }
    }

    private PlaylistSimplified buscarPlaylist(String beerName) throws  SpotifyWebApiException, PlayListNotFoundException {
        try {
            return Arrays.stream(spotifyApi
                                    .searchPlaylists(beerName)
                                        .limit(1)
                                        .market(CountryCode.BR)
                                        .offset(0)
                                        .build()
                                    .execute()
                                        .getItems()
                                )
                            .findAny()
                                    .orElseThrow(() -> new PlayListNotFoundException(beerName));
        }catch (IOException ioe){
            throw new SpotifyWebApiException(ioe.getMessage(),ioe);
        }

    }
}
