package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Pessoa;
import model.exceptions.CadastrarException;

public class PessoaSDao {

    private static PessoaDao pessoaDao = DaoFactory.createPessoaDao();

    public static void cadastrar(Pessoa pessoa){
        if(pessoa == null) 
            throw new CadastrarException("A Pessoa não pode ser Nula!");
        if(pessoaDao.procurarPorCpf(pessoa.getCpf()) != null)
            throw new CadastrarException("ERRO - CPF já cadastrado!");
        pessoaDao.inserir(pessoa);
    }

    private static List<Pessoa> listar(String filtro){
        if(filtro == "") return pessoaDao.listar();
        if(filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$") == true) {
            return pessoaDao.listar().stream().filter(e -> e.getNome().toLowerCase().startsWith(filtro.toLowerCase())).toList();
        }
        return null;
    }

    public static void listarPrint(String filtro){
        if(listar(filtro) == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();
        for (Pessoa pessoa : listar(filtro)) {
            System.out.println(pessoa);
        }
    }
}
