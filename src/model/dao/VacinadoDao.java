package model.dao;

import java.util.List;

import model.entities.Vacinado;

public interface VacinadoDao {
    void inserir(Vacinado vacinado);
    void atualizar(Vacinado vacinado);
    void deletar(Integer dose,Integer pessoa);
    Vacinado procurarPorDosePessoa(Integer dose,Integer pessoa);
    List<Vacinado> procurarPorPessoa(Integer pessoa);
    List<Vacinado> procurarPorUnidade(Integer unidade);
    List<Vacinado> listar();
}
