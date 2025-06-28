package com.challengealura.literatura.principal;

import com.challengealura.literatura.dto.AutorDTO;
import com.challengealura.literatura.dto.LibroDTO;
import com.challengealura.literatura.model.Autor;
import com.challengealura.literatura.model.DatosLibro;
import com.challengealura.literatura.model.Libro;
import com.challengealura.literatura.model.Resultado;
import com.challengealura.literatura.repository.AutorRepository;
import com.challengealura.literatura.repository.LibroRepository;
import com.challengealura.literatura.service.ConsumoApi;
import com.challengealura.literatura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    //Globalizamos las variables que seran utilizadas en los metodos
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    //Con la inyeccion de dependencia obtenemos libroRepository y autorRepository como paremos de la clase main
    public Principal(LibroRepository serieRepository, AutorRepository autorRepository){
        this.libroRepository = serieRepository;
        this.autorRepository = autorRepository;
    }

    //El bucle nos garantiza que se mantenga el programa funcionando hasta que el usuario lo decida cerrar
    public void iniciarAplicacion(){
        Integer opcion = -1;
        do{

            try{
                lanzarMenu();
                opcion = teclado.nextInt();
                teclado.nextLine();
                menuOpciones(opcion);
            }catch (Exception e){
                System.out.println("!Opcion de menu no valida");
                teclado.nextLine();
                opcion = -1;

            }

        }while(opcion!=0);
    }

    //En base a la opcion, aqui manejamos todas las acciones del usuario
    //haciendo el llamado al metodo respecto a la opcion digitada en cosola
    public void menuOpciones(Integer opcion){
        switch (opcion){

            case 1: buscarLibrosPorTitulo();
                break;
            case 2: listarLibros();
                break;
            case 3: listarAutores();
                break;
            case 4: autoresPorAnio();
                break;
            case 5: librosPorIdioma();
                break;
            case 0:
                System.out.println("\nCerrando programa..."); break;
            default:
                System.out.println("!Opcion de menu no valida"); break;

        }
    }

    //Metodo para el menu de la aplicacion
private void lanzarMenu(){
    System.out.println("---------------------------------------");
    System.out.println("Elija la opcion a traves de su numero:");
    System.out.println("1 - Buscar libro por titulo");
    System.out.println("2 - Listar libros registrados");
    System.out.println("3 - Listar autores registrados");
    System.out.println("4 - Listar autores vivos en un determinado año ");
    System.out.println("5 - Buscar libros por idioma");
    System.out.println("0 - Salir");
    System.out.println("Digite la opcion: ");
}
//Nos encargamos de pedir el titulo del libro, consumimos el api y convertimos los datos
    //Luego de obtener el libro lo guardamos
