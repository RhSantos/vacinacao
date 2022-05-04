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

    public static Endereco criarEndereco() {
        Scanner sc = new Scanner(System.in);
        Endereco endereco;
        System.out.println("===CADASTRO DE ENDEREÇO===");
        System.out.print("Digite o Logradouro: ");
        String logradouro = sc.nextLine();
        System.out.print("Digite a Cidade: ");
        String cidade = sc.nextLine();
        System.out.print("Digite o Estado (SIGLA): ");
        String estado = sc.nextLine();
        estado = estado.toLowerCase();
        System.out.print("Digite o Número: ");
        Integer numero = sc.nextInt();
        System.out.print("Digite o Bairro: ");
        sc.nextLine();
        String bairro = sc.nextLine();
        System.out.print("Digite o Complemento: ");
        String complemento = sc.nextLine();
        if(complemento == "") complemento = null;
        System.out.print("Digite o CEP (Com Pontuação): ");
        String cep = sc.nextLine();

        endereco = new Endereco
        (logradouro, cidade, estado, numero, bairro, complemento, cep);

        sc.close();
        return endereco;
    }

    @Override
    public String toString() {
        return "Unidade [centro=" + centro + ", endereco=" + endereco + ", id=" + id + ", nome=" + nome + "]";
    }

}
