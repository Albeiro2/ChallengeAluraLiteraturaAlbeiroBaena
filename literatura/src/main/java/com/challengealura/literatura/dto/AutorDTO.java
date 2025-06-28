package com.challengealura.literatura.dto;

import java.util.List;
//Con esta clase, traemos los autores de la base de datos y
//transferimos solo los datos necesarios para mostrar en consola o al cliente
public record AutorDTO(
        String nombre,
        Integer fechaNacimiento,
        Integer fechaFallecimiento,
        List<String> libros
) {
}
