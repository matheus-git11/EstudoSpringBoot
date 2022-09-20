package io.github.matheusgit11.service.impl;

import io.github.matheusgit11.domain.repository.Pedidos;
import io.github.matheusgit11.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
