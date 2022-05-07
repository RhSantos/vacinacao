package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MovimentoDao;
import model.entities.Movimento;

public class MovimentoSDao {

    public static void cadastrar(Movimento movimento){
        MovimentoDao movDao = DaoFactory.createMovimentoDao();
        if(movimento == null) {
            System.out.println("O Movimento não pode ser Nulo!");
            return;
        }
        movDao.inserir(movimento);
    }

    public static List<Movimento> procurarPorUnidadeLote(Integer unidade, Integer lote){
        MovimentoDao movDao = DaoFactory.createMovimentoDao();
        return movDao.procurarPorUnidadeLote(unidade, lote);
    }
}
