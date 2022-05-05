package app;

import java.io.IOException;
import java.util.Scanner;

import model.entities.*;
import model.dao.*;
import model.services.*;

public class Cadastro {
    private static String opcaoS;

    public static void unidade() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Scanner sc = new Scanner(System.in);
        System.out.println("Cadastro Unidade");
        System.out.println("\u001B[32m"+"       1 - Cadastrar"+"\u001B[0m");
        System.out.println("\u001B[32m"+"       2 - Listar"+"\u001B[0m");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    sc.close();
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case 1:
                    System.out.println();
                    System.out.println();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.out.println("===CADASTRO DE UNIDADES===");
                    System.out.print("Digite o nome da Unidade: ");
                    String nome = sc.nextLine();
                    if (nome.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return;
                    } else if (nome.equals("-"))
                        unidade();
                    System.out.print("É um Centro de Distribuição? (s/n): ");
                    Character centro = sc.next().charAt(0);
                    if (centro == '0') {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return;
                    } else if (centro == '-')
                        unidade();
                    centro = Character.toLowerCase(centro);
                    Unidade unidade = new Unidade(nome, centro == 's');

                    EnderecoDao endDao = DaoFactory.createEnderecoDao();
                    endDao.inserir(unidade.getEndereco());
                    UnidadeSDao.cadastrar(unidade);
                    voltarOuEncerrar(sc,1);
                    break;
                case 2:
                    System.out.println();
                    System.out.println();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.out.println("===LISTAGEM DE UNIDADES===");
                    System.out.print("Digite as iniciais das Unidades (EX: UBS): ");
                    String filtro = sc.nextLine();
                    if (filtro.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return;
                    } else if (filtro.equals("-"))
                        unidade();
                    UnidadeSDao.listarPrint(filtro);
                    voltarOuEncerrar(sc,1);
                    break;
                default:
                    System.out.println("Essa Opção não Existe!");
                    UI.sleep(2.5);
                    unidade();
                    break;
            }
        } catch (NumberFormatException e) {
            if (opcaoS.equals("-"))
                UI.menuCadastros();
            else {
                System.out.println("A Opção deve ser apenas Números inteiros!");
                UI.sleep(2.5);
                unidade();
            }
        }
        sc.close();
    }

    public static void pessoa() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Scanner sc = new Scanner(System.in);
        System.out.println("Cadastro Populacao");
        System.out.println("\u001B[32m"+"       1 - Cadastrar"+"\u001B[0m");
        System.out.println("\u001B[32m"+"       2 - Listar"+"\u001B[0m");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    sc.close();
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case 1:
                    System.out.println();
                    System.out.println();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.out.println("===CADASTRO DE PESSOA===");
                    System.out.print("Digite seu nome: ");
                    String nome = sc.nextLine();
                    if (nome.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return;
                    } else if (nome.equals("-"))
                        pessoa();
                    System.out.print("Digite seu cpf: ");
                    String cpf = sc.nextLine();
                    if (cpf.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return;
                    } else if (cpf.equals("0"))
                        pessoa();
                    Pessoa pessoa = new Pessoa(nome, cpf);
                    EnderecoDao endDao = DaoFactory.createEnderecoDao();
                    endDao.inserir(pessoa.getEndereco());
                    // PessoaSDao.cadastrar(pessoa);
                    voltarOuEncerrar(sc,2);
                    break;
                case 2:
                    System.out.println();
                    System.out.println();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.out.println("===LISTAGEM DE UNIDADES===");
                    System.out.print("Digite as iniciais das Unidades (EX: UBS): ");
                    String filtro = sc.nextLine();
                    if (filtro.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return;
                    } else if (filtro.equals("-"))
                        pessoa();
                    // PessoaSDao.listarPrint(filtro);
                    voltarOuEncerrar(sc,2);
                    break;
                default:
                    System.out.println("Essa Opção não Existe!");
                    UI.sleep(2.5);
                    pessoa();
                    break;
            }
        } catch (NumberFormatException e) {
            if (opcaoS.equals("-"))
                UI.menuCadastros();
            else {
                System.out.println("A Opção deve ser apenas Números inteiros!");
                UI.sleep(2.5);
                pessoa();
            }
        }
        sc.close();
    }

    public static void voltarOuEncerrar(Scanner sc,int opcao) throws InterruptedException, IOException {
        String comando = "";
        while (!comando.equals("-") || !comando.equals("0")) {
            System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
            System.out.print("Digite uma Opção: ");
            comando = sc.nextLine();

            if (comando.equals("0")) {
                System.out.println("Obrigado por usar nosso sistema!");
                UI.sleep(2.5);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                sc.close();
                return;
            } else if (comando.equals("-"))
                if(opcao == 1) unidade();
                else if (opcao == 2) pessoa();
            else {
                System.out.println("Digite uma Opção Válida!");
            }
        }
    }
}