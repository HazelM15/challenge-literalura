package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table( name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombreAutor;
    private Integer añoDeNacimiento;
    private Integer añoDeMuerte;
    @ManyToOne
    private Libro libro;

    public Autor(){
    }

    public Autor(Autor autor) {

    }

    public Autor(String nombreAutor, Integer añoDeNacimiento, Integer añoDeMuerte, Libro libro){
        this.nombreAutor = nombreAutor;
        this.añoDeNacimiento = añoDeNacimiento;
        this.añoDeMuerte = añoDeMuerte;
        this.libro = libro;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Integer getAñoDeNacimiento() {
        return añoDeNacimiento;
    }

    public void setAñoDeNacimiento(Integer añoDeNacimiento) {
        this.añoDeNacimiento = añoDeNacimiento;
    }

    public Integer getAñoDeMuerte() {
        return añoDeMuerte;
    }

    public void setAñoDeMuerte(Integer añoDeMuerte) {
        this.añoDeMuerte = añoDeMuerte;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public  boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombreAutor, autor.nombreAutor) &&
                Objects.equals(añoDeNacimiento, autor.añoDeNacimiento) &&
                Objects.equals(añoDeMuerte, autor.añoDeMuerte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreAutor, añoDeNacimiento, añoDeMuerte);
    }

    @Override
    public String toString() {
        return nombreAutor + " * " +
                "Año de Nacimiento= " + añoDeNacimiento + " * " +
                "Año de Muerte= " + añoDeMuerte;
    }
}
