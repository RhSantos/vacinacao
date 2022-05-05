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
        this.endereco = criarEndereco();
    }

    public Unidade(String nome, Boolean centro) throws InterruptedException, IOException {
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

    public static Endereco criarEndereco() throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        Endereco endereco;
        System.out.println("===CADASTRO DE ENDEREÇO===");
        System.out.print("Digite o Logradouro: ");
        String logradouro = sc.nextLine();
        if(voltarOuSairMenu(logradouro)){
            sc.close();
            return null;
        }
        System.out.print("Digite a Cidade: ");
        String cidade = sc.nextLine();
        if(voltarOuSairMenu(cidade)){
            sc.close();
            return null;
        }
        System.out.print("Digite o Estado (SIGLA): ");
        String estado = sc.nextLine();
        if(voltarOuSairMenu(estado)){
            sc.close();
            return null;
        }
        estado = estado.toLowerCase();
        Integer numero = 0;
        String numeroS = "";
        do{
            try{
                System.out.print("Digite o Número: ");
                numeroS = sc.nextLine();
                numero = Integer.parseInt(numeroS);
            }catch(NumberFormatException e){
                if(numeroS.equals("0")){
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    sc.close();
                    return null;
                }
                else if(numeroS.equals("-")) Cadastro.unidade();
                else {
                    System.out.println("A Opção deve ser apenas Números inteiros!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
            }
        }while(numero <= 0);

        System.out.print("Digite o Bairro: ");
        String bairro = sc.nextLine();
        if(voltarOuSairMenu(bairro)){
            sc.close();
            return null;
        }
        System.out.print("Digite o Complemento: ");
        String complemento = sc.nextLine();
        if(voltarOuSairMenu(complemento)){
            sc.close();
            return null;
        }
        if(complemento == "") complemento = null;
        System.out.print("Digite o CEP (Com Pontuação): ");
        String cep = sc.nextLine();
        if(voltarOuSairMenu(cep)){
            sc.close();
            return null;
        }
        endereco = new Endereco
        (logradouro, cidade, estado, numero, bairro, complemento, cep);
    
        return endereco;
    }

    @Override
    public String toString() {
        String divisoria = "\n************************\n";
        return "\nUnidade:"+divisoria+"ID: "+id
            +"\nNome: "+nome+(centro == true?"\n--Centro de Distribuição--":"\n")+
            endereco+divisoria;
    }

    public static boolean voltarOuSairMenu(String s) throws InterruptedException, IOException{
        if(s.equals("0")){
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return true;
        }
        else if(s.equals("-")) Cadastro.unidade();
        return false;
    }

}
