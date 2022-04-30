package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EnderecoDao;
import model.entities.Endereco;

public class EnderecoDaoJDBC implements EnderecoDao{

    private Connection conn;
    
    public EnderecoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Endereco endereco) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void atualizar(Endereco endereco) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deletar(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Endereco procurarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            
            st = conn.prepareStatement(
                "SELECT * FROM endereco WHERE endereco.endereco = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()){
                Endereco endereco = new Endereco(rs.getInt(1)
                , rs.getString(2),rs.getString(3), 
                rs.getString(4), rs.getInt(5), 
                rs.getString(6), rs.getString(7));
            
            return endereco;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }     
    }

    @Override
    public List<Endereco> listar() {
        // TODO Auto-generated method stub
        return null;
    }

}
