package model.entities;

import java.util.Date;

public class Lote {
    private String lote;
    private String nome;
    private Date dataVencimento;
    
    public Lote(String lote, String nome, Date dataVencimento) {
        this.lote = lote;
        this.nome = nome;
        this.dataVencimento = dataVencimento;
    }

    public Lote(String nome, Date dataVencimento) {
        this.nome = nome;
        this.dataVencimento = dataVencimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    
    
    
}
