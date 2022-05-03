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
    List<Movimento> procurarPorUnidade(Unidade unidade);
    List<Movimento> procurarPorLote(Lote lote);
    List<Movimento> procurarPorUnidadeLote(Unidade unidade, Lote lote);
    List<Movimento> procurarPorDataMovimento(Date dataMovimentacao);
    List<Movimento> procurarPorPessoa(Pessoa pessoa);
    List<Movimento> procurarPorTipoMovimento(TipoMovimento tipo);
    List<Movimento> listar();
}
