package app;

import model.dao.DaoFactory;
import model.dao.EnderecoDao;

public class Program {
    public static void main(String[] args) {
        EnderecoDao endDao = DaoFactory.createEnderecoDao();
        
    }
}
