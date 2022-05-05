package model.entities;

import java.io.Serializable;

import model.dao.DaoFactory;
import model.dao.LoteDao;
import model.dao.UnidadeDao;

public class Estoque implements Serializable{
    private Unidade unidade;
    private Lote lote;
    private Integer quantidade;

    public Estoque(){}

    public Estoque(Unidade unidade, Lote lote, Integer quantidade) {
        this.unidade = unidade;
        this.lote = lote;
        this.quantidade = quantidade;
    }

    public Estoque(Integer idUnidade,Integer idLote,Integer quantidade) {
        LoteDao loteDao = DaoFactory.createLoteDao();
        UnidadeDao uniDao = DaoFactory.createUnidadeDao();
        this.unidade = uniDao.procurarPorId(idUnidade);
        this.lote = loteDao.procurarPorId(idLote);
        this.quantidade = quantidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Lote getlote() {
        return lote;
    }

    public void setlote(Lote lote) {
        this.lote = lote;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        String divisoria = "\n>>>>>>>>>>>>>>>>>>>>>>>>\n";
        return "\nEstoque:"+divisoria+unidade+"\n"
            +lote+"\nQuantidade: "+quantidade+"\n"+divisoria;
    }
}
