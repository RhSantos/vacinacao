package model.dao;

import java.util.List;

import model.entities.Endereco;
import model.entities.Unidade;

public interface UnidadeDao {
    void inserir(Unidade unidade);
    void atualizar(Unidade unidade);
    void deletar(Integer id);
    Unidade procurarPorId(Integer id);
    Unidade procurarPorNome(String nome);
    Unidade procurarPorEndereco(Endereco endereco);
    List<Unidade> listar();
    Unidade listaCD();
}
