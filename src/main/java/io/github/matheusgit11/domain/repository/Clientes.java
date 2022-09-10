package io.github.matheusgit11.domain.repository;

import io.github.matheusgit11.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository  //anotacao para o spring reconhecer esta classe como um repositorio e faz operacao na base de dados
public class Clientes { // classe responsavel pelas operacoes na database

    @Autowired
    private EntityManager entityManager; // faz todas as operacoes nas interfaces

    @Transactional //anotacao que diz que o metodo vai gerar uma transacao na base de dados
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }
    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }
    @Transactional
    public void deletar(Cliente cliente){
      if(!entityManager.contains(cliente)){
          cliente = entityManager.merge(cliente);
      }
        entityManager.remove(cliente);
    }
    @Transactional
    public void deletar(Integer id){
       Cliente cliente = entityManager.find(Cliente.class,id);
       deletar(cliente);
    }
    @Transactional(readOnly = true) // essa transacao e apenas de leitura
    public List<Cliente> buscarPorNome(String nome){
        String jpql = " select c from Cliente c where c.nome like :nome  ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql,Cliente.class);
        query.setParameter("nome","%" + nome + "%");
        return query.getResultList();
    }
    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}