package model.dao;

import java.util.List;

import model.entities.Estoque;
import model.entities.Lote;
import model.entities.Unidade;

public interface EstoqueDao {
    void inserir(Estoque estoque);
    void atualizar(Estoque estoque);
    void deletar(Integer unidade,Integer lote);
    Estoque procurarPorUnidadeLote(Integer unidade,Integer lote);
    List<Estoque> procurarPorUnidade(Integer unidade);
    List<Estoque> procurarPorLote(Integer lote);
    List<Estoque> listar();
}
