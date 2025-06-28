package com.challengealura.literatura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//Definimos la clase com una tabla
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    //Aqui mapeamos el autor de libros para establecer la relacion
    //de un autor puede tener muchos libros
    @OneToMany(mappedBy = "autor",  cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

     public Autor(DatosAutor datosAutor){
         this.nombre = datosAutor.nombre();
         this.fechaNacimiento = datosAutor.fechaNacimiento();
         this.fechaFallecimiento = datosAutor.fechaFallecimiento();
     }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l -> l.setAutor(this));
        this.libros = libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }
}
