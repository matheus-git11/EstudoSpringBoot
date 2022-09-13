package io.github.matheusgit11.domain.repository;

import io.github.matheusgit11.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
