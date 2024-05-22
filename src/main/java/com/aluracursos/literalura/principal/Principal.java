package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.model.Idioma;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository repositorio;
    private List<Libro> libros;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraMenu() {
        var opcion = -1;
        var bienvenida = """
                    *************************************************
                    *                                               *
                    *          Bienvenido(a) a Liter-Alura          *
                    *                                               *
                    *************************************************
                    
                    Seleccione una opcion del menu
                """;
        System.out.println(bienvenida);
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                        
                    0- Salir del sistema
                    """;

            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroWeb();
                        break;
                    case 2:
                        mostrarLibrosRegistrados();
                        break;
                    case 3:
                        mostrarAutoresRegistrados();
                        break;
                    case 4:
                        mostrarAñoAutores();
                        break;
                    case 5:
                        mostrarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema");
                        break;
                    default:
                        System.out.println("Opcion invalida\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un un número válido\n");
                teclado.next();
            }
        }
    }

    //Obtiene informacion del usuario
    private String datosUsuario() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        return nombreLibro;
    }

    //Interaccion con la API
    private Datos datosAPI(String nombreLibro) {
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        return datos;
    }

    //Obtiene informacion del libro buscado
    private Optional<Libro> infoLibro(Datos datosLibro, String nombreLibro) {
        Optional<Libro> libro = datosLibro.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .map(b -> new Libro(b.titulo(), b.autor(), b.idiomas(), b.numeroDescargas()))
                .findFirst();
        return libro;
    }

    //Presenta la informacion del libro buscado y la guarda en la BD
    private Optional<Libro> buscarLibroWeb() {
        String tituloLibro = datosUsuario();
        Datos datos = datosAPI(tituloLibro);
        Optional<Libro> libro = infoLibro(datos, tituloLibro);
        if (libro.isPresent()) {
            Libro libroExistente = libro.get();
            if (repositorio.findByTituloIgnoreCase(libroExistente.getTitulo()).isPresent()) {
                System.out.println("El libro ya está en la base de datos \n");
            } else {
                repositorio.save(libroExistente);
                System.out.println(libroExistente);
            }
        } else {
            System.out.println("Libro no encontrado \n");
        }
        return libro;
    }

    //Muestra los libros guardados en la BD
    private void mostrarLibrosRegistrados() {
        libros = repositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    //Muestra los autores guardados en la BD
    private void mostrarAutoresRegistrados() {
        Set<Autor> autorSet = new HashSet<>();
        List<Libro> libroList = repositorio.findAll();
        for (Libro libro : libroList) {
            autorSet.addAll(libro.getAutor());
        }
        List<Autor> autorList = new ArrayList<>(autorSet);
        autorList.sort(Comparator.comparing(Autor::getNombreAutor));
        autorList.forEach(System.out::println);
    }

    //Muestra autores que estuvieron vivos en un determinado año
    public void mostrarAñoAutores() {
        System.out.println("Introduzca el año que desea, para conocer cuales autores vivian");
        int año = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autorList = repositorio.autoresVivosEnAño(año);
        Set<Autor> autorSet = new HashSet<>(autorList);
        if (autorList.isEmpty()) {
            System.out.println("No hay registro de autores vivos en el año " + año + "\n");
        } else {
            autorSet.stream()
                    .sorted(Comparator.comparing(Autor::getNombreAutor))
                    .forEach(a -> System.out.printf("Autor: %s * Nació en el año: %s * Murió en el año: %s \n",
                            a.getNombreAutor(), a.getAñoDeNacimiento(), a.getAñoDeMuerte()));
        }

    }

    private void mostrarLibrosPorIdioma() {
        var listaIdiomas = """
                De la siguiente lista, escriba el idioma para mostrar los libros registrados con el mismo.
                Omita acentos
                
                Ingles
                Español
                Frances
                """;
        System.out.println(listaIdiomas);
        boolean idiomaValido = false;
        while (!idiomaValido) {
            var opcionUsuario = teclado.nextLine().trim();
            try {
                var idiomaLibro = Idioma.fromUsuario(opcionUsuario);
                List<Libro> listaLibrosPorIdioma = repositorio.encuentraPorIdioma(idiomaLibro);
                listaLibrosPorIdioma.forEach(System.out::println);
                idiomaValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("El idioma ingresado no está en la lista. Por favor, elija uno de los idiomas proporcionados" +
                        " y/o omita acentos \n");
            }
        }
    }
}