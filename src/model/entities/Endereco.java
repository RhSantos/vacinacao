package model.entities;

import java.io.Serializable;

public class Endereco implements Serializable {
    private Integer id;
    private String logradouro;
    private String cidade;
    private String estado;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String cep;

    public Endereco(Integer id, String logradouro, String cidade, String estado, Integer numero, String bairro,
            String complemento, String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco(Integer id, String logradouro, String cidade, String estado, Integer numero, String bairro,
            String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }

    public Endereco(String logradouro, String cidade, String estado, Integer numero, String bairro, String complemento,
            String cep) {
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco(String logradouro, String cidade, String estado, Integer numero, String bairro, String cep) {
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }

    public Endereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        String divisoria = "\n------------------------\n";
        return "Endereço:"+divisoria+"Logradouro: "+logradouro+"\nCidade: "+cidade+
            "\nEstado: "+estado+"\nNúmero: "+numero+"\nBairro: "+bairro+"\nComplemento: "+
            (complemento != null||complemento == ""?complemento:"Sem Complemento")+"\nCep: "+cep+divisoria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cep == null) ? 0 : cep.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        if (cep == null) {
            if (other.cep != null)
                return false;
        } else if (!cep.equals(other.cep))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }  

}
