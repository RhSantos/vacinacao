package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EstoqueDao;
import model.entities.Estoque;

public class EstoqueSDao {
    private static EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();

    private static List<Estoque> listar(String filtro){
        if(filtro.matches("^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$") == true) {
            return estoqueDao.procurarPorUnidade(Integer.parseInt(filtro));
        }
        return null;
    }

    public static void listarPrint(String filtro){
        if(listar(filtro) == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();
        for (Estoque estoque : listar(filtro)) {
            System.out.println(estoque);
        }
    }
}
