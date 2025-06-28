package com.challengealura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Como el json nos retorna un result, que es donde estan los libros
//Mapeamos la lista de libros del result
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Resultado(
        @JsonAlias("results") List<DatosLibro> datosLibros
) {
}
