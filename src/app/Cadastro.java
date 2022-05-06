package app;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.entities.*;
import model.dao.*;
import model.services.*;

public class Cadastro {
    private static String opcaoS;

    public static void unidade() throws InterruptedException, IOException {
        try {
            System.out.println();
            System.out.println();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Scanner sc = new Scanner(System.in);
            System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
            System.out.println("Cadastro Unidade");
            System.out.println("\u001B[32m" + "       1 - Cadastrar" + "\u001B[0m");
            System.out.println("\u001B[32m" + "       2 - Listar" + "\u001B[0m");
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
                        cadastrarUnidade(sc, 1);
                        voltarOuEncerrar(sc, 1);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println();
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
                        System.out.println("Listar Unidades");
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
                        voltarOuEncerrar(sc, 1);
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
        } catch (NoSuchElementException e) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }

    public static void cadastrarUnidade(Scanner sc, int i) throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Cadastro Unidade");
        System.out.print("Digite o nome da Unidade: ");
        String nome = sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            sc.close();
            return;
        } else if (nome.equals("-")) {
            if (i == 1) {
                unidade();
            } else if (i == 2) {
                UI.menuRotina();
            }
        }

        System.out.print("É um Centro de Distribuição? (s/n): ");
        Character centro = sc.next().charAt(0);
        if (centro == '0') {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            sc.close();
            return;
        } else if (centro == '-') {
            if (i == 1) {
                unidade();
            } else if (i == 2) {
                UI.menuRotina();
            }
        }
        centro = Character.toLowerCase(centro);
        Unidade unidade = new Unidade(nome, centro == 's');
        if(i == 1){
            unidade.setEndereco(InstanciarEndereco.criarEndereco(1));
        }
        else if (i == 2){
            unidade.setEndereco(InstanciarEndereco.criarEndereco(2));
        }
        
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        endDao.inserir(unidade.getEndereco());
        UnidadeSDao.cadastrar(unidade);
        System.out.println("Unidade Cadastrada com sucesso");
        UI.sleep(1.0);
        System.out.print("Imprimindo Unidade");
        UI.sleep(1.0);
        System.out.print(".");
        UI.sleep(1.0);
        System.out.print(".");
        UI.sleep(1.0);
        System.out.println(".");
        UI.sleep(1.0);
        System.out.println(unidade);
    }

    public static void pessoa() throws InterruptedException, IOException {
        try {
            System.out.println();
            System.out.println();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Scanner sc = new Scanner(System.in);
            System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
            System.out.println("Cadastro Populacao");
            System.out.println("\u001B[32m" + "       1 - Cadastrar" + "\u001B[0m");
            System.out.println("\u001B[32m" + "       2 - Listar" + "\u001B[0m");
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
                        cadastrarPessoa(sc,0);
                        voltarOuEncerrar(sc, 2);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println();
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
                        System.out.println("Listar Populacao");
                        System.out.print("Digite as Iniciais do Nome: ");
                        String filtro = sc.nextLine();
                        if (filtro.equals("0")) {
                            System.out.println("Obrigado por usar nosso sistema!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            sc.close();
                            return;
                        } else if (filtro.equals("-"))
                            pessoa();
                        PessoaSDao.listarPrint(filtro);
                        voltarOuEncerrar(sc, 2);
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
        } catch (NoSuchElementException e) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }

    public static void cadastrarPessoa(Scanner sc ,int i) throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Cadastro Populacao");
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            sc.close();
            return;
        } else if (nome.equals("-"))
            if(i == 0){
                pessoa();
            }
            else if(i == 1){
                UI.menuRotina();
            }
        System.out.print("Digite seu CPF: ");
        String cpf = sc.nextLine();
        if (cpf.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            sc.close();
            return;
        } else if (cpf.equals("-")){
            if(i == 1){
                pessoa();
            }
            else if(i == 2){
                UI.menuRotina();
            }
        }
        Pessoa pessoa = new Pessoa(nome, cpf);
        if(i == 0){
            pessoa.setEndereco(InstanciarEndereco.criarEndereco(0));
        }
        else if(i == 1){
            pessoa.setEndereco(InstanciarEndereco.criarEndereco(1));
        }
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        endDao.inserir(pessoa.getEndereco());
        PessoaSDao.cadastrar(pessoa);
        System.out.println("Pessoa Cadastrada com sucesso");
        UI.sleep(1.0);
        System.out.print("Imprimindo Pessoa");
        UI.sleep(1.0);
        System.out.print(".");
        UI.sleep(1.0);
        System.out.print(".");
        UI.sleep(1.0);
        System.out.println(".");
        UI.sleep(1.0);
        System.out.println(pessoa);
    }

    public static void lote() throws InterruptedException, IOException, ParseException {
        try {
            System.out.println();
            System.out.println();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Scanner sc = new Scanner(System.in);
            System.out.println("Cadastro Lote Vacina");
            System.out.println("\u001B[32m" + "       1 - Cadastrar" + "\u001B[0m");
            System.out.println("\u001B[32m" + "       2 - Listar" + "\u001B[0m");
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
                        int cadastro = cadastroLote(sc);
                        if (cadastro != 0)
                            voltarOuEncerrar(sc, 3);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println();
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        System.out.println("Listar Lote Vacina");
                        System.out.print("Digite as Iniciais do Nome da Vacina: ");
                        String filtro = sc.nextLine();
                        if (filtro.equals("0")) {
                            System.out.println("Obrigado por usar nosso sistema!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            sc.close();
                            return;
                        } else if (filtro.equals("-"))
                            lote();
                        LoteSDao.listarPrint(filtro);
                        voltarOuEncerrar(sc, 3);
                        break;
                    default:
                        System.out.println("Essa Opção não Existe!");
                        UI.sleep(2.5);
                        lote();
                        break;
                }
            } catch (NumberFormatException e) {
                if (opcaoS.equals("-"))
                    UI.menuCadastros();
                else {
                    System.out.println("A Opção deve ser apenas Números inteiros!");
                    UI.sleep(2.5);
                    lote();
                }
            }
            sc.close();
        } catch (NoSuchElementException e) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }

    public static int cadastroLote(Scanner sc) throws InterruptedException, IOException, ParseException {
        try {
            System.out.println();
            System.out.println();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
            LoteDao loteDao = DaoFactory.createLoteDao();
            Integer id = 0;
            String idS = "";
            Boolean contemId = true;
            System.out.println("Cadastro Lote Vacina");

            do {
                System.out.print("Digite o ID da Vacina: ");
                try {
                    idS = sc.nextLine();
                    id = Integer.parseInt(idS);
                    if (id == 0) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return 0;
                    }
                    if (id < 0)
                        System.out.println("ID Negativo não é válido!");
                    if (loteDao.procurarPorId(id) != null) {
                        System.out.println("ID de Lote já utilizado! - Tente Novamente!");
                        System.out.println();
                        UI.sleep(2.5);
                        contemId = true;
                    } else {
                        contemId = false;
                    }
                } catch (NumberFormatException e) {
                    if (idS.equals("-"))
                        lote();
                    else {
                        System.out.println("O ID deve ser de apenas Números inteiros!");
                        UI.sleep(2.5);
                        cadastroLote(sc);
                    }
                }
            } while (contemId == true || id <= 0);

            System.out.print("Digite o nome da Vacina: ");
            String nome = sc.nextLine();
            if (nome.equals("0")) {
                System.out.println("Obrigado por usar nosso sistema!");
                UI.sleep(2.5);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                sc.close();
                return 0;
            } else if (nome.equals("-"))
                lote();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataVencimento = "";
            Boolean dataValida = false;
            while (!dataValida) {
                System.out.print("Digite a Data de Validade da Vacina (Formato DD/MM/AAAA): ");
                dataVencimento = sc.nextLine();
                if (dataVencimento.equals("0")) {
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    sc.close();
                    return 0;
                } else if (dataVencimento.equals("-"))
                    lote();

                dataValida = dataVencimento.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$");

                if (!dataValida) {
                    System.out.println("Data Inválida - Tente Novamente!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
            }

            Lote lote = new Lote(id, nome, sdf.parse(dataVencimento));

            LoteSDao.cadastrar(lote);
            System.out.println("Lote Vacina Cadastrado com sucesso");
            UI.sleep(1.0);
            System.out.print("Imprimindo Lote");
            UI.sleep(1.0);
            System.out.print(".");
            UI.sleep(1.0);
            System.out.print(".");
            UI.sleep(1.0);
            System.out.println(".");
            UI.sleep(1.0);
            System.out.println(lote);
            return 1;
        } catch (NoSuchElementException e) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        return 1;
    }

    public static void voltarOuEncerrar(Scanner sc, int opcao) throws InterruptedException, IOException {
        try {
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
                    if (opcao == 1)
                        unidade();
                    else if (opcao == 2)
                        pessoa();
                    else
                        try {
                            lote();
                        } catch (ParseException e) {
                            System.out.println("Formato da Data Inválida!");
                            ;
                        }
                else {
                    System.out.println("Digite uma Opção Válida!");
                }
            }
        } catch (NoSuchElementException e) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }
}
