package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Idioma;
import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloIgnoreCase(String titulo);

    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autor a WHERE a.añoDeNacimiento <= :año AND (a.añoDeMuerte IS NULL OR a.añoDeMuerte >= :año)")
    List<Autor> autoresVivosEnAño(@Param("año") Integer año);

    @Query("SELECT a FROM Libro l JOIN l.autor a WHERE a.añoDeNacimiento > :fecha")
    List<Autor> añoDeNacimientoAutores(@Param("fecha") Integer fecha);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> encuentraPorIdioma(@Param("idioma") Idioma idioma);
    }