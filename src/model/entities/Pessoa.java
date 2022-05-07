package model.entities;

import java.io.IOException;

public class Pessoa {
    private Integer id;
    private String nome;
    private String cpf;
    private Endereco endereco;

    public Pessoa(Integer id, String nome, String cpf, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public Pessoa(String nome, String cpf, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public Pessoa(Integer id, String nome, String cpf) throws InterruptedException, IOException {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Pessoa(String nome, String cpf) throws InterruptedException, IOException {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public Pessoa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer pessoa) {
        this.id = pessoa;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public Endereco getEndereco() {
        return endereco;
    }


    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
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
        Pessoa other = (Pessoa) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
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
        String divisoria = "\n........................\n";
        return "\nPopulacao:"+divisoria+"ID: "+id
            +"\nNome: "+nome+"\nCpf: "+cpf+"\n"+
            endereco+divisoria;
    }
    
}
