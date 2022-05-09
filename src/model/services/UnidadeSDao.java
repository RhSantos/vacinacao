package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UnidadeDao;
import model.entities.Unidade;

public class UnidadeSDao {

    public static int cadastrar(Unidade unidade){
        UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();
        if(unidade == null) {
            System.out.println("A Unidade não pode ser nula!");
            return 0;
        }
        if(unidadeDao.listaCD() == null && unidade.getCentro() == true && !unidade.getNome().equals("CD")){
            System.out.println("ERRO - Para Centro de Distribuição utilize o Nome [CD]");
            return 0;
        }
        if(unidadeDao.listaCD() != null && unidade.getNome().equals("CD")){
            System.out.println("ERRO - Nome Reservado ao CD!");
            return 0;
        }
        if(unidadeDao.procurarPorNome(unidade.getNome()) != null){
            System.out.println("ERRO - Nome de Unidade já utilizado!");
            return 0;
        }
        for (Unidade unidadeT : unidadeDao.listar()) {
            if(unidade.getEndereco().equals(unidadeT.getEndereco())){
                System.out.println("ERRO - Endereço já utilizado!");
                return 0;
            }
        }
        unidadeDao.inserir(unidade);
        return 1;
    }

    private static List<Unidade> listar(String filtro){
        UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();
        if(filtro == "") return unidadeDao.listar();
        if(filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$") == true) {
            return unidadeDao.listar().stream().filter(e -> e.getNome().toLowerCase().startsWith(filtro.toLowerCase())).toList();
        }
        return null;
    }

    public static void listarPrint(String filtro){
        List<Unidade> unidades = listar(filtro);
        if(unidades == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();
        for (Unidade unidade : unidades) {
            System.out.println(unidade);
        }
    }

    public static Unidade procurarPorId(Integer id){
        UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();
        return unidadeDao.procurarPorId(id);
    }

    public static Unidade listarCd(){
        UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();
        return unidadeDao.listaCD();
    }

}
