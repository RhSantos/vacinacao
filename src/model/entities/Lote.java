package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lote {
    private Integer lote;
    private String nome;
    private Date dataVencimento;
    
    public Lote(Integer lote, String nome, Date dataVencimento) {
        this.lote = lote;
        this.nome = nome;
        this.dataVencimento = dataVencimento;
    }

    public Lote(String nome, Date dataVencimento) {
        this.nome = nome;
        this.dataVencimento = dataVencimento;
    }

    public Lote() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String divisoria = "\n________________________\n";
        return "\nLote:"+divisoria+"ID: "+lote
            +"\nNome: "+nome+"\nData de Validade: "+sdf.format(dataVencimento)+divisoria;
    }

}
