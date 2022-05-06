package app;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.EstoqueDao;
import model.dao.LoteDao;
import model.dao.MovimentoDao;
import model.dao.UnidadeDao;
import model.entities.Estoque;
import model.entities.Lote;
import model.entities.Movimento;
import model.entities.Unidade;
import model.enums.TipoMovimento;
import model.enums.TipoTransacao;
import model.services.EstoqueSDao;
import model.services.LoteSDao;
import model.services.MovimentoSDao;
import model.services.UnidadeSDao;

public class Rotina {
    public static void recebimento() throws InterruptedException, IOException, ParseException {
        try {
            System.out.println();
            System.out.println();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
            System.out.println("Recebimento de Vacinas");

            try (Scanner sc = new Scanner(System.in)) {
                Integer id = 0;
                String idS = "";
                LoteDao loteDao = DaoFactory.createLoteDao();
                do {
                    try {
                        System.out.print("Digite o ID do Lote para Rotina de Recebimento de Vacinas: ");
                        idS = sc.nextLine();
                        id = Integer.parseInt(idS);
                        if (idS.equals("0")) {
                            System.out.println("Obrigado por usar nosso sistema!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        if (idS.equals("-"))
                            UI.menuRotina();
                        else {
                            System.out.println("A Opção deve ser apenas Números inteiros!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        }
                    }
                } while (id <= 0);
                UnidadeDao uniDao = DaoFactory.createUnidadeDao();
                if (loteDao.procurarPorId(id) == null) {
                    Cadastro.cadastroLote(sc);
                }
                Lote lote = loteDao.procurarPorId(id);
                if (uniDao.listaCD() == null) {
                    System.out.println("Centro de distribuição não encontrado no Sistema!");
                    System.out.print("Indo para Cadastro de Unidade");
                    UI.sleep(1.0);
                    System.out.print(".");
                    UI.sleep(1.0);
                    System.out.print(".");
                    UI.sleep(1.0);
                    System.out.println(".");
                    UI.sleep(1.0);
                    Cadastro.cadastrarUnidade(sc, 2);
                }
                String quantS = "";
                Integer quant = 0;
                do {
                    try {
                        System.out.print("Quantidade de Vacinas para Recebimento: ");
                        quantS = sc.nextLine();
                        quant = Integer.parseInt(quantS);
                        if (quantS.equals("0")) {
                            System.out.println("Obrigado por usar nosso sistema!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        if (quantS.equals("-"))
                            UI.menuRotina();
                        else {
                            System.out.println("A Quantidade não pode ser Negativa!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        }
                    }
                } while (quant < 0);
                Unidade cd = uniDao.listaCD();
                Movimento movimento = new Movimento(id, cd, lote, quant,
                        TipoMovimento.Entrada, TipoTransacao.rec, new Date(), null);
                Estoque estoque = null;
                if (EstoqueSDao.procurarPorUnidadeLote(cd, lote) == null) {
                    estoque = new Estoque(cd.getId(), lote.getLote(), quant);
                    EstoqueSDao.cadastrar(estoque);
                } else {
                    estoque = EstoqueSDao.procurarPorUnidadeLote(cd, lote);
                    estoque.setQuantidade(quant += estoque.getQuantidade());
                    EstoqueSDao.atualizar(estoque);
                }
                MovimentoSDao.cadastrar(movimento);
                System.out.println(lote);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Data de Recebimento: " + sdf.format(movimento.getDataMovimento()));
                System.out.println("Quantidade de Vacinas: " + estoque.getQuantidade());
                System.out.println();
                voltarOuEncerrar(sc);
            }

        } catch (NoSuchElementException e) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }

    public static void transferencia() throws InterruptedException, IOException {
        try {
            System.out.println();
            System.out.println();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
            System.out.println("Rotina de Transferencia");

            try (Scanner sc = new Scanner(System.in)) {
                Integer id = 0;
                String idS = "";
                Unidade unidade = null;
                Lote lote = null;
                do {
                    try {
                        System.out.print("Digite o ID da Unidade para Rotina de Transferencia de Vacinas: ");
                        idS = sc.nextLine();
                        id = Integer.parseInt(idS);
                        if (idS.equals("0")) {
                            System.out.println("Obrigado por usar nosso sistema!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            return;
                        }
                        unidade = UnidadeSDao.procurarPorId(id);
                        if (unidade == null) {
                            System.out.println("Unidade Inexistente - Tente Novamente");
                            System.out.println();
                            id = 0;
                            idS = "";
                        }
                    } catch (NumberFormatException e) {
                        if (idS.equals("-"))
                            UI.menuRotina();
                        else {
                            System.out.println("A Opção deve ser apenas Números inteiros!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        }
                    }
                } while (id <= 0);
                id = 0;
                idS = "";
                do {
                    try {
                        System.out.print("Digite o ID do Lote para Rotina de Transferencia de Vacinas: ");
                        idS = sc.nextLine();
                        id = Integer.parseInt(idS);
                        if (idS.equals("0")) {
                            System.out.println("Obrigado por usar nosso sistema!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            return;
                        }
                        lote = LoteSDao.procurarPorId(id);
                        if (lote == null) {
                            System.out.println("Lote Inexistente - Tente Novamente");
                            System.out.println();
                            id = 0;
                            idS = "";
                        }
                    } catch (NumberFormatException e) {
                        if (idS.equals("-"))
                            UI.menuRotina();
                        else {
                            System.out.println("A Opção deve ser apenas Números inteiros!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        }
                    }
                } while (id <= 0);

                Unidade cd = UnidadeSDao.listarCd();

                if (cd == null) {
                    System.out.println("Centro de Distribuição Não Existente!");
                    voltarOuEncerrar(sc);
                }

                Estoque estoqueCd = EstoqueSDao.procurarPorUnidadeLote(cd, lote);

                if (estoqueCd == null) {
                    System.out.println("O Centro de Distribuição não tem o Estoque para esse Lote!");
                    voltarOuEncerrar(sc);
                }

                Integer quantEstoqueCd = estoqueCd.getQuantidade();

                Estoque estoque = EstoqueSDao.procurarPorUnidadeLote(unidade, lote);

                if (estoque == null) {
                    Estoque estoqueNovo = new Estoque(unidade.getId(), lote.getLote(), 0);
                    estoque = estoqueNovo;
                    EstoqueSDao.cadastrar(estoqueNovo);
                }

                estoqueCd.setQuantidade(0);
                EstoqueSDao.atualizar(estoqueCd);
                Movimento movimento1 = new Movimento(cd, lote, quantEstoqueCd, TipoMovimento.Saida, TipoTransacao.tra,
                        new Date(), unidade);
                MovimentoSDao.cadastrar(movimento1);
                estoque.setQuantidade(quantEstoqueCd += estoque.getQuantidade());
                EstoqueSDao.atualizar(estoque);
                Movimento movimento2 = new Movimento(unidade, lote, quantEstoqueCd, TipoMovimento.Entrada,
                        TipoTransacao.tra,
                        new Date(), cd);
                MovimentoSDao.cadastrar(movimento2);
                System.out.println("Transferencia Concluida com Sucesso!");
                voltarOuEncerrar(sc);
            }
        } catch (NoSuchElementException e) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }

    public static void aplicacao() throws InterruptedException, IOException {
        try {
            System.out.println();
            System.out.println();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
            System.out.println("Rotina de Aplicacao");

            try (Scanner sc = new Scanner(System.in)) {
                Integer id = 0;
                String idS = "";
                Unidade unidade = null;
                Lote lote = null;
                do {
                    try {
                        System.out.print("Digite o CPF da Pessoa para Rotina de Aplicacao de Vacinas: ");
                        idS = sc.nextLine();
                        id = Integer.parseInt(idS);
                        if (idS.equals("0")) {
                            System.out.println("Obrigado por usar nosso sistema!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            return;
                        }
                        unidade = UnidadeSDao.procurarPorId(id);
                        if (unidade == null) {
                            System.out.println("Unidade Inexistente - Tente Novamente");
                            System.out.println();
                            id = 0;
                            idS = "";
                        }
                    } catch (NumberFormatException e) {
                        if (idS.equals("-"))
                            UI.menuRotina();
                        else {
                            System.out.println("A Opção deve ser apenas Números inteiros!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        }
                    }
                } while (id <= 0);
                id = 0;
                idS = "";
                do {
                    try {
                        System.out.print("Digite o ID do Lote para Rotina de Transferencia de Vacinas: ");
                        idS = sc.nextLine();
                        id = Integer.parseInt(idS);
                        if (idS.equals("0")) {
                            System.out.println("Obrigado por usar nosso sistema!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            return;
                        }
                        lote = LoteSDao.procurarPorId(id);
                        if (lote == null) {
                            System.out.println("Lote Inexistente - Tente Novamente");
                            System.out.println();
                            id = 0;
                            idS = "";
                        }
                    } catch (NumberFormatException e) {
                        if (idS.equals("-"))
                            UI.menuRotina();
                        else {
                            System.out.println("A Opção deve ser apenas Números inteiros!");
                            UI.sleep(2.5);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        }
                    }
                } while (id <= 0);

                Unidade cd = UnidadeSDao.listarCd();

                if (cd == null) {
                    System.out.println("Centro de Distribuição Não Existente!");
                    voltarOuEncerrar(sc);
                }

                Estoque estoqueCd = EstoqueSDao.procurarPorUnidadeLote(cd, lote);

                if (estoqueCd == null) {
                    System.out.println("O Centro de Distribuição não tem o Estoque para esse Lote!");
                    voltarOuEncerrar(sc);
                }

                Integer quantEstoqueCd = estoqueCd.getQuantidade();

                Estoque estoque = EstoqueSDao.procurarPorUnidadeLote(unidade, lote);

                if (estoque == null) {
                    Estoque estoqueNovo = new Estoque(unidade.getId(), lote.getLote(), 0);
                    estoque = estoqueNovo;
                    EstoqueSDao.cadastrar(estoqueNovo);
                }

                estoqueCd.setQuantidade(0);
                EstoqueSDao.atualizar(estoqueCd);
                Movimento movimento1 = new Movimento(cd, lote, quantEstoqueCd, TipoMovimento.Saida, TipoTransacao.tra,
                        new Date(), unidade);
                MovimentoSDao.cadastrar(movimento1);
                estoque.setQuantidade(quantEstoqueCd += estoque.getQuantidade());
                EstoqueSDao.atualizar(estoque);
                Movimento movimento2 = new Movimento(unidade, lote, quantEstoqueCd, TipoMovimento.Entrada,
                        TipoTransacao.tra,
                        new Date(), cd);
                MovimentoSDao.cadastrar(movimento2);
                System.out.println("Transferencia Concluida com Sucesso!");
                voltarOuEncerrar(sc);
            }
        } catch (NoSuchElementException e) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
                UI.menuRotina();
            else {
                System.out.println("Digite uma Opção Válida!");
            }
        }
    }
}
