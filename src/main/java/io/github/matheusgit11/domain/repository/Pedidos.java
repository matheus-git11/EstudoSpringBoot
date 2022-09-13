package io.github.matheusgit11.domain.repository;

import io.github.matheusgit11.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido,Integer> {
}
