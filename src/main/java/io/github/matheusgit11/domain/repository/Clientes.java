package io.github.matheusgit11.domain.repository;

import io.github.matheusgit11.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    //List<Object> findByNomeLike(String nome); usando convensao do query methods
    @Query(value = " select * from Cliente c where c.nome like %:nome% ", nativeQuery = true)
    // essa consulta pode ser feita em HQL ou sql nativo
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id =:id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);


}