package io.github.matheusgit11;


import io.github.matheusgit11.domain.entity.Cliente;
import io.github.matheusgit11.domain.entity.Pedido;
import io.github.matheusgit11.domain.repository.Clientes;
import io.github.matheusgit11.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {

        return args -> {
            System.out.println("Salvando clientes");
            Cliente cliente1 = new Cliente("Matheus");
            clientes.save(cliente1);

            Pedido p = new Pedido();
            p.setCliente(cliente1);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);
            Cliente cliente = clientes.findClienteFetchPedidos(cliente1.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());

             pedidos.findByCliente(cliente1).forEach(System.out::println);
        };

    }

}

