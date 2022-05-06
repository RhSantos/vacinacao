package model.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import app.Cadastro;
import app.UI;

public class Unidade implements Serializable {
    private Integer id;
    private String nome;
    private Boolean centro;
    private Endereco endereco;

    public Unidade(Integer id, String nome, Boolean centro, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.centro = centro;
        this.endereco = endereco;
    }

    public Unidade(String nome, Boolean centro, Endereco endereco) {
        this.nome = nome;
        this.centro = centro;
        this.endereco = endereco;
    }

    public Unidade(Integer id, String nome, Boolean centro) throws InterruptedException, IOException {
        this.id = id;
        this.nome = nome;
        this.centro = centro;
    }

    public Unidade(String nome, Boolean centro) throws InterruptedException, IOException {
        this.nome = nome;
        this.centro = centro;
    }

    public Unidade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getCentro() {
        return centro;
    }

    public void setCentro(Boolean centro) {
        this.centro = centro;
    }

    @Override
    public String toString() {
        String divisoria = "\n************************\n";
        return "\nUnidade:"+divisoria+"ID: "+id
            +"\nNome: "+nome+(centro == true?"\n--Centro de Distribuição--\n":"\n")+
            endereco+divisoria;
    }

}
