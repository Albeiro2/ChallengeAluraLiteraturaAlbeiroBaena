package com.challengealura.literatura.dto;

//Con esta clase, traemos los libros de la base de datos y
//transferimos solo los datos necesarios para mostrar en consola o al cliente
public record LibroDTO(
        String titulo,
        String autor,
        String idioma,
        Double numeroDescargas
) {
}
