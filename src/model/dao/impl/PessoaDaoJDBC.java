package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.PessoaDao;
import model.entities.Pessoa;

public class PessoaDaoJDBC implements PessoaDao{

    private Connection conn;

    public PessoaDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Pessoa pessoa) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deletar(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Pessoa procurarPorId(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Pessoa> listar() {
        // TODO Auto-generated method stub
        return null;
    }

}
