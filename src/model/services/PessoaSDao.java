package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Pessoa;

public class PessoaSDao {

    private static PessoaDao pessoaDao = DaoFactory.createPessoaDao();

    public static void cadastrar(Pessoa pessoa){
        if(pessoa == null) {
            System.out.println("A Pessoa não pode ser Nula!");
            return;
        }
        if(pessoaDao.procurarPorCpf(pessoa.getCpf()) != null){
            System.out.println("ERRO - CPF já cadastrado!");
            return;
        }
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
        List<Pessoa> pessoas = listar(filtro);
        if(pessoas == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
    }
}
