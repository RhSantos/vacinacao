package app;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.LoteDao;
import model.dao.UnidadeDao;
import model.entities.Estoque;
import model.entities.Lote;
import model.entities.Movimento;
import model.entities.Pessoa;
import model.entities.Unidade;
import model.entities.Vacinado;
import model.enums.TipoMovimento;
import model.enums.TipoTransacao;
import model.services.EstoqueSDao;
import model.services.LoteSDao;
import model.services.MovimentoSDao;
import model.services.PessoaSDao;
import model.services.UnidadeSDao;
import model.services.VacinadoSDao;

public class Rotina {
    public static void recebimento() throws InterruptedException, IOException, ParseException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Recebimento de Vacinas");

        Integer id = 0;
        String idS = "";
        LoteDao loteDao = DaoFactory.createLoteDao();
        do {
            try {
                System.out.print("Digite o ID do Lote para Rotina de Recebimento de Vacinas: ");
                idS = UI.sc.nextLine();
                id = Integer.parseInt(idS);
                if (idS.equals("0")) {
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
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
        Lote lote = loteDao.procurarPorId(id);
        if (lote == null) {
            Cadastro.cadastroLote(id);
        }
        lote = loteDao.procurarPorId(id);

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
            Cadastro.cadastrarUnidade(2);
        }

        Unidade cd = uniDao.listaCD();
        Movimento movimento = new Movimento(id, cd, lote, 150,
                TipoMovimento.Entrada, TipoTransacao.rec, new Date(), null);
        Estoque estoque = null;
        if (EstoqueSDao.procurarPorUnidadeLote(cd, lote) == null) {
            estoque = new Estoque(cd.getId(), lote.getLote(), 150);
            EstoqueSDao.cadastrar(estoque);
        } else {
            estoque = EstoqueSDao.procurarPorUnidadeLote(cd, lote);
            estoque.setQuantidade(150 + estoque.getQuantidade());
            EstoqueSDao.atualizar(estoque);
        }
        MovimentoSDao.cadastrar(movimento);
        System.out.println(lote);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Data de Recebimento: " + sdf.format(movimento.getDataMovimento()));
        System.out.println("Quantidade de Vacinas: " + estoque.getQuantidade());
        System.out.println();
        voltarOuEncerrar();

    }

    public static void transferencia() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Rotina de Transferencia");

        Integer id = 0;
        String idS = "";
        Unidade unidade = null;
        Lote lote = null;
        do {
            try {
                System.out.print("Digite o ID da Unidade para Rotina de Transferencia de Vacinas: ");
                idS = UI.sc.nextLine();
                id = Integer.parseInt(idS);
                if (idS.equals("0")) {
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
                }
                unidade = UnidadeSDao.procurarPorId(id);
                if (unidade == null) {
                    System.out.println("Unidade Inexistente - Tente Novamente");
                    System.out.println();
                    voltarOuEncerrar();
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
                idS = UI.sc.nextLine();
                id = Integer.parseInt(idS);
                if (idS.equals("0")) {
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
                }
                lote = LoteSDao.procurarPorId(id);
                if (lote == null) {
                    System.out.println("Lote Inexistente - Tente Novamente");
                    System.out.println();
                    voltarOuEncerrar();
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
            System.out.println();
            voltarOuEncerrar();
        }

        if (unidade.equals(cd)) {
            List<Estoque> estoques = EstoqueSDao.procurarPorLote(lote);
            for (Estoque estoque : estoques) {
                if (!estoque.getUnidade().equals(unidade)) {
                    cd = estoque.getUnidade();
                }
            }
            if (cd.equals(unidade)) {
                System.out.println("Transferencia para o mesmo lugar é inválida!");
                System.out.println();
                voltarOuEncerrar();
            }

            List<Movimento> movTeste = MovimentoSDao.procurarPorUnidadeLote(cd.getId(), lote.getLote());

            if (!movTeste.isEmpty()) {
                for (Movimento movimento : movTeste) {
                    if (movimento.getTipoTransacao() == TipoTransacao.apl) {
                        System.out.println("Você não pode transferir o Lote porque o mesmo já foi aberto!");
                        System.out.println();
                        voltarOuEncerrar();
                    }
                }
            }
        } else {
            List<Movimento> movTeste = MovimentoSDao.procurarPorUnidadeLote(unidade.getId(), lote.getLote());

            if (!movTeste.isEmpty()) {
                for (Movimento movimento : movTeste) {
                    if (movimento.getTipoTransacao() == TipoTransacao.apl) {
                        System.out.println("Você não pode transferir o Lote porque o mesmo já foi aberto!");
                        System.out.println();
                        voltarOuEncerrar();
                    }
                }
            }
        }

        Estoque estoqueCd = EstoqueSDao.procurarPorUnidadeLote(cd, lote);

        if (estoqueCd == null) {
            System.out.println("O Centro de Distribuição não tem o Estoque para esse Lote!");
            System.out.println();
            voltarOuEncerrar();
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Transferencia Concluida com Sucesso!");
        UI.sleep(1.0);
        System.out.print("Imprimindo Informações");
        UI.sleep(1.0);
        System.out.print(".");
        UI.sleep(1.0);
        System.out.print(".");
        UI.sleep(1.0);
        System.out.println(".");
        UI.sleep(1.0);
        System.out.println();
        System.out.println("Transferencia de: " + movimento1.getUnidade().getNome());
        System.out.println("Para: " + movimento1.getUnidadeTransfer().getNome());
        System.out.println(estoque.getLote());
        System.out.println("Data de Transferencia: " + sdf.format(movimento1.getDataMovimento()));
        System.out.println("Quantidade de Vacinas: " + estoque.getQuantidade());
        System.out.println();
        voltarOuEncerrar();

    }

    public static void aplicacao() throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("=== SISTEMA PARA CONTROLE DE VACINAÇÃO DE SÃO LEOPOLDO ===\n");
        System.out.println("Rotina de Aplicacao");

        Integer id = 0;
        String idS = "";
        Pessoa pessoa = null;
        Lote lote = null;
        System.out.print("Digite o CPF da Pessoa para Rotina de Aplicacao de Vacinas: ");
        idS = UI.sc.nextLine();
        if (idS.equals("0")) {
            System.out.println("Obrigado por usar nosso sistema!");
            UI.sleep(2.5);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.exit(0);
        } else if (idS.equals("-"))
            UI.menuRotina();

        pessoa = PessoaSDao.procurarPorCpf(idS);

        if (pessoa == null) {
            Cadastro.cadastrarPessoa(1,idS);
            pessoa = PessoaSDao.procurarPorCpf(idS);
        }
        idS = "";
        do {
            try {
                System.out.print("Digite o ID do Lote para Rotina de Aplicacao de Vacinas: ");
                idS = UI.sc.nextLine();
                id = Integer.parseInt(idS);
                if (idS.equals("0")) {
                    System.out.println("Obrigado por usar nosso sistema!");
                    UI.sleep(2.5);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
                }
                lote = LoteSDao.procurarPorId(id);
                if (lote == null) {
                    System.out.println("Lote Inexistente - Tente Novamente");
                    System.out.println();
                    voltarOuEncerrar();
                }
                if (lote.getDataVencimento().getTime() < new Date().getTime()) {
                    System.out.println("Lote Vencido! A Aplicação não será realizada!");
                    System.out.println();
                    voltarOuEncerrar();
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

        List<Estoque> estoques = EstoqueSDao.procurarPorLote(lote);
        if (estoques.isEmpty()) {
            System.out.println("Estoque da Vacina Inexistente! A Aplicação não poderá ser feita!");
            System.out.println();
            voltarOuEncerrar();
        }

        Unidade cd = UnidadeSDao.listarCd();

        if (cd == null) {
            System.out.println("Centro de Distribuição Não Existente!");
            voltarOuEncerrar();
        }

        Estoque estq = null;

        for (Estoque estoque : estoques) {
            if (!estoque.getUnidade().equals(cd)) {
                if (estoque.getQuantidade() == 0) {
                    System.out.println("Estoque da Vacina Vazio! A Aplicação não poderá ser feita!");
                    System.out.println();
                    voltarOuEncerrar();
                } else {
                    estq = estoque;
                    break;
                }

            }
        }

        List<Vacinado> cartelaVacina = VacinadoSDao.procurarPorPessoa(pessoa.getId());
        Integer maiorDose = 0;

        if (!cartelaVacina.isEmpty()) {
            for (Vacinado vacinado : cartelaVacina) {
                if (vacinado.getDose() == 3) {
                    System.out.println("Pessoa com Esquema de Vacinação Completo!");
                    System.out.println();
                    voltarOuEncerrar();
                }
                if (vacinado.getDose() > maiorDose)
                    maiorDose = vacinado.getDose();
            }
        }

        estq.setQuantidade(estq.getQuantidade() - 1);
        EstoqueSDao.atualizar(estq);
        Movimento movimento = new Movimento(estq.getUnidade(), lote, pessoa, 1, TipoMovimento.Saida,
                TipoTransacao.apl,
                new Date());
        MovimentoSDao.cadastrar(movimento);

        Vacinado vacinado = new Vacinado(maiorDose + 1, pessoa, estq.getUnidade(), lote, movimento, new Date());
        VacinadoSDao.cadastrar(vacinado);
        System.out.println("Aplicacao feita com Sucesso!");
        UI.sleep(1.0);
        System.out.print("Imprimindo Resultados");
        UI.sleep(1.0);
        System.out.print(".");
        UI.sleep(1.0);
        System.out.print(".");
        UI.sleep(1.0);
        System.out.println(".");
        UI.sleep(1.0);
        System.out.println();
        System.out.println("Nome: " + movimento.getPessoa().getNome());
        System.out.println("CPF: " + movimento.getPessoa().getCpf());
        System.out.println("Dose: " + vacinado.getDose());
        System.out.println(movimento.getLote());
        System.out.println();
        voltarOuEncerrar();

    }

    public static void voltarOuEncerrar() throws InterruptedException, IOException {
        String comando = "";
        while (!comando.equals("-") || !comando.equals("0")) {
            System.out.println("Em qualquer tela, digite '-' para voltar ou '0' para sair!");
            System.out.print("Digite uma Opção: ");

            comando = UI.sc.nextLine();

            if (comando.equals("0")) {
                System.out.println("Obrigado por usar nosso sistema!");
                UI.sleep(2.5);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                System.exit(0);
            } else if (comando.equals("-"))
                UI.menuRotina();
            else {
                System.out.println("Digite uma Opção Válida!");
            }
        }

    }
}
