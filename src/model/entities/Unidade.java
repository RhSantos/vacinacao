package model.entities;

import java.io.Serializable;
import java.util.Scanner;

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

    public Unidade(Integer id, String nome, Boolean centro) {
        this.id = id;
        this.nome = nome;
        this.centro = centro;
        this.endereco = criarEndereco();
    }

    public Unidade(String nome, Boolean centro) {
        this.nome = nome;
        this.centro = centro;
        this.endereco = criarEndereco();
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

    public Endereco criarEndereco() {
        Scanner sc = new Scanner(System.in);
        Endereco endereco;
        String logradouro = sc.nextLine();
        String cidade = sc.next();
        String estado = sc.next();
        Integer numero = sc.nextInt();
        sc.next();
        String bairro = sc.next();
        String cep = sc.next();

        endereco = new Endereco(logradouro, cidade, estado, numero, bairro, cep);

        sc.close();
        return endereco;
    }
}
