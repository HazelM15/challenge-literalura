package com.aluracursos.literalura.model;

public enum Idioma {
    INGLES("en", "Ingles"),
    ESPAÑOL("es", "Español"),
    FRANCES("fr", "Frances");

    private String idiomaOmdb;
    private String idiomaUsuario;

    Idioma(String idiomaOmdb, String idiomaUsuario) {
        this.idiomaOmdb = idiomaOmdb;
        this.idiomaUsuario = idiomaUsuario;
    }
    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaOmdb.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw  new IllegalArgumentException("Idioma no encontrado" + text);
    }

    public  static Idioma fromUsuario(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaUsuario.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw  new IllegalArgumentException("Idioma no encontrado" + text);
    }
}
