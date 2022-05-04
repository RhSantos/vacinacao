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
    private String queryPessoa = "SELECT " +
    "movimento_estoque.movimento," +
    "uni.unidade," +
    "uni.nome AS uniNome," +
    "uni.centro," +
    "uniEndereco.endereco AS uniEndereco," +
    "uniEndereco.logradouro AS uniLogradouro," +
    "uniEndereco.cidade AS uniCidade," +
    "uniEndereco.estado AS uniEstado," +
    "uniEndereco.numero AS uniNumero," +
    "uniEndereco.bairro AS uniBairro," +
    "uniEndereco.complemento AS uniComplemento," +
    "uniEndereco.cep AS uniCep," +
    "lote.lote," +
    "lote.nome," +
    "lote.data_vencimento," +
    "pessoa.pessoa," +
    "pessoa.nome AS pesNome," +
    "pessoa.cpf," +
    "pesEndereco.endereco AS pesEndereco," +
    "pesEndereco.logradouro AS pesLogradouro," +
    "pesEndereco.cidade AS pesCidade," +
    "pesEndereco.estado AS pesEstado," +
    "pesEndereco.numero AS pesNumero," +
    "pesEndereco.bairro AS pesBairro," +
    "pesEndereco.complemento AS pesComplemento," +
    "pesEndereco.cep AS pesCep," +
    "movimento_estoque.quantidade as movQuant," +
    "movimento_estoque.tipo_movimento," +
    "movimento_estoque.tipo_transacao," +
    "movimento_estoque.data_movimento FROM movimento_estoque " +
    "INNER JOIN cadastro_unidade as uni ON movimento_estoque.unidade = uni.unidade " +
    "INNER JOIN endereco as uniEndereco ON uni.endereco = uniEndereco.endereco " +
    "INNER JOIN lote ON movimento_estoque.lote = lote.lote " +
    "INNER JOIN cadastro_pessoa as pessoa ON movimento_estoque.pessoa = pessoa.pessoa " +
    "INNER JOIN endereco as pesEndereco ON pessoa.endereco = pesEndereco.endereco ";

    private String queryUnidadeTransfer = "SELECT " +
    "movimento_estoque.movimento," +
    "uni.unidade," +
    "uni.nome AS uniNome," +
    "uni.centro," +
    "uniEndereco.endereco AS uniEndereco," +
    "uniEndereco.logradouro AS uniLogradouro," +
    "uniEndereco.cidade AS uniCidade," +
    "uniEndereco.estado AS uniEstado," +
    "uniEndereco.numero AS uniNumero," +
    "uniEndereco.bairro AS uniBairro," +
    "uniEndereco.complemento AS uniComplemento," +
    "uniEndereco.cep AS uniCep," +
    "lote.lote," +
    "lote.nome," +
    "lote.data_vencimento," +
    "movimento_estoque.quantidade as movQuant," +
    "movimento_estoque.tipo_movimento," +
    "movimento_estoque.tipo_transacao," +
    "movimento_estoque.data_movimento," +
    "uniTransfer.unidade AS uniTransfer," +
    "uniTransfer.nome AS uniTransferNome," +
    "uniTransfer.centro AS uniTransferCentro," +
    "uniTransferEndereco.endereco AS uniTransferEndereco," +
    "uniTransferEndereco.logradouro AS uniTransferLogradouro," +
    "uniTransferEndereco.cidade AS uniTransferCidade," +
    "uniTransferEndereco.estado AS uniTransferEstado," +
    "uniTransferEndereco.numero AS uniTransferNumero," +
    "uniTransferEndereco.bairro AS uniTransferBairro," +
    "uniTransferEndereco.complemento AS uniTransferComplemento," +
    "uniTransferEndereco.cep AS uniTransferCep " +
    "FROM movimento_estoque " +
    "INNER JOIN cadastro_unidade AS uni ON movimento_estoque.unidade = uni.unidade " +
    "INNER JOIN endereco AS uniEndereco ON uni.endereco = uniEndereco.endereco " +
    "INNER JOIN lote ON movimento_estoque.lote = lote.lote " +
    "INNER JOIN cadastro_unidade AS uniTransfer ON movimento_estoque.unidade_transfer = uniTransfer.unidade "
    +
    "INNER JOIN endereco as uniTransferEndereco ON uniTransfer.endereco = uniTransferEndereco.endereco ";

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
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(queryPessoa+="WHERE movimento = ?");

            st2 = conn.prepareStatement(queryUnidadeTransfer+="WHERE movimento = ?");


            st1.setInt(1, id);
            st2.setInt(1, id);
            rs1 = st1.executeQuery();
            rs2 = st2.executeQuery();
            if (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = instanciarUniEndereco(rs1);
                Unidade uni = instanciarUni(rs1, endUni);
                Endereco endPessoa = instanciarPesEndereco(rs1);
                Pessoa pes = instanciarPessoa(rs1, endPessoa);
                return new Movimento(rs1.getInt(1),uni, lote, pes, rs1.getInt(27),
                        TipoMovimento.valueOf(rs1.getString(28)),
                        TipoTransacao.valueOf(rs1.getString(29)),
                        rs1.getDate(30));
            }
            if (rs2.next()){
                Lote lote = LoteDaoJDBC.instanciarLote(rs2);
                Endereco endUni = instanciarUniEndereco(rs2);
                Unidade uni = instanciarUni(rs2, endUni);
                Endereco endUniTransfer = instanciarUniTransferEndereco(rs2);
                Unidade uniTransfer = instanciarUniTransfer(rs2, endUniTransfer);
                return new Movimento(rs2.getInt(1),uni, lote, rs2.getInt(16),
                        TipoMovimento.valueOf(rs2.getString(17)),
                        TipoTransacao.valueOf(rs2.getString(18)),
                        rs2.getDate(19), uniTransfer);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st1);
            DB.closeStatement(st2);
            DB.closeResultSet(rs1);
            DB.closeResultSet(rs2);
        }
    }

    @Override
    public List<Movimento> procurarPorUnidade(Integer unidade) {
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(queryPessoa+="WHERE uni.unidade = ?");
            st2 = conn.prepareStatement(queryUnidadeTransfer+="WHERE uni.unidade = ?");

            st1.setInt(1, unidade);
            st2.setInt(1, unidade);

            rs1 = st1.executeQuery();
            rs2 = st2.executeQuery();
            List<Movimento> list = new ArrayList<>();
            while (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = instanciarUniEndereco(rs1);
                Unidade uni = instanciarUni(rs1, endUni);
                Endereco endPessoa = instanciarPesEndereco(rs1);
                Pessoa pes = instanciarPessoa(rs1, endPessoa);
                list.add(new Movimento(rs1.getInt(1),uni, lote, pes, rs1.getInt(27),
                        TipoMovimento.valueOf(rs1.getString(28)),
                        TipoTransacao.valueOf(rs1.getString(29)),
                        rs1.getDate(30)));
            }

            while (rs2.next()){
                Lote lote = LoteDaoJDBC.instanciarLote(rs2);
                Endereco endUni = instanciarUniEndereco(rs2);
                Unidade uni = instanciarUni(rs2, endUni);
                Endereco endUniTransfer = instanciarUniTransferEndereco(rs2);
                Unidade uniTransfer = instanciarUniTransfer(rs2, endUniTransfer);
                list.add(new Movimento(rs2.getInt(1),uni, lote, rs2.getInt(16),
                        TipoMovimento.valueOf(rs2.getString(17)),
                        TipoTransacao.valueOf(rs2.getString(18)),
                        rs2.getDate(19), uniTransfer));
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

    @Override
    public List<Movimento> procurarPorLote(Integer loteId) {
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(queryPessoa+="WHERE lote.lote = ?");
            st2 = conn.prepareStatement(queryUnidadeTransfer+="WHERE lote.lote = ?");

            st1.setInt(1, loteId);
            st2.setInt(1, loteId);

            rs1 = st1.executeQuery();
            rs2 = st2.executeQuery();
            List<Movimento> list = new ArrayList<>();
            while (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = instanciarUniEndereco(rs1);
                Unidade uni = instanciarUni(rs1, endUni);
                Endereco endPessoa = instanciarPesEndereco(rs1);
                Pessoa pes = instanciarPessoa(rs1, endPessoa);
                list.add(new Movimento(rs1.getInt(1),uni, lote, pes, rs1.getInt(27),
                        TipoMovimento.valueOf(rs1.getString(28)),
                        TipoTransacao.valueOf(rs1.getString(29)),
                        rs1.getDate(30)));
            }

            while (rs2.next()){
                Lote lote = LoteDaoJDBC.instanciarLote(rs2);
                Endereco endUni = instanciarUniEndereco(rs2);
                Unidade uni = instanciarUni(rs2, endUni);
                Endereco endUniTransfer = instanciarUniTransferEndereco(rs2);
                Unidade uniTransfer = instanciarUniTransfer(rs2, endUniTransfer);
                list.add(new Movimento(rs2.getInt(1),uni, lote, rs2.getInt(16),
                        TipoMovimento.valueOf(rs2.getString(17)),
                        TipoTransacao.valueOf(rs2.getString(18)),
                        rs2.getDate(19), uniTransfer));
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

    @Override
    public List<Movimento> procurarPorUnidadeLote(Integer unidadeId, Integer loteId) {
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(queryPessoa+="WHERE lote.lote = ? && uni.unidade = ?");
            st2 = conn.prepareStatement(queryUnidadeTransfer+="WHERE lote.lote = ? && uni.unidade = ?");

            st1.setInt(1, loteId);
            st1.setInt(2, unidadeId);
            st2.setInt(1, loteId);
            st2.setInt(2, unidadeId);

            rs1 = st1.executeQuery();
            rs2 = st2.executeQuery();

            List<Movimento> list = new ArrayList<>();
            while (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = instanciarUniEndereco(rs1);
                Unidade uni = instanciarUni(rs1, endUni);
                Endereco endPessoa = instanciarPesEndereco(rs1);
                Pessoa pes = instanciarPessoa(rs1, endPessoa);
                list.add(new Movimento(rs1.getInt(1),uni, lote, pes, rs1.getInt(27),
                        TipoMovimento.valueOf(rs1.getString(28)),
                        TipoTransacao.valueOf(rs1.getString(29)),
                        rs1.getDate(30)));
            }

            while (rs2.next()){
                Lote lote = LoteDaoJDBC.instanciarLote(rs2);
                Endereco endUni = instanciarUniEndereco(rs2);
                Unidade uni = instanciarUni(rs2, endUni);
                Endereco endUniTransfer = instanciarUniTransferEndereco(rs2);
                Unidade uniTransfer = instanciarUniTransfer(rs2, endUniTransfer);
                list.add(new Movimento(rs2.getInt(1),uni, lote, rs2.getInt(16),
                        TipoMovimento.valueOf(rs2.getString(17)),
                        TipoTransacao.valueOf(rs2.getString(18)),
                        rs2.getDate(19), uniTransfer));
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

    @Override
    public List<Movimento> procurarPorDataMovimento(Date dataMovimentacao) {
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(queryPessoa+="WHERE data_movimento = ?");
            st2 = conn.prepareStatement(queryUnidadeTransfer+="WHERE data_movimento = ?");

            st1.setDate(1, new java.sql.Date(dataMovimentacao.getTime()));
            st2.setDate(1, new java.sql.Date(dataMovimentacao.getTime()));
            
            rs1 = st1.executeQuery();
            rs2 = st2.executeQuery();

            List<Movimento> list = new ArrayList<>();
            while (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = instanciarUniEndereco(rs1);
                Unidade uni = instanciarUni(rs1, endUni);
                Endereco endPessoa = instanciarPesEndereco(rs1);
                Pessoa pes = instanciarPessoa(rs1, endPessoa);
                list.add(new Movimento(rs1.getInt(1),uni, lote, pes, rs1.getInt(27),
                        TipoMovimento.valueOf(rs1.getString(28)),
                        TipoTransacao.valueOf(rs1.getString(29)),
                        rs1.getDate(30)));
            }

            while (rs2.next()){
                Lote lote = LoteDaoJDBC.instanciarLote(rs2);
                Endereco endUni = instanciarUniEndereco(rs2);
                Unidade uni = instanciarUni(rs2, endUni);
                Endereco endUniTransfer = instanciarUniTransferEndereco(rs2);
                Unidade uniTransfer = instanciarUniTransfer(rs2, endUniTransfer);
                list.add(new Movimento(rs2.getInt(1),uni, lote, rs2.getInt(16),
                        TipoMovimento.valueOf(rs2.getString(17)),
                        TipoTransacao.valueOf(rs2.getString(18)),
                        rs2.getDate(19), uniTransfer));
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

    @Override
    public List<Movimento> procurarPorPessoa(Integer pessoaId) {
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(queryPessoa+="WHERE pessoa.pessoa = ?");
            st1.setInt(1, pessoaId);

            rs1 = st1.executeQuery();

            List<Movimento> list = new ArrayList<>();

            while (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = instanciarUniEndereco(rs1);
                Unidade uni = instanciarUni(rs1, endUni);
                Endereco endPessoa = instanciarPesEndereco(rs1);
                Pessoa pes = instanciarPessoa(rs1, endPessoa);
                list.add(new Movimento(rs1.getInt(1),uni, lote, pes, rs1.getInt(27),
                        TipoMovimento.valueOf(rs1.getString(28)),
                        TipoTransacao.valueOf(rs1.getString(29)),
                        rs1.getDate(30)));
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

    @Override
    public List<Movimento> procurarPorTipoMovimento(TipoMovimento tipo) {
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(queryPessoa+="WHERE tipo_movimento = ?");
            st2 = conn.prepareStatement(queryUnidadeTransfer+="WHERE tipo_movimento = ?");

            st1.setString(1, tipo.name());
            st2.setString(1, tipo.name());

            rs1 = st1.executeQuery();
            rs2 = st2.executeQuery();

            List<Movimento> list = new ArrayList<>();
            while (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = instanciarUniEndereco(rs1);
                Unidade uni = instanciarUni(rs1, endUni);
                Endereco endPessoa = instanciarPesEndereco(rs1);
                Pessoa pes = instanciarPessoa(rs1, endPessoa);
                list.add(new Movimento(rs1.getInt(1),uni, lote, pes, rs1.getInt(27),
                        TipoMovimento.valueOf(rs1.getString(28)),
                        TipoTransacao.valueOf(rs1.getString(29)),
                        rs1.getDate(30)));
            }

            while (rs2.next()){
                Lote lote = LoteDaoJDBC.instanciarLote(rs2);
                Endereco endUni = instanciarUniEndereco(rs2);
                Unidade uni = instanciarUni(rs2, endUni);
                Endereco endUniTransfer = instanciarUniTransferEndereco(rs2);
                Unidade uniTransfer = instanciarUniTransfer(rs2, endUniTransfer);
                list.add(new Movimento(rs2.getInt(1),uni, lote, rs2.getInt(16),
                        TipoMovimento.valueOf(rs2.getString(17)),
                        TipoTransacao.valueOf(rs2.getString(18)),
                        rs2.getDate(19), uniTransfer));
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

    @Override
    public List<Movimento> listar() {
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            st1 = conn.prepareStatement(queryPessoa);

            st2 = conn.prepareStatement(queryUnidadeTransfer);

            rs1 = st1.executeQuery();
            rs2 = st2.executeQuery();
            List<Movimento> list = new ArrayList<>();
            while (rs1.next()) {
                Lote lote = LoteDaoJDBC.instanciarLote(rs1);
                Endereco endUni = instanciarUniEndereco(rs1);
                Unidade uni = instanciarUni(rs1, endUni);
                Endereco endPessoa = instanciarPesEndereco(rs1);
                Pessoa pes = instanciarPessoa(rs1, endPessoa);
                list.add(new Movimento(rs1.getInt(1),uni, lote, pes, rs1.getInt(27),
                        TipoMovimento.valueOf(rs1.getString(28)),
                        TipoTransacao.valueOf(rs1.getString(29)),
                        rs1.getDate(30)));
            }

            while (rs2.next()){
                Lote lote = LoteDaoJDBC.instanciarLote(rs2);
                Endereco endUni = instanciarUniEndereco(rs2);
                Unidade uni = instanciarUni(rs2, endUni);
                Endereco endUniTransfer = instanciarUniTransferEndereco(rs2);
                Unidade uniTransfer = instanciarUniTransfer(rs2, endUniTransfer);
                list.add(new Movimento(rs2.getInt(1),uni, lote, rs2.getInt(16),
                        TipoMovimento.valueOf(rs2.getString(17)),
                        TipoTransacao.valueOf(rs2.getString(18)),
                        rs2.getDate(19), uniTransfer));
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

    private Pessoa instanciarPessoa(ResultSet rs, Endereco endereco) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getInt("pessoa"));
        pessoa.setNome(rs.getString("pesNome"));
        pessoa.setCpf(rs.getString("cpf"));
        pessoa.setEndereco(endereco);
        return pessoa;
    }

    private Endereco instanciarPesEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("pesEndereco"));
        endereco.setLogradouro(rs.getString("pesLogradouro"));
        endereco.setCidade(rs.getString("pesCidade"));
        endereco.setEstado(rs.getString("pesEstado"));
        endereco.setNumero(rs.getInt("pesNumero"));
        endereco.setBairro(rs.getString("pesBairro"));
        endereco.setComplemento(rs.getString("pesComplemento"));
        endereco.setCep(rs.getString("pesCep"));
        return endereco;
    }

    private Unidade instanciarUni(ResultSet rs,Endereco endereco) throws SQLException {
        Unidade unidade = new Unidade();
        unidade.setId(rs.getInt("unidade"));
        unidade.setNome(rs.getString("uniNome"));
        unidade.setCentro(rs.getBoolean("centro"));
        unidade.setEndereco(endereco);
        return unidade;
}

    private Endereco instanciarUniEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("uniEndereco"));
        endereco.setLogradouro(rs.getString("uniLogradouro"));
        endereco.setCidade(rs.getString("uniCidade"));
        endereco.setEstado(rs.getString("uniEstado"));
        endereco.setNumero(rs.getInt("uniNumero"));
        endereco.setBairro(rs.getString("uniBairro"));
        endereco.setComplemento(rs.getString("uniComplemento"));
        endereco.setCep(rs.getString("uniCep"));
        return endereco;
    }

    private Unidade instanciarUniTransfer(ResultSet rs,Endereco endereco) throws SQLException {
        Unidade unidade = new Unidade();
        unidade.setId(rs.getInt("uniTransfer"));
        unidade.setNome(rs.getString("uniTransferNome"));
        unidade.setCentro(rs.getBoolean("uniTransferCentro"));
        unidade.setEndereco(endereco);
        return unidade;
}

    private Endereco instanciarUniTransferEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("uniTransferEndereco"));
        endereco.setLogradouro(rs.getString("uniTransferLogradouro"));
        endereco.setCidade(rs.getString("uniTransferCidade"));
        endereco.setEstado(rs.getString("uniTransferEstado"));
        endereco.setNumero(rs.getInt("uniTransferNumero"));
        endereco.setBairro(rs.getString("uniTransferBairro"));
        endereco.setComplemento(rs.getString("uniTransferComplemento"));
        endereco.setCep(rs.getString("uniTransferCep"));
        return endereco;
    }

}
