package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Double numeroDescargas;

    public Libro(){
    }

    public Libro (List<DatosLibro> resultados) {
    }

    public Libro(String titulo, List<DatosAutor> autor, List<String> idioma, Double numeroDescargas) {
        this.titulo = titulo;
        this.autor = new ArrayList<>();
        for (DatosAutor datosAutor : autor) {
            Autor autores = new Autor(datosAutor.nombreAutor(), datosAutor.añoDeNacimiento(), datosAutor.añoDeMuerte(), this);
            this.autor.add(autores);
        }
        this.idioma = Idioma.fromString(idioma.get(0));
        this.numeroDescargas = numeroDescargas;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        autor.forEach(a -> a.setLibro(this));
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "Titulo= *** " + titulo + " *** \n" +
                "Autor= " + autor + " \n" +
                "Idiomas= " + idioma + " \n" +
                "Descargas= " + numeroDescargas + " \n";
    }

}
