package io.github.matheusgit11.repository;

import io.github.matheusgit11.model.Cliente;
import org.springframework.stereotype.Repository;
//anotacao para dizer que essa classe vai acessar a base de dados
// acessar a tabela de clientes , salvar , atualizar ,deletar e por ai vai
@Repository
public class ClientesRepository {
    public void persistir(Cliente cliente) {
        //acessa a base e salva o cliente
    }
}
