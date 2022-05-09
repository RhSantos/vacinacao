package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.LoteDao;
import model.entities.Lote;

public class LoteSDao {

    public static int cadastrar(Lote lote){
        LoteDao loteDao = DaoFactory.createLoteDao();
        if(lote == null) {
            System.out.println("O Lote não pode ser Nulo!");
            return 0;
        }
        loteDao.inserir(lote);
        return 1;
    }

    private static List<Lote> listar(String filtro){
        LoteDao loteDao = DaoFactory.createLoteDao();
        if(filtro == "") return loteDao.listar();
        if(filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$") == true) {
            return loteDao.listar().stream().filter(e -> e.getNome().toLowerCase().startsWith(filtro.toLowerCase())).toList();
        }
        return null;
    }

    public static void listarPrint(String filtro){
        List<Lote> lotes = listar(filtro);
        if(lotes == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();
        for (Lote lote : lotes) {
            System.out.println(lote);
        }
    }

    public static Lote procurarPorId(Integer id) {
        LoteDao loteDao = DaoFactory.createLoteDao();
        return loteDao.procurarPorId(id);
    }

    
}
