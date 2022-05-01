package model.dao;

import java.util.List;

import model.entities.Endereco;
import model.entities.Pessoa;

public interface PessoaDao {
    void inserir(Pessoa pessoa);
    void atualizar(Pessoa pessoa);
    void deletar(Integer id);
    Pessoa procurarPorId(Integer id);
    Pessoa procurarPorEndereco(Endereco endereco);
    List<Pessoa> listar();
}
