package model.entities;

import java.io.IOException;

import app.Cadastro;
import app.UI;

public class InstanciarEndereco {
    public static Endereco criarEndereco(int i) throws InterruptedException, IOException {
        Endereco endereco;
        System.out.println("===CADASTRO DE ENDEREÇO===");
        System.out.print("Digite o Logradouro: ");
        String logradouro = UI.sc.nextLine();
        if (voltarOuSairMenu(logradouro, i)) {
            return null;
        }
        System.out.print("Digite a Cidade: ");
        String cidade = UI.sc.nextLine();
        if (voltarOuSairMenu(cidade, i)) {
            return null;
        }
        System.out.print("Digite o Estado (SIGLA): ");
        String estado = UI.sc.nextLine();
        if (voltarOuSairMenu(estado, i)) {
            return null;
        }
        estado = estado.toLowerCase();
        Integer numero = 0;
        String numeroS = "";
        do {
            try {
                System.out.print("Digite o Número: ");
                numeroS = UI.sc.nextLine();
                numero = Integer.parseInt(numeroS);
                if (numeroS.equals("0")) {
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    return null;
                }
            } catch (NumberFormatException e) {
                if (numeroS.equals("-")) {
                    if (i == 0) {
                        Cadastro.pessoa();
                    } else if (i == 1) {
                        Cadastro.unidade();
                    } else if (i == 2) {
                        UI.menuRotina();
                        ;
                    }
                } else {
                    System.out.println("A Opção deve ser apenas Números inteiros!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
            }
        } while (numero <= 0);

        System.out.print("Digite o Bairro: ");
        String bairro = UI.sc.nextLine();
        if (voltarOuSairMenu(bairro, i)) {
            return null;
        }
        System.out.print("Digite o Complemento: ");
        String complemento = UI.sc.nextLine();
        if (voltarOuSairMenu(complemento, i)) {
            return null;
        }
        if (complemento == "")
            complemento = null;
        System.out.print("Digite o CEP (Com Pontuação): ");
        String cep = UI.sc.nextLine();

        if (voltarOuSairMenu(cep, i)) {
            return null;
        }
        endereco = new Endereco(logradouro, cidade, estado, numero, bairro, complemento, cep);

        return endereco;

    }

    public static boolean voltarOuSairMenu(String s, int i) throws InterruptedException, IOException {
        if (s.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return true;
        } else if (s.equals("-")) {
            if (i == 0) {
                Cadastro.pessoa();
            } else if (i == 1) {
                Cadastro.unidade();
            } else if (i == 2) {
                UI.menuRotina();
                ;
            }
        }
        return false;
    }
}
