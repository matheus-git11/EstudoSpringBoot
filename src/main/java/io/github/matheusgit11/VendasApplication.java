package io.github.matheusgit11;


import io.github.matheusgit11.domain.entity.Cliente;
import io.github.matheusgit11.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){

        return args -> {
            System.out.println("Salvando clientes");
            clientes.save( new Cliente("Matheus"));
            clientes.save( new Cliente("Trevor"));


            List<Cliente> result  = clientes.encontrarPorNome("theus");
            result.forEach(System.out::println);


        };

    }

}

