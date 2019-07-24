package br.com.ciclic.cervejeira.config.handler;

import br.com.ciclic.cervejeira.business.exception.BaseNotFoundException;
import br.com.ciclic.cervejeira.business.exception.PlayListNotFoundException;
import br.com.ciclic.cervejeira.resource.dto.ErrorDTO;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler{

	@ExceptionHandler({ BaseNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorDTO> handleNotFound(BaseNotFoundException ex,
																		WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
									.body(ErrorDTO.builder()
													.message(ex.getMessage())
													.status(HttpStatus.NOT_FOUND.value())
													.timestamp(new Date())
											      .build());
	}

	@ExceptionHandler({ SpotifyWebApiException.class})
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ResponseEntity<ErrorDTO> handleSpotifyWebApiException(SpotifyWebApiException ex,
																		WebRequest request) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
									.body(ErrorDTO.builder()
													.message(ex.getMessage())
													.status(HttpStatus.SERVICE_UNAVAILABLE.value())
													.timestamp(new Date())
											      .build());
	}
}
