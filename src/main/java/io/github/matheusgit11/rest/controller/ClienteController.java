package io.github.matheusgit11.rest.controller;

import io.github.matheusgit11.domain.entity.Cliente;
import io.github.matheusgit11.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) { // o spring injeta esses parametros automaticamento , devido as anotacoes
        this.clientes = clientes;
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clientes.findById(id)
                    .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Cliente nao encontrado" ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
      clientes.findById(id)
              .map(clienteAchado -> {
                  clientes.delete(clienteAchado);
                  return clienteAchado;
              }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao Encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody Cliente cliente){

         clientes
                .findById(id)
                .map(clientsExistent ->{
                    cliente.setId(clientsExistent.getId());
                    clientes.save(cliente);
                    return clientsExistent;
                } ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente nao encontrado"));

    }
    @GetMapping
   public List<Cliente> find (Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro,matcher);
        return clientes.findAll(example);

    }














}
