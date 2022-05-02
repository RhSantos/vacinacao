package model.dao;

import db.DB;
import model.dao.impl.*;

public class DaoFactory {
    public static EnderecoDao createEnderecoDao(){
        return new EnderecoDaoJDBC(DB.getConnection());
    }

    public static PessoaDao createPessoaDao(){
        return new PessoaDaoJDBC(DB.getConnection());
    }

    public static UnidadeDao createUnidadeDao(){
        return new UnidadeDaoJDBC(DB.getConnection());
    }

}
