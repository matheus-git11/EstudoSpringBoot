package io.github.matheusgit11.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") // serve para fazer a definicao das colunas do mesmo jeito que a anatocao Table , nao necessario para nosso exemplo mas bom lembrar
    private Integer id;

    @Column(name = "nome" , length = 100)
    private String nome;

    public Cliente(){

    }
    public Cliente(String nome){
        this.nome= nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
