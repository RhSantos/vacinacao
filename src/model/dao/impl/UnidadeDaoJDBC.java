package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UnidadeDao;
import model.entities.Endereco;
import model.entities.Unidade;

public class UnidadeDaoJDBC implements UnidadeDao{

    private Connection conn;

    public UnidadeDaoJDBC(Connection conn){
       this.conn = conn; 
    }

    @Override
    public void inserir(Unidade unidade) {
        PreparedStatement st = null;
        
        try {   
            st = conn.prepareStatement(
                "INSERT INTO cadastro_unidade "+
                "(nome,centro,endereco) "+
                "VALUES "+
                "(?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

            st.setString(1, unidade.getNome());
            st.setBoolean(2, unidade.getCentro());
            st.setInt(3, unidade.getEndereco().getIdEndereco());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    unidade.setId(id);
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
    public void atualizar(Unidade unidade) {
        PreparedStatement st = null;
        try {   
            st = conn.prepareStatement(
                "UPDATE cadastro_unidade "+
                "SET "+
                "nome = ?,centro = ?,endereco = ? "+
                "WHERE unidade = ?");

            st.setString(1, unidade.getNome());
            st.setBoolean(2, unidade.getCentro());
            st.setInt(3, unidade.getEndereco().getIdEndereco());
            st.setInt(4, unidade.getId());
            
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletar(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Unidade procurarPorId(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Unidade procurarPorNome(String nome) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Unidade procurarPorEndereco(Endereco endereco) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Unidade> listar() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Unidade listaCD() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * "+
                "FROM cadastro_unidade INNER JOIN endereco "+
                "ON cadastro_unidade.endereco = endereco.endereco "+
                "WHERE cadastro_unidade.centro = 1");
            rs = st.executeQuery();
            if(rs.next()){
                Endereco endereco = instanciarEndereco(rs);
                Unidade unidade = instanciarUnidade(rs,endereco);
                return unidade;
            }
            return null;
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Unidade instanciarUnidade(ResultSet rs,Endereco endereco) throws SQLException {
        Unidade unidade = new Unidade();
        unidade.setId(rs.getInt("unidade"));
        unidade.setNome(rs.getString("nome"));
        unidade.setCentro(rs.getBoolean("centro"));
        unidade.setEndereco(endereco);
        return unidade;
}

    private Endereco instanciarEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(rs.getInt("endereco"));
        endereco.setLogradouro(rs.getString("logradouro"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setEstado(rs.getString("estado"));
        endereco.setNumero(rs.getInt("numero"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setComplemento(rs.getString("complemento"));
        endereco.setCep(rs.getString("cep"));
        return endereco;
    }
    
}
