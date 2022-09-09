package io.github.matheusgit11.domain.repository;

import io.github.matheusgit11.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository  //anotacao para o spring reconhecer esta classe como um repositorio e faz operacao na base de dados
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values (?)"; // script basico de sql para fazer um insert , valor como ponto de interrogacao pois ainda vamos defini-lo
    private static String SELECT_ALL = "SELECT * FROM CLIENTE"; // script basico para selecionar todos os clientes da database
    private static String UPDATE = "update cliente set nome = ? where id = ? ";
    private static String DELETE = "delete from cliente where id = ? ";
    @Autowired
    private JdbcTemplate jdbcTemplate; //ja com a conexao configurada e permite que facamos operacoes com a base de dados


    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT , new Object[]{cliente.getNome()}) ;//recebe 2 parametros , comando sql e o objeto
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getNome(),cliente.getId()
            });
        return cliente;
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }

    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat("where nome like ? "),
                new Object[]{"%" + nome + "%"},
                getClienteRowMapper());
    }


    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, getClienteRowMapper());
    }

    private static RowMapper<Cliente> getClienteRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}