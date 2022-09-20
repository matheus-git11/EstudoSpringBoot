package io.github.matheusgit11.rest.controller;

import io.github.matheusgit11.domain.entity.Pedido;
import io.github.matheusgit11.rest.dto.PedidoDTO;
import io.github.matheusgit11.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
       Pedido pedido = service.salvar(dto);
       return pedido.getId();
    }
}
