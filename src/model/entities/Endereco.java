package model.entities;

import java.io.Serializable;

public class Endereco implements Serializable {
    private Integer idEndereco;
    private String logradouro;
    private String cidade;
    private String estado;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String cep;

    public Endereco(Integer idEndereco, String logradouro, String cidade, String estado, Integer numero, String bairro,
            String complemento, String cep) {
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco(Integer idEndereco, String logradouro, String cidade, String estado, Integer numero, String bairro,
            String cep) {
        this.idEndereco = idEndereco;
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

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
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
        return "Endereco [bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", complemento=" + complemento
                + ", estado=" + estado + ", idEndereco=" + idEndereco + ", logradouro=" + logradouro + ", numero="
                + numero + "]";
    }

}
