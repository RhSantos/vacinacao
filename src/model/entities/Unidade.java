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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((centro == null) ? 0 : centro.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Unidade other = (Unidade) obj;
        if (centro == null) {
            if (other.centro != null)
                return false;
        } else if (!centro.equals(other.centro))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String divisoria = "\n************************\n";
        return "\nUnidade:"+divisoria+"ID: "+id
            +"\nNome: "+nome+(centro == true?"\n--Centro de Distribuição--\n":"\n")+
            endereco+divisoria;
    }

}
