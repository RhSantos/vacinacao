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
import model.dao.LoteDao;
import model.entities.Lote;

public class LoteDaoJDBC implements LoteDao{

    private Connection conn;

    public LoteDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Lote lote) {
        PreparedStatement st = null;
        if(lote.getLote() == null){
            try {   
                st = conn.prepareStatement(
                    "INSERT INTO lote "+
                    "(nome,data_vencimento) "+
                    "VALUES "+
                    "(?,?)",
                    Statement.RETURN_GENERATED_KEYS);

                st.setString(1, lote.getNome());
                st.setDate(2, new java.sql.Date(lote.getDataVencimento().getTime()));

                int rowsAffected = st.executeUpdate();

                if(rowsAffected > 0){
                    ResultSet rs = st.getGeneratedKeys();
                    if(rs.next()){
                        int id = rs.getInt(1);          
                        lote.setLote(id);
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
        else{
            try {   
                st = conn.prepareStatement(
                    "INSERT INTO lote "+
                    "(lote,nome,data_vencimento) "+
                    "VALUES "+
                    "(?,?,?)");
    
                st.setInt(1, lote.getLote());
                st.setString(2, lote.getNome());
                st.setDate(3, new java.sql.Date(lote.getDataVencimento().getTime()));
                
                st.execute();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
            }  
        } 
    }

    @Override
    public void atualizar(Lote lote) {
        PreparedStatement st = null;
        try {   
            st = conn.prepareStatement(
                "UPDATE lote "+
                "SET "+
                "nome = ?,data_vencimento = ?"+
                "WHERE lote = ?");

            st.setString(1, lote.getNome());
            st.setDate(2, new java.sql.Date(lote.getDataVencimento().getTime()));
            st.setInt(3, lote.getLote());
            
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletar(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "DELETE FROM lote "+
                "WHERE lote = ?");
            
            st.setInt(1, id);
            
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Lote> procurarPorNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT *"+
                "FROM lote WHERE nome = ?");
            st.setString(1, nome);
            rs = st.executeQuery();
            List<Lote> list = new ArrayList<>();
            while(rs.next()){
                Lote lote = instanciarLote(rs);
                list.add(lote);
            }
            return list;
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Lote procurarPorId(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * "+
                "FROM lote "+
                "WHERE lote  = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Lote lote = instanciarLote(rs);
                return lote;
            }
            return null;
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Lote> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT *"+
                "FROM lote ");
            rs = st.executeQuery();
            List<Lote> list = new ArrayList<>();
            while(rs.next()){
                Lote lote = instanciarLote(rs);
                list.add(lote);
            }
            return list;
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
    public static Lote instanciarLote(ResultSet rs) throws SQLException {
        Lote lote = new Lote();
        lote.setLote(rs.getInt("lote"));
        lote.setNome(rs.getString("nome"));
        lote.setDataVencimento(rs.getDate("data_vencimento"));
        return lote;
}

}
