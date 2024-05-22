# Liter-Alura

Este proyecto es una aplicación de biblioteca desarrollada en Java que utiliza la API [gutendex.com](https://gutendex.com/) para obtener información sobre libros y autores, y una base de datos PostgreSQL gestionada con pgAdmin 4. La configuración y estructura del proyecto se han realizado utilizando el framework Spring.

## Tabla de Contenidos

- [Características](#características)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Detalles de la Clase Principal](#detalles-de-la-clase-principal)
- [Configuración](#configuración)
- [Requisitos Previos](#requisitos-previos)

## Características

- Consumo de la API de gutendex.com para obtener datos de libros y autores.
- Almacenamiento de datos en una base de datos PostgreSQL.
- Gestión y configuración utilizando Spring.
- Separación lógica del código en diferentes paquetes para mejorar la mantenibilidad y legibilidad.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

1. **dto:** Contiene los Data Transfer Objects (DTOs) que son:

   - *AutorDTO:* Record que representa los datos de un autor.
   - *LibroDTO:* Record que representa los datos de un libro.

2. **model:** Contiene las clases y records que representan el modelo de datos de la aplicación:

   - *Libro:* Clase que representa un libro.
   - *Autor:* Clase que representa un autor.
   - *DatosLibro:* Record con información detallada de un libro.
   - *DatosAutor:* Record con información detallada de un autor.
   - *Datos:* Record que agrupa los datos.
   - *Idioma:* Enum que representa los idiomas disponibles.
  
3. **principal:** Contiene la clase principal para la ejecución de la aplicación:
   
   - *Principal:* Clase principal que contiene el método main.
  
4. **repository:** Contiene la interfaz para la gestión de datos en la base de datos:

   - *LibroRepository:* Interfaz que extiende de JpaRepository para la gestión de libros.

5. **service:** Contiene las clases y interfaces que implementan la lógica de negocio:

   - *ConsumoAPI:* Clase que maneja la lógica para consumir la API de gutendex.com.
   - *ConvierteDatos:* Clase que convierte los datos obtenidos en objetos del modelo.
   - *IConvierteDatos:* Interfaz que define los métodos de conversión de datos.

6. **LiteraluraApplication:** Clase que corre el programa y configura el contexto de Spring Boot.

## Detalles de la Clase Principal

La clase *Principal* maneja la interaccion con el usuario a través de un menú y realiza las operaciones principales de la aplicación.
### Métodos Principales
- **muestraMenu():** Muestra el menú principal y maneja la interacción del usuario.
- **datosUsuario():** Obtiene el nombre del libro que el usuario desea buscar.
- **datosAPI():** Consume la API de gutendex.com para obtener datos del libro.
- **infoLibro():** Filtra y mapea los datos del libro obtenido de la API.
- **buscarLibroWeb():** Integra los métodos anteriores para buscar un libro, mostrar su información y guardarlo en la base de datos.
- **mostrarLibrosRegistrados():** Muestra todos los libros guardados en la base de datos.
- **mostrarAutoresRegistrados():** Muestra todos los autores guardados en la base de datos.
- **mostrarAñoAutores():** Muestra los autores que estuvieron vivos en un año específico ingresado por el usuario.
- **mostrarLibrosPorIdioma():** Muestra los libros guardados en la base de datos por idioma seleccionado.
### Ejemplo de Uso
1. **Buscar libro por título:**
   - Seleccionar la opción 1 en el menú.
   - Ingresar el nombre del libro cuando se solicite.
   - El sistema buscará el libro en la API y lo guardará en la base de datos si no existe.
  
2. **Listar libros registrados:**
   - Seleccionar la opción 2 en el menú.
   - El sistema mostrará todos los libros almacenados en la base de datos.

3. **Listar autores registrados:**
   - Seleccionar la opción 3 en el menú.
   - El sistema mostrará todos los autores almacenados en la base de datos.
  
4. **Listar autores vivos en un determinado año:**
   - Seleccionar la opción 4 en el menú.
   - Ingresar el año deseado.
   - El sistema mostrará los autores que estaban vivos en ese año.

5. **Listar libros por idioma:**
   - Seleccionar la opción 5 en el menú.
   - Ingresar el idioma deseado (sin acentos).
   - El sistema mostrará los libros registrados en ese idioma.
  
## Configuración
1. **Configuración de la Base de Datos:**
   - Tener PostgreSQL instalado y funcionando.

2. **Archivo application.properties:**
   - Editar el archivo src/main/resources/application.properties con las credenciales.
   ```sh
   spring.datasource.url=jdbc:postgresql://localhost:5432/
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

## Requisitos Previos
- Java 11 o superior.
- Maven.
- PostgreSQL.
- pgAdmin 4.
- Conexión a internet para acceder a la API de gutendex.com.
