package io.github.matheusgit11.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    // serve para fazer a definicao das colunas do mesmo jeito que a anatocao Table , nao necessario para nosso exemplo mas bom lembrar
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf" , length = 11)
    private String cpf;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente" , fetch = FetchType.LAZY) // o tipo lazy diz que ao carregar os clientes ele nao carrega os pedidos juntos
    private Set<Pedido> pedidos;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
