package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Pessoa;

public class PessoaSDao {

    public static int cadastrar(Pessoa pessoa){
        PessoaDao pessoaDao = DaoFactory.createPessoaDao();
        if(pessoa == null) {
            System.out.println("A Pessoa não pode ser Nula!");
            return 0;
        }
        if(pessoaDao.procurarPorCpf(pessoa.getCpf()) != null){
            System.out.println("ERRO - CPF já cadastrado!");
            return 0;
        }
        pessoaDao.inserir(pessoa);
        return 1;
    }

    public static Pessoa procurarPorCpf(String cpf){
        PessoaDao pessoaDao = DaoFactory.createPessoaDao();
        return pessoaDao.procurarPorCpf(cpf);
    }

    private static List<Pessoa> listar(String filtro){
        PessoaDao pessoaDao = DaoFactory.createPessoaDao();
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

    public static Pessoa procurarPorId(Integer id){
        PessoaDao pessoaDao = DaoFactory.createPessoaDao();
        return pessoaDao.procurarPorId(id);
    }
}
