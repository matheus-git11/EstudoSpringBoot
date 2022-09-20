package io.github.matheusgit11.service;

import io.github.matheusgit11.domain.entity.Pedido;
import io.github.matheusgit11.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);
}