private void buscarLibrosPorTitulo(){
    String titulo;
    System.out.println("\n---------------------------------------");
    System.out.println("Digite el titulo del libro que desea buscar: ");
    titulo = teclado.nextLine();
    var json = consumoApi.obtenerDatos(URL_BASE+titulo.replace(" ", "%20"));
    Resultado libros = convierteDatos.obtenerDatos(json, Resultado.class);
    Optional<DatosLibro> libroEncontrado = libros.datosLibros().stream()
            .findFirst();
    if(libroEncontrado.isPresent()){
        DatosLibro libro = libroEncontrado.get();
        guardarLibro(libro);
    }else{
        System.out.println("Libro no encontrado");
    }

}
//Para guardar el libro, verificamos que el autor exista, si no existe
//guardamos el autor y se lo asignamos al libro y asi evitamos autores repetidos
//En casa de que exista, traemos el autor de la base de datos y se lo asignamos al libro
private void guardarLibro(DatosLibro datosLibro) {

        Libro libro = new Libro(datosLibro);
        // Buscar si ya existe el autor
        String nombreAutor = datosLibro.autor().get(0).nombre();
        Autor autor = autorRepository.findByNombreIgnoreCase(nombreAutor)
                .orElseGet(() -> autorRepository.save(new Autor(datosLibro.autor().get(0))));
       // Autor autor = new Autor(datosLibro.autor().get(0));
        libro.setAutor(autor);
        try{
            libroRepository.save(libro);
            detallesLibro(libro);

        }catch (Exception e){
            System.out.println("\nLibro ya buscado anteriormente, puede listarlo en la opcion 2");
        }

    }
    //Cuando obtenemos uno o mas libros, los pasamos por este metodo para que se vean mejor presentados
    private void detallesLibro(Libro libro){
        LibroDTO libroDTO = new LibroDTO(libro.getTitulo(),libro.getAutor().getNombre(),libro.getIdioma(),libro.getNumeroDescargas());
        System.out.println("-------------------------");
        System.out.println("\n------- LIBRO -----------");
        System.out.println("Titulo: " + libroDTO.titulo());
        System.out.println("Autor: " + libroDTO.autor());
        System.out.println("Idioma: " + libroDTO.idioma());
        System.out.println("Numero de descargas: " + libroDTO.numeroDescargas());
        System.out.println("--------------------------");
        System.out.println();

    }
    //Cuando obtenemos uno o mas autores, los pasamos por este metodo para que se vean mejor presentados
    private void detallesAutor(Autor autor){
        AutorDTO autorDTO = new AutorDTO(autor.getNombre(),autor.getFechaNacimiento(),autor.getFechaFallecimiento(),autor.getLibros()
                .stream()
                .map(Libro::getTitulo)
                .collect(Collectors.toList()));
        System.out.println("-------------------------");
        System.out.println("\n------- AUTOR -----------");
        System.out.println("Nombre: "+autorDTO.nombre());
        System.out.println("Fecha de nacimiento: "+autorDTO.fechaNacimiento());
        System.out.println("Fecha de fallecimiento: "+autorDTO.fechaFallecimiento());
        System.out.println("Libros: "+autorDTO.libros());
        System.out.println("--------------------------");
        System.out.println();
    }

    //Listamos los libros, si hay en la base de datos
    private void listarLibros(){
        List<Libro> librosListados = libroRepository.findAll();
        if(librosListados.isEmpty()){
            System.out.println("No hay libros que listar");
        }else {
            librosListados.forEach(this::detallesLibro);

        }
    }
    //Listamos los autores, si hay en la base de datos
    private void listarAutores(){
        List<Autor> autoresListados = autorRepository.obtenerAutoresConLibros();
        if(autoresListados.isEmpty()){
            System.out.println("No hay autores para listar");
        }else {
            autoresListados.forEach(this::detallesAutor);
        }
    }
    //Listamos los autores vivos en determinado año, si hay en la base de datos
    private void listarAutoresVivos(Integer anio){
        List<Autor> autoresVivos = autorRepository.buscarAutoresVivosEnAnio(anio);
        if(autoresVivos.isEmpty()){
            System.out.println("No hay autores vivos en este año");
        }else {
            autoresVivos.forEach(this::detallesAutor);
        }
    }
    private void autoresPorAnio(){
        try{
            System.out.println("Digite el año para consultar autores: ");
            Integer anio = teclado.nextInt();
            teclado.nextLine();
            listarAutoresVivos(anio);
        }catch (Exception e){
            System.out.println("Por favor digite un valor valido!");
            System.out.println(e);
        }

    }
    private void librosPorIdioma(){
        System.out.println("\nIngrese el idioma para buscar los libros:");
        System.out.println("es - español");
        System.out.println("en - inglés");
        System.out.println("fr - francés");
        System.out.println("pt - portugués");
        String idioma = teclado.nextLine();

        listarLibrosPorIdioma(idioma);
    }
    //Listamos los libros por idioma
    private void listarLibrosPorIdioma(String idioma){
        List<Libro> librosEncontrados = libroRepository.findByIdiomaIgnoreCase(idioma);
        if(librosEncontrados.isEmpty()){
            System.out.println("No hay libros en ese idioma para listar");
        }else{
            librosEncontrados.forEach(this::detallesLibro);
        }
    }


}

