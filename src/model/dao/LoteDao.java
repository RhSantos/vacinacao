package model.dao;

import java.util.List;

import model.entities.Lote;

public interface LoteDao {
    void inserir(Lote lote);
    void atualizar(Lote lote);
    void deletar(Integer id);
    Lote procurarPorId(Integer id);
    Lote procurarPorNome(String nome);
    List<Lote> listar();
}
