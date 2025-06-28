package com.challengealura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//Clase para mapear los datos que tiene un libro
//Solo mapeamos los mas relevantes, para eso ignoramos las demas propiedades(JsonIgnoreProperties)
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Double numeroDescargas
) {
}
