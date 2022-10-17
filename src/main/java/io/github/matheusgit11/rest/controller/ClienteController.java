package io.github.matheusgit11.rest.controller;

import io.github.matheusgit11.domain.entity.Cliente;
import io.github.matheusgit11.domain.repository.Clientes;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) { // o spring injeta esses parametros automaticamento , devido as anotacoes
        this.clientes = clientes;
    }

    @GetMapping("{id}")
    @ApiOperation("Obter Detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200 , message = "Cliente encontrado com sucesso"),
            @ApiResponse(code = 404 , message = "Cliente nao encontrado para o ID informado")
    })
    public Cliente getClienteById(@PathVariable @ApiParam("Id do cliente") Integer id) {
        return clientes.findById(id)
                    .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Cliente nao encontrado" ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo Cliente")
    @ApiResponses({
            @ApiResponse(code = 201 , message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400 , message = "Erro de validacao")
    })
    public Cliente save(@RequestBody @Valid Cliente cliente){
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
    public void update(@PathVariable @Valid Integer id,
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
