package io.github.matheusgit11.rest.controller;

import io.github.matheusgit11.domain.entity.Produto;

import io.github.matheusgit11.domain.repository.Produtos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/produtos")
@Api("Api produtos")
public class ProdutoController {

    private Produtos repository;

    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    @ApiOperation("Obter Detalhes de um Produto")
    @ApiResponses({
            @ApiResponse(code = 200 , message = "Produto encontrado com sucesso"),
            @ApiResponse(code = 404 , message = "Produto nao encontrado para o ID informado")
    })
    public Produto getProdutoById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto nao encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo Produto")
    @ApiResponses({
            @ApiResponse(code = 201 , message = "Produto salvo com sucesso"),
            @ApiResponse(code = 400 , message = "Erro de validacao")
    })
    public Produto save(@RequestBody @Valid Produto produto) {
        return repository.save(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deletar Produto pelo Id")
    @ApiResponses({
            @ApiResponse(code = 200 , message = "Produto Deletado com sucesso"),
            @ApiResponse(code = 204 , message = "Produto Nao encontrado")
    })
    public void delete(@PathVariable Integer id) {
        repository.findById(id)
                .map(produto -> {
                    repository.delete(produto);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto nao encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Atualizar Produto")
    @ApiResponses({
            @ApiResponse(code = 200 , message = "Produto Atualizado com sucesso"),
            @ApiResponse(code = 404 , message = "Produto Nao encontrado")
    })
    public void update(@RequestBody @Valid Produto produto,
                       @PathVariable Integer id) {
        repository
                .findById(id)
                .map(FoundProduct -> {
                    produto.setId(FoundProduct.getId());
                    repository.save(produto);
                    return FoundProduct;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto nao encontrado"));
    }


    @GetMapping
    @ApiOperation("Listar todos os Produtos")
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro,matcher);
        return repository.findAll(example);
    }


}
