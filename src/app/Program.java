package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.dao.*;
import model.entities.*;
import model.enums.TipoMovimento;
import model.enums.TipoTransacao;

public class Program {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        LoteDao loteDao = DaoFactory.createLoteDao();
        Lote lote = new Lote(1,"PFIZER", sdf.parse("15/07/2022"));
        // loteDao.inserir(lote);

        // System.out.println(loteDao.procurarPorNome("CoronaVAC"));

        UnidadeDao uniDao = DaoFactory.createUnidadeDao();
        PessoaDao pesDao = DaoFactory.createPessoaDao();
        Pessoa pessoa = pesDao.procurarPorId(1);

        Endereco endereco = new Endereco
            (1, "Avenida Centenário","Gravataí", "RS", 
                3455, "Centro","94010-021");
        Endereco endereco1 = new Endereco
            (2, "Rua Anápio Gomes","Gravataí","RS", 
                212,"Centro","94010-353");
        // endDao.inserir(endereco);
        // endDao.inserir(endereco1);

        Unidade unidade1 = new Unidade(1,"CD",true,endereco);
        Unidade unidade2 = new Unidade(2,"UBS Barnabé",false,endereco1);
        EstoqueDao estDao = DaoFactory.createEstoqueDao();
        Estoque estoque = new Estoque(uniDao.procurarPorId(2),loteDao.procurarPorId(1), 0);
        // System.out.println(estDao.listar());

        // System.out.println(estDao.procurarPorLote(lote));
        // System.out.println(estDao.procurarPorIdUnidadeLote(1,1));
        // estDao.inserir(estoque);
        TipoMovimento tipo = TipoMovimento.Entrada;
        System.out.println(tipo.name());

        MovimentoDao movDao = DaoFactory.createMovimentoDao();
        Estoque est = estDao.procurarPorIdUnidadeLote(unidade1.getId(), lote.getLote());
        Estoque est2 = estDao.procurarPorIdUnidadeLote(unidade2.getId(), lote.getLote());
        Movimento movimento = new Movimento
            (unidade1, lote,est.getQuantidade(), 
                TipoMovimento.Entrada, 
                TipoTransacao.rec,new Date(),unidade2);
        
        // est2.setQuantidade(est2.getQuantidade() + est.getQuantidade());
        // est.setQuantidade(0);
        // estDao.atualizar(est2);
        // estDao.atualizar(est);
        // movDao.inserir(movimento);
        movimento.setDataMovimento(new Date());
        movimento.setId(1);
        movDao.atualizar(movimento);
        // estoque.setQuantidade(140);
        // estDao.atualizar(estoque);

        // estoque.setUnidade(uniDao.procurarPorId(1));
        // estoque.setQuantidade(0);
        // estDao.atualizar(estoque);

        // unidade1.setEndereco(endereco1);
        // uniDao.atualizar(unidade1);
        // uniDao.deletar(2);
        // uniDao.inserir(unidade1);
        // uniDao.inserir(unidade2);
        // System.out.println(uniDao.listaCD());
        // System.out.println();
        // System.out.println(uniDao.listar());
    }
}
