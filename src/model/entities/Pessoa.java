package model.entities;

import java.io.Serializable;
import java.util.Scanner;

public class Pessoa implements Serializable {
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

    public Pessoa(Integer id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = criarEndereco();
    }

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = criarEndereco();
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

    public Endereco criarEndereco(){
        Scanner sc = new Scanner(System.in);
        Endereco endereco;
        String logradouro = sc.nextLine();
        String cidade = sc.next();
        String estado = sc.next();
        Integer numero = sc.nextInt();
        sc.next();
        String bairro = sc.next();
        String complemento = sc.next();
        String cep = sc.next();
        

        endereco = new Endereco
        (logradouro, cidade, estado, numero, bairro, complemento, cep);

        sc.close();
        return endereco;
    }

    @Override
    public String toString() {
        return "Pessoa [cpf=" + cpf + ", endereco=" + endereco + ", id=" + id + ", nome=" + nome + "]";
    }
    
}
