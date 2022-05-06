package model.services;

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
}
