package io.github.matheusgit11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Value("${application.name}") //usando o application.properties e nao mais o configuration
    private String applicationName;

    @Gato
    private Animal animal;
    @Cachorro
    private Animal animal2;

    @Bean(name= "executarAnimal")
    public CommandLineRunner executar(){
        return args -> {
            this.animal.fazerBarulho();
            this.animal2.fazerBarulho();
        };
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }
}
