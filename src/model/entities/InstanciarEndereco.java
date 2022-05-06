package model.entities;

import java.io.IOException;
import java.util.Scanner;

import app.Cadastro;
import app.UI;

public class InstanciarEndereco {
    public static Endereco criarEndereco(int i) throws InterruptedException, IOException {
        try(Scanner sc = new Scanner(System.in)){
            Endereco endereco;
            System.out.println("===CADASTRO DE ENDEREÇO===");
            System.out.print("Digite o Logradouro: ");
            String logradouro = sc.nextLine();
            if(voltarOuSairMenu(logradouro,i)){
                sc.close();
                return null;
            }
            System.out.print("Digite a Cidade: ");
            String cidade = sc.nextLine();
            if(voltarOuSairMenu(cidade,i)){
                sc.close();
                return null;
            }
            System.out.print("Digite o Estado (SIGLA): ");
            String estado = sc.nextLine();
            if(voltarOuSairMenu(estado,i)){
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
                    if(numeroS.equals("0")){
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return null;
                    }
                }catch(NumberFormatException e){
                    if(numeroS.equals("-")) {
                        if(i == 0){
                            Cadastro.pessoa();
                        }
                        else if(i == 1){
                            Cadastro.unidade();
                        }
                        else if(i == 2){
                            UI.menuRotina();;
                        }
                    }
                    else {
                        System.out.println("A Opção deve ser apenas Números inteiros!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                }
            }while(numero <= 0);

            System.out.print("Digite o Bairro: ");
            String bairro = sc.nextLine();
            if(voltarOuSairMenu(bairro,i)){
                sc.close();
                return null;
            }
            System.out.print("Digite o Complemento: ");
            String complemento = sc.nextLine();
            if(voltarOuSairMenu(complemento,i)){
                sc.close();
                return null;
            }
            if(complemento == "") complemento = null;
            System.out.print("Digite o CEP (Com Pontuação): ");
            String cep = sc.nextLine();
            
            if(voltarOuSairMenu(cep,i)){
                sc.close();
                return null;
            }
            endereco = new Endereco
            (logradouro, cidade, estado, numero, bairro, complemento, cep);

            return endereco;
        }
    }

    public static boolean voltarOuSairMenu(String s,int i) throws InterruptedException, IOException{
        if(s.equals("0")){
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return true;
        }
        else if(s.equals("-")){
            if(i == 0){
                Cadastro.pessoa();
            }
            else if(i == 1){
                Cadastro.unidade();
            }
            else if(i == 2){
                UI.menuRotina();;
            }
        }
        return false;
    }
}
