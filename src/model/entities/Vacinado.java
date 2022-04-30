package model.entities;

import java.util.Date;

public class Vacinado {
    private Movimento movimento;
    private Pessoa pessoa;
    private Lote vacina;
    private Date dataVacinacao;
    
    public Vacinado(Movimento movimento, Pessoa pessoa, Lote vacina, 
        Date dataVacinacao) {
        this.movimento = movimento;
        this.pessoa = pessoa;
        this.vacina = vacina;
        this.dataVacinacao = dataVacinacao;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Lote getVacina() {
        return vacina;
    }

    public void setVacina(Lote vacina) {
        this.vacina = vacina;
    }

    public Date getDataVacinacao() {
        return dataVacinacao;
    }

    public void setDataVacinacao(Date dataVacinacao) {
        this.dataVacinacao = dataVacinacao;
    }

}
