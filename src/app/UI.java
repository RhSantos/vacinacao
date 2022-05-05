package app;

import java.io.IOException;
import java.util.Scanner;

public class UI {
    private static String opcaoS;

    public static void menu() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("1 - Cadastros");
        System.out.println("        1 - Cadastro Unidade");
        System.out.println("                1 - Cadastrar");
        System.out.println("                2 - Listar");
        System.out.println("        2 - Cadastro Populacao");
        System.out.println("                1 - Cadastrar");
        System.out.println("                2 - Listar");
        System.out.println("        3 - Cadastro Lote Vacina");
        System.out.println("                1 - Cadastrar");
        System.out.println("                2 - Listar");
        System.out.println("2 - Rotinas");
        System.out.println("        1 - Recebimento de Vacinas");
        System.out.println("        2 - Transferencia de Vacinas");
        System.out.println("        3 - Aplicacao de Vacinas");
        System.out.println("3 - Relatorios");
        System.out.println("        1 - Estoque da Vacina");
        System.out.println("        2 - Aplicacao");
        System.out.println("        3 - Resumo Aplicacao");
        System.out.println("        4 - Pessoas com Esquema Vacinal Imcompleto");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    sc.close();
                    System.out.println("Obrigado por usar nosso sistema!");
                    sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case 1:
                    menuCadastros();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Essa Opção não Existe!");
                    sleep(2.5);
                    menu();
                    break;
            }
        } catch (NumberFormatException e) {
            if (opcaoS.equals("-"))
                menu();
            else {
                System.out.println("A Opção deve ser apenas Números inteiros!");
                sleep(2.5);
                menu();
            }
        }
        sc.close();
    }

    public static void menuCadastros() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("1 - Cadastro Unidade");
        System.out.println("        1 - Cadastrar");
        System.out.println("        2 - Listar");
        System.out.println("2 - Cadastro Populacao");
        System.out.println("        1 - Cadastrar");
        System.out.println("        2 - Listar");
        System.out.println("3 - Cadastro Lote Vacina");
        System.out.println("        1 - Cadastrar");
        System.out.println("        2 - Listar");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    sc.close();
                    System.out.println("Obrigado por usar nosso sistema!");
                    sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case 1:
                    Cadastro.unidade();
                    break;
                case 2:
                    Cadastro.pessoa();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Essa Opção não Existe!");
                    sleep(2.5);
                    menuCadastros();
                    break;
            }
        } catch (NumberFormatException e) {
            if (opcaoS.equals("-"))
                menu();
            else {
                System.out.println("A Opção deve ser apenas Números inteiros!");
                sleep(2.5);
                menuCadastros();
            }
        }
        sc.close();
    }

    public static void sleep(Double segundos) {
        String tempo = "" + (int) (segundos * 1000);
        try {
            Thread.sleep(Long.parseLong(tempo));
        } catch (InterruptedException ex) {
        }
    }
}
