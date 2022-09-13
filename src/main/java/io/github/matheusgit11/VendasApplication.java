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

            List<Cliente> todosCLientes = clientes.findAll();
            todosCLientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosCLientes.forEach(c ->{
                c.setNome(c.getNome()+" atualizado");
                clientes.save(c);
            });
            todosCLientes = clientes.findAll();
            todosCLientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clientes.findByNomeLike("evor").forEach(System.out::println);

            System.out.println("Deletando clientes");
            clientes.findAll().forEach(c->{
                clientes.delete(c);
            });

            todosCLientes = clientes.findAll();
            if(todosCLientes.isEmpty()){
                System.out.println("Nao ha resultados para essa busca , esta vazio");
            }else{
                todosCLientes.forEach(System.out::println);
            }
        };

    }

}

