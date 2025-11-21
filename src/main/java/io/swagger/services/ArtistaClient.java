package io.swagger.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.threeten.bp.OffsetDateTime;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.swagger.model.Contenido;
import io.swagger.model.Genero;
import io.swagger.model.Artista;

@Service
public class ArtistaClient {

    private String usuariosBaseUrl = "http://localhost:3000/api/usuarios";

    private final RestTemplate restTemplate = new RestTemplate();

    public Artista obtenerArtistaPorId(Integer idArtista) {
       String url = usuariosBaseUrl + "/artistas/" + idArtista;

        ResponseEntity<Artista> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Artista.class
        );

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("No se ha podido obtener el artista con id " + idArtista);
        }

        Artista artista = response.getBody();
        // Si quieres asegurarte de que el id es el que tú usas en la URL:
        return artista;
    }
}

