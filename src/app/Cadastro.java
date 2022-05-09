package app;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.entities.*;
import model.dao.*;
import model.services.*;

public class Cadastro {
    private static String opcaoS;

    public static void unidade() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Cadastro Unidade");
        System.out.println("\u001B[32m" + "       1 - Cadastrar" + "\u001B[0m");
        System.out.println("\u001B[32m" + "       2 - Listar" + "\u001B[0m");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = UI.sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case 1:
                    cadastrarUnidade(1);
                    voltarOuEncerrar(1);
                    break;
                case 2:
                    System.out.println();
                    System.out.println();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
                    System.out.println("Listar Unidades");
                    System.out.print("Digite as iniciais das Unidades (EX: UBS): ");
                    String filtro = UI.sc.nextLine();
                    if (filtro.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        return;
                    } else if (filtro.equals("-"))
                        unidade();
                    UnidadeSDao.listarPrint(filtro);
                    voltarOuEncerrar(1);
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
    }

    public static void cadastrarUnidade(int i) throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Cadastro Unidade");
        System.out.print("Digite o nome da Unidade: ");
        String nome = UI.sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return;
        } else if (nome.equals("-")) {
            if (i == 1) {
                unidade();
            } else if (i == 2) {
                UI.menuRotina();
            }
        }

        System.out.print("É um Centro de Distribuição? (s/n): ");
        Character centro = UI.sc.next().charAt(0);
        UI.sc.nextLine();
        if (centro == '0') {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
        if(unidade.getCentro() == true && UnidadeSDao.listarCd() != null){
            System.out.println("Centro de Distribuição já Cadastrado!");
            return;
        }
        if (i == 1) {
            unidade.setEndereco(InstanciarEndereco.criarEndereco(1));
        } else if (i == 2) {
            unidade.setEndereco(InstanciarEndereco.criarEndereco(2));
        }

        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        
        int retorno = UnidadeSDao.cadastrar(unidade);
        if (retorno != 0) {
            endDao.inserir(unidade.getEndereco());
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

    }

    public static void pessoa() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Cadastro Populacao");
        System.out.println("\u001B[32m" + "       1 - Cadastrar" + "\u001B[0m");
        System.out.println("\u001B[32m" + "       2 - Listar" + "\u001B[0m");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");

        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = UI.sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case 1:
                    cadastrarPessoa(0);
                    voltarOuEncerrar(2);
                    break;
                case 2:
                    System.out.println();
                    System.out.println();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
                    System.out.println("Listar Populacao");
                    System.out.print("Digite as Iniciais do Nome: ");
                    String filtro = UI.sc.nextLine();
                    if (filtro.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        return;
                    } else if (filtro.equals("-"))
                        pessoa();
                    PessoaSDao.listarPrint(filtro);
                    voltarOuEncerrar(2);
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
    }

    public static void cadastrarPessoa(int i) throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Cadastro Populacao");
        System.out.print("Digite seu nome: ");
        String nome = UI.sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return;
        } else if (nome.equals("-"))
            if (i == 0) {
                pessoa();
            } else if (i == 1) {
                UI.menuRotina();
            }
        System.out.print("Digite seu CPF: ");
        String cpf = UI.sc.nextLine();
        if (cpf.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return;
        } else if (cpf.equals("-")) {
            if (i == 1) {
                pessoa();
            } else if (i == 2) {
                UI.menuRotina();
            }
        }
        Pessoa pessoa = new Pessoa(nome, cpf);
        if (i == 0) {
            pessoa.setEndereco(InstanciarEndereco.criarEndereco(0));
        } else if (i == 1) {
            pessoa.setEndereco(InstanciarEndereco.criarEndereco(1));
        }
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        
        int retorno = PessoaSDao.cadastrar(pessoa);

        if (retorno != 0) {
            endDao.inserir(pessoa.getEndereco());
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
    }

    public static void lote() throws InterruptedException, IOException, ParseException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.println("Cadastro Lote Vacina");
        System.out.println("\u001B[32m" + "       1 - Cadastrar" + "\u001B[0m");
        System.out.println("\u001B[32m" + "       2 - Listar" + "\u001B[0m");
        System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
        System.out.print("Digite uma Opção do Menu para Navegar: ");
        int opcao = 0;
        opcaoS = "";
        try {
            opcaoS = UI.sc.nextLine();
            opcao = Integer.parseInt(opcaoS);
            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case 1:
                    int cadastro = cadastroLote();
                    if (cadastro != 0)
                        voltarOuEncerrar(3);
                    break;
                case 2:
                    System.out.println();
                    System.out.println();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.out.println("Listar Lote Vacina");
                    System.out.print("Digite as Iniciais do Nome da Vacina: ");
                    String filtro = UI.sc.nextLine();
                    if (filtro.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        return;
                    } else if (filtro.equals("-"))
                        lote();
                    LoteSDao.listarPrint(filtro);
                    voltarOuEncerrar(3);
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
    }

    public static int cadastroLote() throws InterruptedException, IOException, ParseException {
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
                idS = UI.sc.nextLine();
                id = Integer.parseInt(idS);
                if (id == 0) {
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
                    cadastroLote();
                }
            }
        } while (contemId == true || id <= 0);

        System.out.print("Digite o nome da Vacina: ");
        String nome = UI.sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return 0;
        } else if (nome.equals("-"))
            lote();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataVencimento = "";
        Boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Digite a Data de Validade da Vacina (Formato DD/MM/AAAA): ");
            dataVencimento = UI.sc.nextLine();
            if (dataVencimento.equals("0")) {
                System.out.println("Obrigado por usar nosso sistema!");
                UI.sleep(2.5);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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

        int retorno = LoteSDao.cadastrar(lote);
        if (retorno != 0) {
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
        }
        return 1;
    }

    public static void voltarOuEncerrar(int opcao) throws InterruptedException, IOException {
        String comando = "";
        while (!comando.equals("-") || !comando.equals("0")) {
            System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
            System.out.print("Digite uma Opção: ");

            comando = UI.sc.nextLine();

            if (comando.equals("0")) {
                System.out.println("Obrigado por usar nosso sistema!");
                UI.sleep(2.5);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
    }
}
