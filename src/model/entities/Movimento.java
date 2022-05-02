package model.entities;

import java.util.Date;

import model.enums.TipoMovimento;
import model.enums.TipoTransacao;

public class Movimento {
    private Integer id;
    private Unidade unidade;
    private Lote lote;
    private Pessoa pessoa;
    private Integer quantidade;
    private TipoMovimento tipoMovimento;
    private TipoTransacao tipoTransacao;
    private Date dataMovimento;
    private Unidade unidadeTransfer;

    public Movimento(Integer id, Unidade unidade, Lote lote, Pessoa pessoa,
            Integer quantidade, TipoMovimento tipoMovimento,TipoTransacao tipoTransacao, 
            Date dataMovimento) {
        this.id = id;
        this.lote = lote;
        this.pessoa = pessoa;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.tipoMovimento = tipoMovimento;
        this.tipoTransacao = tipoTransacao;
        this.dataMovimento = dataMovimento;
    }

    public Movimento(Integer id, Unidade unidade,Lote lote,Unidade unidadeTransfer, Integer quantidade,
            TipoMovimento tipoMovimento,TipoTransacao tipoTransacao,Date dataMovimento) {
        this.id = id;
        this.lote = lote;
        this.unidade = unidade;
        this.unidadeTransfer = unidadeTransfer;
        this.quantidade = quantidade;
        this.tipoMovimento = tipoMovimento;
        this.tipoTransacao = tipoTransacao;
        this.dataMovimento = dataMovimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidadeTransfer() {
        return unidadeTransfer;
    }

    public void setUnidadeTransfer(Unidade unidadeTransfer) {
        this.unidadeTransfer = unidadeTransfer;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }
}
