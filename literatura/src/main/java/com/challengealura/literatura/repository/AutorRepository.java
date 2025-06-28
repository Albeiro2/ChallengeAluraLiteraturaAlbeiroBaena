package com.challengealura.literatura.repository;

import com.challengealura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//Aqui manejamos los metodos para interactuar con los autores de la base de datos
//Ya sea ingresar nuevos o traerlos
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a JOIN FETCH a.libros")
    List<Autor> obtenerAutoresConLibros();

    @Query("SELECT a FROM Autor a JOIN FETCH a.libros WHERE (:anio BETWEEN a.fechaNacimiento AND a.fechaFallecimiento)")
    List<Autor> buscarAutoresVivosEnAnio(@Param("anio") Integer anio);
}