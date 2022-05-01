package app;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.dao.PessoaDao;
import model.entities.Endereco;
import model.entities.Pessoa;

public class Program {
    public static void main(String[] args) {
        PessoaDao pesDao = DaoFactory.createPessoaDao();
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        Endereco endereco = endDao.procurarPorId(3);
        Endereco endereco1 = endDao.procurarPorId(2);

        // Pessoa pessoa = new Pessoa
        //     (2,"Julia Cristina","232.532.123-87",endereco1);
        // pesDao.inserir(pessoa); 
        // pesDao.atualizar(pessoa);

        Pessoa pessoa1 = pesDao.procurarPorId(1);
        // System.out.println(pessoa1);

        Pessoa pessoa2 = pesDao.procurarPorEndereco(endereco1);
        List<Pessoa> pessoas = pesDao.listar();
        // System.out.println(pessoa2);
        System.out.println(pessoas);

        // List<Endereco> enderecos = endDao.listar();
        // System.out.println(endereco);
        // System.out.println(enderecos);
        // Endereco end = new Endereco
        //     (3,"Rua Luiz Bastos do Prado","Gravataí", 
        //     "RS",482,"Centro", "94010-014");
        // endDao.atualizar(end);
    }
}
