package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;

public record LibroDTO(Long id,
                       String titulo,
                       Autor autor,
                       Libro idiomas,
                       Double numeroDescargas) {
}
