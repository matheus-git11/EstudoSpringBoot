package io.github.matheusgit11.rest.controller;

import io.github.matheusgit11.domain.entity.Cliente;
import io.github.matheusgit11.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) { // o spring injeta esses parametros automaticamento , devido as anotacoes
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()){                       // response entity representa a resposta de uma requisicao
            return ResponseEntity.ok(cliente.get()); // retorna o resultado da requisiscao , codigo 200 , sucesso
        }
        return ResponseEntity.notFound().build();
    }
}
