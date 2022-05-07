package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EstoqueDao;
import model.entities.Estoque;
import model.entities.Lote;
import model.entities.Unidade;

public class EstoqueSDao {

    public static void cadastrar(Estoque estoque){
        EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();
        if(estoque == null) {
            System.out.println("O Estoque não pode ser Nulo!");
            return;
        }
        estoqueDao.inserir(estoque);
    } 

    private static List<Estoque> listar(String filtro){
        EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();
        if(filtro.matches("^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$") == true) {
            return estoqueDao.procurarPorUnidade(Integer.parseInt(filtro));
        }
        return null;
    }

    public static void atualizar(Estoque estoque){
        EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();
        if(estoque == null) {
            System.out.println("O Estoque não pode ser Nulo!");
            return;
        }
        estoqueDao.atualizar(estoque);
    }

    public static Estoque procurarPorUnidadeLote(Unidade unidade,Lote lote){
        EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();
        return estoqueDao.procurarPorUnidadeLote(unidade.getId(), lote.getLote());
    }

    public static List<Estoque> procurarPorLote(Lote lote){
        EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();
        return estoqueDao.procurarPorLote(lote.getLote());
    }

    public static void listarPrint(String filtro){
        List<Estoque> estoques = listar(filtro);
        if(estoques == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();
        for (Estoque estoque : estoques) {
            System.out.println(estoque);
        }
    }
}
