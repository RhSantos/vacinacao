package app;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class UI {
    private static String opcaoS;
    public static Scanner sc = new Scanner(System.in);

    public static void menu() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("\u001B[32m" + "1 - Cadastros" + "\u001B[0m");
        System.out.println("        1 - Cadastro Unidade");
        System.out.println("                1 - Cadastrar");
        System.out.println("                2 - Listar");
        System.out.println("        2 - Cadastro Populacao");
        System.out.println("                1 - Cadastrar");
        System.out.println("                2 - Listar");
        System.out.println("        3 - Cadastro Lote Vacina");
        System.out.println("                1 - Cadastrar");
        System.out.println("                2 - Listar");
        System.out.println("\u001B[32m" + "2 - Rotinas" + "\u001B[0m");
        System.out.println("        1 - Recebimento de Vacinas");
        System.out.println("        2 - Transferencia de Vacinas");
        System.out.println("        3 - Aplicacao de Vacinas");
        System.out.println("\u001B[32m" + "3 - Relatorios" + "\u001B[0m");
        System.out.println("        1 - Estoque da Vacina");
        System.out.println("        2 - Aplicacao");
        System.out.println("        3 - Resumo Aplicacao");
        System.out.println("        4 - Pessoas com Esquema Vacinal Imcompleto");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
                    break;
                case 1:
                    menuCadastros();
                    break;
                case 2:
                    menuRotina();
                    break;
                case 3:
                    menuRelatorios();
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
    }

    public static void menuCadastros() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("\u001B[32m" + "1 - Cadastro Unidade" + "\u001B[0m");
        System.out.println("        1 - Cadastrar");
        System.out.println("        2 - Listar");
        System.out.println("\u001B[32m" + "2 - Cadastro Populacao" + "\u001B[0m");
        System.out.println("        1 - Cadastrar");
        System.out.println("        2 - Listar");
        System.out.println("\u001B[32m" + "3 - Cadastro Lote Vacina" + "\u001B[0m");
        System.out.println("        1 - Cadastrar");
        System.out.println("        2 - Listar");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
                    break;
                case 1:
                    Cadastro.unidade();
                    break;
                case 2:
                    Cadastro.pessoa();
                    break;
                case 3:
                    Cadastro.lote();
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
        } catch (ParseException e) {
            System.out.println("Formato da Data Inválida!");
            ;
        }
    }

    public static void menuRotina() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("\u001B[32m" + "1 - Recebimento de Vacinas" + "\u001B[0m");
        System.out.println("\u001B[32m" + "2 - Transferencia de Vacinas" + "\u001B[0m");
        System.out.println("\u001B[32m" + "3 - Aplicacao de Vacinas" + "\u001B[0m");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
                    break;
                case 1:
                    Rotina.recebimento();
                    break;
                case 2:
                    Rotina.transferencia();
                    break;
                case 3:
                    Rotina.aplicacao();
                    break;
                default:
                    System.out.println("Essa Opção não Existe!");
                    sleep(2.5);
                    menuRelatorios();
                    break;
            }
        } catch (NumberFormatException e) {
            if (opcaoS.equals("-"))
                menu();
            else {
                System.out.println("A Opção deve ser apenas Números inteiros!");
                sleep(2.5);
                menuRelatorios();
            }
        }

        catch (ParseException e) {
        }
    }

    public static void menuRelatorios() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("\u001B[32m" + "1 - Estoque da Vacina" + "\u001B[0m");
        System.out.println("\u001B[32m" + "2 - Aplicacao" + "\u001B[0m");
        System.out.println("\u001B[32m" + "3 - Resumo Aplicacao" + "\u001B[0m");
        System.out.println("\u001B[32m" + "4 - Pessoas com Esquema Vacinal Incompleto" + "\u001B[0m");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
                    break;
                case 1:
                    Relatorios.estoqueVacina();
                    break;
                case 2:
                    Relatorios.aplicacao();
                    break;
                case 3:
                    Relatorios.resumoAplicacao();
                    break;
                case 4:
                    Relatorios.esquemaVacinalIncompleto();
                    break;
                default:
                    System.out.println("Essa Opção não Existe!");
                    sleep(2.5);
                    menuRelatorios();
                    break;
            }
        } catch (NumberFormatException e) {
            if (opcaoS.equals("-"))
                menu();
            else {
                System.out.println("A Opção deve ser apenas Números inteiros!");
                sleep(2.5);
                menuRelatorios();
            }
        }

    }

    public static void sleep(Double segundos) {
        String tempo = "" + (int) (segundos * 1000);
        try {
            Thread.sleep(Long.parseLong(tempo));
        } catch (InterruptedException ex) {
        }
    }
}
