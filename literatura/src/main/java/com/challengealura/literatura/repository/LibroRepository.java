package com.challengealura.literatura.repository;

import com.challengealura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
//Aqui manejamos los metodos para interactuar con los libros de la base de datos
//Ya sea ingresar nuevos o traerlos
public interface LibroRepository extends JpaRepository <Libro, Long> {
    Optional<Libro> findByTituloIgnoreCase(String titulo);
    List<Libro> findByIdiomaIgnoreCase(String idioma);
}
