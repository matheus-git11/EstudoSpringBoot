package io.github.matheusgit11;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


// criando uma anotacao customizada chamada Development e faz com que minha configuracao herde as anotacoes dela
// este codigo era rodar apenas se no application properties estiver definido o development
@Development
public class MinhaConfiguration {

    @Bean
    public CommandLineRunner executar(){
         return args -> {
             System.out.println("Rodando a configuracao de desenvolvimento");
         };
    }

}
