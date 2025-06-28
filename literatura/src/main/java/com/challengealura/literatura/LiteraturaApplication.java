package com.challengealura.literatura;

import com.challengealura.literatura.principal.Principal;
import com.challengealura.literatura.repository.AutorRepository;
import com.challengealura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
	//Hacemos la inyeccion de dependencia e implementamos el metodo de CommandLineRunner para iniciar nuestra aplicacion
	@Autowired
    private LibroRepository LibroRepository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(LibroRepository,autorRepository);
		principal.iniciarAplicacion();
	}
}
