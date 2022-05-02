package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.dao.*;
import model.entities.*;

public class Program {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        LoteDao loteDao = DaoFactory.createLoteDao();
        Lote lote = new Lote(1,"PFIZER", sdf.parse("15/07/2022"));
        // loteDao.inserir(lote);

        // System.out.println(loteDao.procurarPorNome("CoronaVAC"));

        UnidadeDao uniDao = DaoFactory.createUnidadeDao();
        // PessoaDao pesDao = DaoFactory.createPessoaDao();
        

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
        System.out.println(estDao.procurarPorUnidade(unidade1));
        // estDao.inserir(estoque);

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
