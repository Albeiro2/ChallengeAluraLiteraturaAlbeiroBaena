package com.challengealura.literatura.model;

import jakarta.persistence.*;
//Definimos la clase com una tabla
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double numeroDescargas;

    //Definimos autor como la clave foranea de la tabla
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma().isEmpty() ? "No especificado" : datosLibro.idioma().get(0);
        this.numeroDescargas = datosLibro.numeroDescargas();

    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;

    }

    public String getNombreAutor() {
        return autor != null ? autor.getNombre() : "Desconocido";
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
