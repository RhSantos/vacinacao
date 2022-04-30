package app;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.entities.Endereco;

public class Program {
    public static void main(String[] args) {
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        Endereco endereco = endDao.procurarPorId(1);
        List<Endereco> enderecos = endDao.listar();
        System.out.println(endereco);
        System.out.println(enderecos);
        Endereco end = new Endereco
            (3,"Rua Luiz Bastos do Prado","Gravataí", 
            "RS",482,"Centro", "94010-014");
        endDao.atualizar(end);
    }
}
