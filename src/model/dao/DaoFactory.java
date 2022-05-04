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

    public static LoteDao createLoteDao(){
        return new LoteDaoJDBC(DB.getConnection());
    }

    public static EstoqueDao createEstoqueDao(){
        return new EstoqueDaoJDBC(DB.getConnection());
    }

    public static MovimentoDao createMovimentoDao(){
        return new MovimentoDaoJDBC(DB.getConnection());
    }

    public static VacinadoDao createVacinadoDao(){
        return new VacinadoDaoJDBC(DB.getConnection());
    }

}
