package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UnidadeDao;
import model.entities.Unidade;

public class UnidadeSDao {
    private static UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();

    public static void cadastrar(Unidade unidade){
        if(unidade == null) {
            System.out.println("A Unidade não pode ser nula!");
            return;
        }
        if(unidadeDao.listaCD() == null && unidade.getCentro() == true && unidade.getNome() != "CD"){
            System.out.println("ERRO - Para Centro de Distribuição utilize o Nome [CD]");
            return;
        }
        if(unidadeDao.listaCD() != null && unidade.getNome().equals("CD")){
            System.out.println("ERRO - Nome Reservado ao CD!");
            return;
        }
        if(unidadeDao.listaCD() != null && unidade.getCentro() == true){
            System.out.println("ERRO - Centro de Distribuição já cadastrado!");
            return;
        }
        if(unidadeDao.procurarPorNome(unidade.getNome()) != null){
            System.out.println("ERRO - Nome de Unidade já utilizado!");
            return;
        }
        for (Unidade unidadeT : unidadeDao.listar()) {
            if(unidade.getEndereco().equals(unidadeT.getEndereco())){
                System.out.println("ERRO - Endereço já utilizado!");
                return;
            }
        }
        unidadeDao.inserir(unidade);
    }

    private static List<Unidade> listar(String filtro){
        if(filtro == "") return unidadeDao.listar();
        if(filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$") == true) {
            return unidadeDao.listar().stream().filter(e -> e.getNome().toLowerCase().startsWith(filtro.toLowerCase())).toList();
        }
        return null;
    }

    public static void listarPrint(String filtro){
        if(listar(filtro) == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();
        for (Unidade unidade : listar(filtro)) {
            System.out.println(unidade);
        }
    }

}
