package model.entities;

import java.io.Serializable;

public class Estoque implements Serializable{
    private Integer unidade;
    private Lote lote;
    private Integer quantidade;

    public Estoque(Integer unidade, Lote lote, Integer quantidade) {
        this.unidade = unidade;
        this.lote = lote;
        this.quantidade = quantidade;
    }

    public Estoque(Lote lote, Integer quantidade) {
        this.lote = lote;
        this.quantidade = quantidade;
    }

    public Integer getUnidade() {
        return unidade;
    }

    public void setUnidade(Integer unidade) {
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

}
