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
        // endDao.inserir(endereco);
        Lote lote = new Lote(2,"PFIZER", sdf.parse("15/07/2022"));
        

        System.out.println(loteDao.procurarPorNome("CoronaVAC"));

        // UnidadeDao uniDao = DaoFactory.createUnidadeDao();
        // PessoaDao pesDao = DaoFactory.createPessoaDao();
        

        // Endereco endereco = endDao.procurarPorId(3);
        // Endereco endereco1 = endDao.procurarPorId(4);

        // Unidade unidade1 = new Unidade(1,"CD",true,endereco);
        // Unidade unidade2 = new Unidade(2,"UBS Barnabé",false,endereco);
        // unidade1.setEndereco(endereco1);
        // uniDao.atualizar(unidade1);
        // uniDao.deletar(2);
        // uniDao.inserir(unidade2);
        // System.out.println(uniDao.listaCD());
        // System.out.println();
        // System.out.println(uniDao.listar());
    }
}
