package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(@JsonAlias("name") String nombreAutor,
                         @JsonAlias("birth_year") Integer añoDeNacimiento,
                         @JsonAlias("death_year") Integer añoDeMuerte) {
}
