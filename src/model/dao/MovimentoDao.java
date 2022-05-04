package model.dao;

import java.util.Date;
import java.util.List;

import model.entities.Lote;
import model.entities.Movimento;
import model.entities.Pessoa;
import model.entities.Unidade;
import model.enums.TipoMovimento;

public interface MovimentoDao {
    void inserir(Movimento movimento);
    void atualizar(Movimento movimento);
    void deletar(Integer id);
    Movimento procurarPorId(Integer id);
    List<Movimento> procurarPorUnidade(Integer unidade);
    List<Movimento> procurarPorLote(Integer lote);
    List<Movimento> procurarPorUnidadeLote(Integer unidade, Integer lote);
    List<Movimento> procurarPorDataMovimento(Date dataMovimentacao);
    List<Movimento> procurarPorPessoa(Integer pessoa);
    List<Movimento> procurarPorTipoMovimento(TipoMovimento tipo);
    List<Movimento> listar();
}
