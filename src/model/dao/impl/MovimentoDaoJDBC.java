package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.MovimentoDao;
import model.entities.Lote;
import model.entities.Movimento;
import model.entities.Pessoa;
import model.entities.Unidade;
import model.enums.TipoMovimento;

public class MovimentoDaoJDBC implements MovimentoDao{

    private Connection conn;

    public MovimentoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Movimento movimento) {
        PreparedStatement st = null;

        try {   
            st = conn.prepareStatement(
                "INSERT INTO movimento_estoque "+
                "(unidade,lote,pessoa,quantidade,tipo_movimento"+
                ",tipo_transacao,data_movimento,unidade_transfer) "+
                "VALUES "+
                "(?,?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, movimento.getUnidade().getId());
            st.setInt(2, movimento.getLote().getLote());
            if(movimento.getPessoa() == null){
                st.setNull(3,Types.INTEGER);
            }
            else{
                st.setInt(3, movimento.getPessoa().getId());
            }
            st.setInt(4, movimento.getQuantidade());
            st.setString(5, movimento.getTipoMovimento().name());
            st.setString(6, movimento.getTipoTransacao().name());
            st.setDate(7, new java.sql.Date(movimento.getDataMovimento().getTime()));
            st.setInt(8, movimento.getUnidadeTransfer().getId());

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    movimento.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else{
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Movimento movimento) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deletar(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Movimento procurarPorId(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Movimento> procurarPorUnidade(Unidade unidade) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Movimento> procurarPorLote(Lote lote) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Movimento> procurarPorUnidadeLote(Unidade unidade, Lote lote) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Movimento> procurarPorDataMovimento(Date dataMovimentacao) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Movimento> procurarPorPessoa(Pessoa pessoa) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Movimento> procurarPorTipoMovimento(TipoMovimento tipo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Movimento> listar() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
