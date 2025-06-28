package com.challengealura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Clase para mapear los datos que tiene un autor
//Solo mapeamos los mas relevantes, para eso ignoramos las demas propiedades(JsonIgnoreProperties)
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(

        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNacimiento,
        @JsonAlias("death_year") Integer fechaFallecimiento
) {

}
