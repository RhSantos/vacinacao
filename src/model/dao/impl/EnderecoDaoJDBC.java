package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        PreparedStatement st = null;

        try {   
            st = conn.prepareStatement(
                "INSERT INTO endereco "+
                "(logradouro,cidade,estado,numero,bairro,complemento,cep) "+
                "VALUES "+
                "(?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

            st.setString(1, endereco.getLogradouro());
            st.setString(2, endereco.getCidade());
            st.setString(3, endereco.getEstado());
            st.setInt(4, endereco.getNumero());
            st.setString(5, endereco.getBairro());
            st.setString(6, endereco.getComplemento());
            st.setString(7, endereco.getCep());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    endereco.setIdEndereco(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
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
    
                Endereco endereco = new Endereco
                    (rs.getInt(1), rs.getString(2), 
                    rs.getString(3), rs.getString(4), 
                    rs.getInt(5),rs.getString(6), 
                    rs.getString(7), rs.getString(8));
            
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

        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * FROM endereco");
            rs = st.executeQuery();

            List<Endereco> list = new ArrayList<>();

            while(rs.next()){
                Endereco enderecos = new Endereco
                (rs.getInt(1), rs.getString(2), 
                rs.getString(3), rs.getString(4), 
                rs.getInt(5),rs.getString(6), 
                rs.getString(7), rs.getString(8));

                list.add(enderecos);
            }
            return list;
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}
