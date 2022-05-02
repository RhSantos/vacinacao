package app;

import java.util.List;
import model.dao.*;
import model.entities.*;

public class Program {
    public static void main(String[] args) {
        UnidadeDao uniDao = DaoFactory.createUnidadeDao();
        PessoaDao pesDao = DaoFactory.createPessoaDao();
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        Endereco endereco = endDao.procurarPorId(3);
        Endereco endereco1 = endDao.procurarPorId(2);

        pesDao.deletar(2);

        Unidade unidade = new Unidade("CD",true,endereco);
        uniDao.inserir(unidade);
        
    }
}
