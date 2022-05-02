package model.entities;

import java.util.Date;

public class Vacinado {
    
    private Integer dose;
    private Pessoa pessoa;
    private Unidade unidade;
    private Lote vacina;
    private Movimento movimento;
    private Date dataVacinacao;
    
    public Vacinado(Integer dose, Pessoa pessoa, Unidade unidade, Lote vacina, Movimento movimento,
            Date dataVacinacao) {
        this.dose = dose;
        this.pessoa = pessoa;
        this.unidade = unidade;
        this.vacina = vacina;
        this.movimento = movimento;
        this.dataVacinacao = dataVacinacao;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
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
