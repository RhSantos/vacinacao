package model.services;

import model.dao.DaoFactory;
import model.dao.UnidadeDao;
import model.entities.Unidade;
import model.exceptions.CadastrarException;

public class UnidadeSDao {
    private static UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();

    public static void cadastrar(Unidade unidade){
        if(unidade == null) 
            throw new CadastrarException("A Unidade não pode ser nula!");
        if(unidadeDao.listaCD() == null && unidade.getCentro() == true && unidade.getNome() != "CD")
            throw new CadastrarException("ERRO - Para Centro de Distribuição utilize o Nome [CD]");
        if(unidadeDao.listaCD() != null && unidade.getNome().equals("CD"))
            throw new CadastrarException("ERRO - Nome Reservado ao CD!");
        if(unidadeDao.listaCD() != null && unidade.getCentro() == true)
            throw new CadastrarException("ERRO - Centro de Distribuição já cadastrado!");
        if(unidadeDao.procurarPorNome(unidade.getNome()) != null)
            throw new CadastrarException("ERRO - Nome de Unidade já utilizado!");
        for (Unidade unidadeT : unidadeDao.listar()) {
            if(unidade.getEndereco().equals(unidadeT.getEndereco()))
                throw new CadastrarException("ERRO - Endereço já utilizado!");
        }
        unidadeDao.inserir(unidade);
    }

}
