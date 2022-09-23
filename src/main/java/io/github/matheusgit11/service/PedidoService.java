package io.github.matheusgit11.service;

import io.github.matheusgit11.domain.entity.Pedido;
import io.github.matheusgit11.domain.enums.StatusPedido;
import io.github.matheusgit11.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus( Integer id , StatusPedido status);
}
