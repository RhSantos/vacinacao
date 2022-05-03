package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.MovimentoDao;
import model.entities.Endereco;
import model.entities.Lote;
import model.entities.Movimento;
import model.entities.Pessoa;
import model.entities.Unidade;
import model.enums.TipoMovimento;
import model.enums.TipoTransacao;

public class MovimentoDaoJDBC implements MovimentoDao {

    private Connection conn;

    public MovimentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Movimento movimento) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO movimento_movimento " +
                            "(unidade,lote,pessoa,quantidade,tipo_movimento" +
                            ",tipo_transacao,data_movimento,unidade_transfer) " +
                            "VALUES " +
                            "(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, movimento.getUnidade().getId());
            st.setInt(2, movimento.getLote().getLote());
            if (movimento.getPessoa() == null) {
                st.setNull(3, Types.INTEGER);
            } else {
                st.setInt(3, movimento.getPessoa().getId());
            }
            st.setInt(4, movimento.getQuantidade());
            st.setString(5, movimento.getTipoMovimento().name());
            st.setString(6, movimento.getTipoTransacao().name());
            st.setDate(7, new java.sql.Date(movimento.getDataMovimento().getTime()));
            if (movimento.getUnidadeTransfer() == null) {
                st.setNull(8, Types.INTEGER);
            } else {
                st.setInt(8, movimento.getUnidadeTransfer().getId());
            }

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    movimento.setId(id);
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
    public void atualizar(Movimento movimento) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE movimento_estoque " +
                            "SET " +
                            "unidade = ?,lote = ?,pessoa = ?," +
                            "quantidade = ?,tipo_movimento = ?," +
                            "tipo_transacao = ?," +
                            "data_movimento = ?,unidade_transfer = ? " +
                            "WHERE movimento = ?");

            st.setInt(1, movimento.getUnidade().getId());
            st.setInt(2, movimento.getLote().getLote());
            if (movimento.getPessoa() == null) {
                st.setNull(3, Types.INTEGER);
            } else {
                st.setInt(3, movimento.getPessoa().getId());
            }
            st.setInt(4, movimento.getQuantidade());
            st.setString(5, movimento.getTipoMovimento().name());
            st.setString(6, movimento.getTipoTransacao().name());
            st.setDate(7, new java.sql.Date(movimento.getDataMovimento().getTime()));
            if (movimento.getUnidadeTransfer() == null) {
                st.setNull(8, Types.INTEGER);
            } else {
                st.setInt(8, movimento.getUnidadeTransfer().getId());
            }
            st.setInt(9, movimento.getId());

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
                    "DELETE FROM movimento_estoque " +
                            "WHERE movimento = ?");

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
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
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(
                    "SELECT " +
                            "movimento_estoque.movimento," +
                            "uni.unidade," +
                            "uni.nome," +
                            "uni.centro," +
                            "uniEndereco.*," +
                            "lote.*," +
                            "pessoa.pessoa," +
                            "pessoa.nome," +
                            "pessoa.cpf," +
                            "pesEndereco.*," +
                            "movimento_estoque.quantidade as movQuant," +
                            "movimento_estoque.tipo_movimento," +
                            "movimento_estoque.tipo_transacao," +
                            "movimento_estoque.data_movimento FROM movimento_estoque " +
                            "INNER JOIN cadastro_unidade as uni ON movimento_estoque.unidade = uni.unidade " +
                            "INNER JOIN endereco as uniEndereco ON uni.endereco = uniEndereco.endereco " +
                            "INNER JOIN lote ON movimento_estoque.lote = lote.lote " +
                            "INNER JOIN cadastro_pessoa as pessoa ON movimento_estoque.pessoa = pessoa.pessoa " +
                            "INNER JOIN endereco as pesEndereco ON pessoa.endereco = pesEndereco.endereco");

            st2 = conn.prepareStatement(
                    "SELECT " +
                            "movimento_estoque.movimento," +
                            "uni.unidade," +
                            "uni.nome," +
                            "uni.centro," +
                            "uniEndereco.*," +
                            "lote.*," +
                            "movimento_estoque.quantidade as movQuant," +
                            "movimento_estoque.tipo_movimento," +
                            "movimento_estoque.tipo_transacao," +
                            "movimento_estoque.data_movimento," +
                            "uniTransfer.unidade," +
                            "uniTransfer.nome," +
                            "uniTransfer.centro," +
                            "uniTransferEndereco.* FROM movimento_estoque " +
                            "INNER JOIN cadastro_unidade as uni ON movimento_estoque.unidade = uni.unidade " +
                            "INNER JOIN endereco as uniEndereco ON uni.endereco = uniEndereco.endereco " +
                            "INNER JOIN lote ON movimento_estoque.lote = lote.lote " +
                            "INNER JOIN cadastro_unidade as uniTransfer ON movimento_estoque.unidade_transfer = uniTransfer.unidade "
                            +
                            "INNER JOIN endereco as uniTransferEndereco ON uniTransfer.endereco = uniTransferEndereco.endereco");

            rs1 = st1.executeQuery();
            rs2 = st2.executeQuery();
            List<Movimento> list = new ArrayList<>();
            while (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = UnidadeDaoJDBC.instanciarEndereco(rs1);
                Unidade uni = UnidadeDaoJDBC.instanciarUnidade(rs1, endUni);
                Endereco endPessoa = UnidadeDaoJDBC.instanciarEndereco(rs1);
                Pessoa pes = PessoaDaoJDBC.instanciarPessoa(rs1, endPessoa);
                list.add(new Movimento(uni, lote, pes, rs1.getInt(5),
                        TipoMovimento.valueOf(rs1.getString(6)),
                        TipoTransacao.valueOf(rs1.getString(7)),
                        rs1.getDate(8)));
            }

            while (rs2.next()){
                Lote lote = LoteDaoJDBC.instanciarLote(rs2);
                Endereco endUni = UnidadeDaoJDBC.instanciarEndereco(rs2);
                Unidade uni = UnidadeDaoJDBC.instanciarUnidade(rs2, endUni);
                Endereco endUniTransfer = UnidadeDaoJDBC.instanciarEndereco(rs2);
                Unidade uniTransfer = UnidadeDaoJDBC.instanciarUnidade(rs2, endUniTransfer);
                list.add(new Movimento(uni, lote, rs2.getInt(5),
                        TipoMovimento.valueOf(rs2.getString(6)),
                        TipoTransacao.valueOf(rs2.getString(7)),
                        rs2.getDate(8), uniTransfer));
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st1);
            DB.closeStatement(st2);
            DB.closeResultSet(rs1);
            DB.closeResultSet(rs2);
        }
    }

}
