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
import model.dao.EstoqueDao;
import model.entities.Endereco;
import model.entities.Estoque;
import model.entities.Lote;
import model.entities.Unidade;

public class EstoqueDaoJDBC implements EstoqueDao{

    private Connection conn;

    public EstoqueDaoJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void inserir(Estoque estoque) {
        PreparedStatement st = null;

        try {   
            st = conn.prepareStatement(
                "INSERT INTO estoque_vacinas "+
                "(unidade,lote,quantidade) "+
                "VALUES "+
                "(?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, estoque.getUnidade().getId());
            st.setInt(2, estoque.getLote().getLote());
            st.setInt(3, estoque.getQuantidade());

            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0) {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
        
    }

    @Override
    public void atualizar(Estoque estoque) {
        PreparedStatement st = null;
        try {   
            st = conn.prepareStatement(
                "UPDATE estoque_vacinas "+
                "SET "+
                "quantidade = ? "+
                "WHERE unidade = ? && lote = ?");

            st.setInt(1, estoque.getQuantidade());
            st.setInt(2, estoque.getUnidade().getId());
            st.setInt(3, estoque.getLote().getLote());
            
            
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletar(Integer unidade, Integer lote) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "DELETE FROM estoque_vacinas "+
                "WHERE unidade = ? && lote = ?");
            
            st.setInt(1,unidade);
            st.setInt(2,lote);
            
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }   
    }

    @Override
    public List<Estoque> procurarPorUnidade(Integer unidade) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT cadastro_unidade.unidade,cadastro_unidade.nome,cadastro_unidade.centro"+
                ",endereco.*,lote.*,quantidade " +
                "FROM estoque_vacinas "+ 
                "INNER JOIN cadastro_unidade "+
                "ON estoque_vacinas.unidade = cadastro_unidade.unidade "+
                "INNER JOIN endereco ON cadastro_unidade.endereco = endereco.endereco "+
                "INNER JOIN lote "+
                "ON estoque_vacinas.lote = lote.lote "+
                "WHERE estoque_vacinas.unidade = ?");
            st.setInt(1, unidade);
            rs = st.executeQuery();
            List<Estoque> list = new ArrayList<>();
            while(rs.next()){
                Endereco endereco = UnidadeDaoJDBC.instanciarEndereco(rs);
                Unidade uniEst = UnidadeDaoJDBC.instanciarUnidade(rs,endereco);
                Lote lote = LoteDaoJDBC.instanciarLote(rs);
                list.add(new Estoque(uniEst, lote, rs.getInt(3)));
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
    public List<Estoque> procurarPorLote(Integer lote) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT cadastro_unidade.unidade,cadastro_unidade.nome,cadastro_unidade.centro"+
                ",endereco.*,lote.*,quantidade " +
                "FROM estoque_vacinas "+ 
                "INNER JOIN cadastro_unidade "+
                "ON estoque_vacinas.unidade = cadastro_unidade.unidade "+
                "INNER JOIN endereco ON cadastro_unidade.endereco = endereco.endereco "+
                "INNER JOIN lote "+
                "ON estoque_vacinas.lote = lote.lote "+
                "WHERE estoque_vacinas.lote = ?");
            st.setInt(1, lote);
            rs = st.executeQuery();
            List<Estoque> list = new ArrayList<>();
            while(rs.next()){
                Endereco endereco = UnidadeDaoJDBC.instanciarEndereco(rs);
                Unidade unidade = UnidadeDaoJDBC.instanciarUnidade(rs,endereco);
                Lote loteEst = LoteDaoJDBC.instanciarLote(rs);
                list.add(new Estoque(unidade,loteEst, rs.getInt(3)));
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
    public List<Estoque> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT cadastro_unidade.unidade,cadastro_unidade.nome,cadastro_unidade.centro"+
                ",endereco.*,lote.*,quantidade " +
                "FROM estoque_vacinas "+ 
                "INNER JOIN cadastro_unidade "+
                "ON estoque_vacinas.unidade = cadastro_unidade.unidade "+
                "INNER JOIN endereco ON cadastro_unidade.endereco = endereco.endereco "+
                "INNER JOIN lote "+
                "ON estoque_vacinas.lote = lote.lote");

            rs = st.executeQuery();
            List<Estoque> list = new ArrayList<>();
            while(rs.next()){
                Endereco endereco = UnidadeDaoJDBC.instanciarEndereco(rs);
                Unidade unidade = UnidadeDaoJDBC.instanciarUnidade(rs,endereco);
                Lote lote = LoteDaoJDBC.instanciarLote(rs);
                list.add(new Estoque(unidade, lote, rs.getInt(3)));
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
    public Estoque procurarPorUnidadeLote(Integer unidade, Integer lote) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT cadastro_unidade.unidade,cadastro_unidade.nome,cadastro_unidade.centro"+
                ",endereco.*,lote.*,quantidade " +
                "FROM estoque_vacinas "+ 
                "INNER JOIN cadastro_unidade "+
                "ON estoque_vacinas.unidade = cadastro_unidade.unidade "+
                "INNER JOIN endereco ON cadastro_unidade.endereco = endereco.endereco "+
                "INNER JOIN lote "+
                "ON estoque_vacinas.lote = lote.lote "+
                "WHERE estoque_vacinas.unidade = ? && estoque_vacinas.lote = ?");
            st.setInt(1, unidade);
            st.setInt(2, lote);
            rs = st.executeQuery();

            if(rs.next()){
                Endereco endereco = UnidadeDaoJDBC.instanciarEndereco(rs);
                Unidade uniEst = UnidadeDaoJDBC.instanciarUnidade(rs,endereco);
                Lote loteEst = LoteDaoJDBC.instanciarLote(rs);
                return new Estoque(uniEst, loteEst, rs.getInt(3));
            }
            return null;
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
}
