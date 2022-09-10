package io.github.matheusgit11;


import io.github.matheusgit11.domain.entity.Cliente;
import io.github.matheusgit11.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.SchemaOutputResolver;
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
            clientes.salvar( new Cliente("Matheus"));
            clientes.salvar( new Cliente("Trevor"));

            List<Cliente> todosCLientes = clientes.obterTodos();
            todosCLientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosCLientes.forEach(c ->{
                c.setNome(c.getNome()+" atualizado");
                clientes.atualizar(c);
            });
            todosCLientes = clientes.obterTodos();
            todosCLientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clientes.buscarPorNome("evor").forEach(System.out::println);

            System.out.println("Deletando clientes");
            clientes.obterTodos().forEach(c->{
                clientes.deletar(c);
            });

            todosCLientes = clientes.obterTodos();
            if(todosCLientes.isEmpty()){
                System.out.println("Nao ha resultados para essa busca , esta vazio");
            }else{
                todosCLientes.forEach(System.out::println);
            }
        };

    }

}

