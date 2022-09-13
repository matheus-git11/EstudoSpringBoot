package io.github.matheusgit11.domain.repository;

import io.github.matheusgit11.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Clientes  extends JpaRepository<Cliente , Integer>{
     List<Object> findByNomeLike(String nome); // classe responsavel pelas operacoes na database

}