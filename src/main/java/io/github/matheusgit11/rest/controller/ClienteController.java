package io.github.matheusgit11.rest.controller;

import io.github.matheusgit11.domain.entity.Cliente;
import io.github.matheusgit11.domain.repository.Clientes;
import org.springframework.data.domain.Example;
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
    @ResponseBody // diz a aplicacao que estamos retornando o corpo da resposta
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id); // optional serve para se achar ou nao
        if (cliente.isPresent()) {                       // response entity representa a resposta de uma requisicao
            return ResponseEntity.ok(cliente.get()); // retorna o resultado da requisiscao , codigo 200 , sucesso
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
          clientes.delete(cliente.get());
          return ResponseEntity.noContent().build();
        }
         return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Cliente cliente){

        return clientes
                .findById(id)
                .map(clienteExistente ->{
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return ResponseEntity.noContent().build();
                } ).orElseGet(() -> ResponseEntity.notFound().build());

    }

  //  @GetMapping("/api/clientes")
  //public ResponseEntity find (Cliente filtro){
    //}
}
