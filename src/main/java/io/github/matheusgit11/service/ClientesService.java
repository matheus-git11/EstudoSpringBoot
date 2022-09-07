package io.github.matheusgit11.service;

import io.github.matheusgit11.model.Cliente;
import io.github.matheusgit11.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//classe que tem toda regra de negocio , validacoes , acessar base de dados
// a classe cliente service vai precisar de classe clientes repository para poder salvar os clientes que ele vai tratar

@Service
public class ClientesService {

    private ClientesRepository repository; // criando uma instancia para injetar a depedencia do repositorio

    public ClientesService(ClientesRepository repository){ // instancia da classe repository
        this.repository = repository; // injecao de dependencias

    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente); // metodo para salvar
    }

    public void validarCliente(Cliente cliente){
    //aplica validacoes
    }
}
