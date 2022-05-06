package app;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UnidadeDao;
import model.entities.Unidade;
import model.services.EstoqueSDao;
import model.services.VacinadoSDao;

public class Relatorios {
    public static void estoqueVacina() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        try (Scanner sc = new Scanner(System.in)) {
            Integer id = 0;
            String idS = "";
            Boolean contemId = false;
            UnidadeDao uniDao = DaoFactory.createUnidadeDao();

            do {
                try {
                    System.out.print("Digite o ID da Unidade para Listar os Dados de Estoque Disponível: ");
                    idS = sc.nextLine();
                    id = Integer.parseInt(idS);

                    if (idS.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        return;
                    }
                    List<Unidade> unidades = uniDao.listar();
                    for (Unidade unidade : unidades) {
                        if (unidade.getId() == id) {
                            contemId = true;
                        }
                    }
                    if (contemId == false) {
                        System.out.println("ID de Unidade Inexistente - Tente Novamente!");
                        System.out.println();
                    }
                } catch (NumberFormatException e) {
                    if (idS.equals("-"))
                        UI.menuRelatorios();
                    else {
                        System.out.println("A Opção deve ser apenas Números inteiros!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                }
            } while (id <= 0 || contemId == false);
            EstoqueSDao.listarPrint(id.toString());
            voltarOuEncerrar(sc);
        }

    }

    public static void aplicacao() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        try (Scanner sc = new Scanner(System.in)) {
            Integer id = 0;
            String idS = "";
            Boolean contemId = false;
            UnidadeDao uniDao = DaoFactory.createUnidadeDao();
            do {
                try {
                    System.out.print("Digite o ID da Unidade para Listar as Aplicações: ");
                    idS = sc.nextLine();
                    id = Integer.parseInt(idS);
                    if (idS.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        return;
                    }
                    List<Unidade> unidades = uniDao.listar();
                    for (Unidade unidade : unidades) {
                        if (unidade.getId() == id) {
                            contemId = true;
                        }
                    }
                    if (contemId == false) {
                        System.out.println("ID de Unidade Inexistente - Tente Novamente!");
                        System.out.println();
                    }
                } catch (NumberFormatException e) {
                    if (idS.equals("-"))
                        UI.menuRelatorios();
                    else {
                        System.out.println("A Opção deve ser apenas Números inteiros!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                }
            } while (id <= 0 || contemId == false);
            VacinadoSDao.listarPrint(id.toString());
            voltarOuEncerrar(sc);
        }
    }

    public static void resumoAplicacao() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        try (Scanner sc = new Scanner(System.in)) {
            Integer id = 0;
            String idS = "";
            Boolean contemId = false;
            UnidadeDao uniDao = DaoFactory.createUnidadeDao();
            do {
                try {
                    System.out.print("Digite o ID da Unidade para Listar o Resumo das Aplicações: ");
                    idS = sc.nextLine();
                    id = Integer.parseInt(idS);
                    if (idS.equals("0")) {
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        return;
                    }
                    List<Unidade> unidades = uniDao.listar();
                    for (Unidade unidade : unidades) {
                        if (unidade.getId() == id) {
                            contemId = true;
                        }
                    }
                    if (contemId == false) {
                        System.out.println("ID de Unidade Inexistente - Tente Novamente!");
                    }
                } catch (NumberFormatException e) {
                    if (idS.equals("-"))
                        UI.menuRelatorios();
                    else {
                        System.out.println("A Opção deve ser apenas Números inteiros!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                }
            } while (id <= 0 || contemId == false);
            VacinadoSDao.listarPrintContagem(id.toString());
            voltarOuEncerrar(sc);
        }

    }

    public static void esquemaVacinalImcompleto() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        try (Scanner sc = new Scanner(System.in)) {
            String filtro = "";
            System.out.print("Digite as Iniciais do Bairro para Filtrar: ");
            filtro = sc.nextLine();
            if (filtro.equals("0")) {
                System.out.println("Obrigado por usar nosso sistema!");
                UI.sleep(2.5);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                return;
            }
            else if (filtro.equals("-")) UI.menuRelatorios();
            VacinadoSDao.listarVacinalImcompletoPrint(filtro);
            voltarOuEncerrar(sc);
        }
    }

    public static void voltarOuEncerrar(Scanner sc) throws InterruptedException, IOException {
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
                UI.menuRelatorios();
            else {
                System.out.println("Digite uma Opção Válida!");
            }
        }
    }
}
