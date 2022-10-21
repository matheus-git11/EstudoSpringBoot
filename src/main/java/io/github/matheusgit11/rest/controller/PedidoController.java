package io.github.matheusgit11.rest.controller;

import io.github.matheusgit11.domain.entity.ItemPedido;
import io.github.matheusgit11.domain.entity.Pedido;
import io.github.matheusgit11.domain.enums.StatusPedido;
import io.github.matheusgit11.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.matheusgit11.rest.dto.InformacaoItemPedidoDTO;
import io.github.matheusgit11.rest.dto.InformacoesPedidoDTO;
import io.github.matheusgit11.rest.dto.PedidoDTO;
import io.github.matheusgit11.service.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
@Api("Api Pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Salva um novo Pedido")
    @ApiResponses({
            @ApiResponse(code = 201 , message = "Pedido salvo com sucesso"),
            @ApiResponse(code = 400 , message = "Erro de validacao")
    })
    public Integer save(@RequestBody @Valid PedidoDTO dto){
       Pedido pedido = service.salvar(dto);
       return pedido.getId();
    }

    @GetMapping("{id}")
    @ApiOperation("Obter Detalhes de um pedido")
    @ApiResponses({
            @ApiResponse(code = 200 , message = "Pedido encontrado com sucesso"),
            @ApiResponse(code = 404 , message = "pedido nao encontrado para o ID informado")
    })
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoCompleto(id)
                .map( p -> converter(p))
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"Pedido nao encontrado"));
    }

    @PatchMapping("{id}") //Diferente do put mapping em que voce nao tem que passar todos os dados
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Atualizar status")
    public void updateStatus(@RequestBody AtualizacaoStatusPedidoDTO dto, @PathVariable Integer id){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));

    }



    private InformacoesPedidoDTO converter(Pedido pedido){
           return InformacoesPedidoDTO
                    .builder()
                    .codigo(pedido.getId())
                    .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                    .cpf(pedido.getCliente().getCpf())
                    .nomeCliente(pedido.getCliente().getNome())
                    .total(pedido.getTotal())
                    .status(pedido.getStatus().name())
                    .items(converter(pedido.getItens()))
                    .build();

    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens
                .stream()
                .map(item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                ).collect(Collectors.toList());

    }
}
