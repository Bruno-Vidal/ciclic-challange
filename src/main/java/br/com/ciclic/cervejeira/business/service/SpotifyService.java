package br.com.ciclic.cervejeira.business.service;

import br.com.ciclic.cervejeira.business.service.response.SpotifyServiceResponse;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {

    private SpotifyApi spotifyApi;

    public SpotifyService(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public SpotifyServiceResponse buscaPlaylist(String beerName){

        final SearchPlaylistsRequest playlistsRequest = spotifyApi
                .searchPlaylists(beerName)
                .limit(1)
                .market(CountryCode.BR)
                .offset(0)
                .build();

        final Paging<PlaylistSimplified> playListResponse = playlistsRequest.execute();
        return SpotifyServiceResponse.builder().build();
    }
}
