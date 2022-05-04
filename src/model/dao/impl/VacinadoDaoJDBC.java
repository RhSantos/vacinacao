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
import model.dao.VacinadoDao;
import model.entities.Endereco;
import model.entities.Lote;
import model.entities.Movimento;
import model.entities.Pessoa;
import model.entities.Unidade;
import model.entities.Vacinado;
import model.enums.TipoMovimento;
import model.enums.TipoTransacao;

public class VacinadoDaoJDBC implements VacinadoDao {

    private Connection conn;

    private String queryVacinado = "SELECT " +
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
    "movimento_estoque.data_movimento,"+
    "vacinados.dose FROM vacinados " +
    "INNER JOIN movimento_estoque ON vacinados.movimento = movimento_estoque.movimento " +
    "INNER JOIN cadastro_unidade as uni ON movimento_estoque.unidade = uni.unidade " +
    "INNER JOIN endereco as uniEndereco ON uni.endereco = uniEndereco.endereco " +
    "INNER JOIN lote ON movimento_estoque.lote = lote.lote " +
    "INNER JOIN cadastro_pessoa as pessoa ON movimento_estoque.pessoa = pessoa.pessoa " +
    "INNER JOIN endereco as pesEndereco ON pessoa.endereco = pesEndereco.endereco ";
    private String order = "ORDER BY dose";
    
    public VacinadoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Vacinado vacinado) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO vacinados " +
                    "(pessoa,unidade,lote,movimento,data_aplicacao) " +
                    "VALUES " +
                    "(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, vacinado.getPessoa().getId());
            st.setInt(2, vacinado.getUnidade().getId());
            st.setInt(3, vacinado.getLote().getLote());
            st.setInt(4, vacinado.getMovimento().getId());
            st.setDate(5, new java.sql.Date(vacinado.getDataVacinacao().getTime()));

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    vacinado.setDose(id);
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
    public void atualizar(Vacinado vacinado) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE vacinados " +
                            "SET " +
                            "unidade = ?,lote = ?," +
                            "movimento = ?,data_aplicacao = ? "+
                    "WHERE dose = ? && pessoa = ?");

            st.setInt(1, vacinado.getUnidade().getId());
            st.setInt(2, vacinado.getLote().getLote());
            st.setInt(3, vacinado.getMovimento().getId());
            st.setDate(4, new java.sql.Date(vacinado.getDataVacinacao().getTime()));

            st.setInt(5, vacinado.getDose());
            st.setInt(6, vacinado.getPessoa().getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletar(Integer dose,Integer pessoa) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM vacinados " +
                            "WHERE dose = ? && pessoa = ?");

            st.setInt(1, dose);
            st.setInt(1, pessoa);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        } 
    }

    @Override
    public Vacinado procurarPorDosePessoa(Integer dose, Integer pessoa) {
        PreparedStatement st = null;

        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                queryVacinado+="WHERE dose = ? && pessoa.pessoa = ? "+order);

            st.setInt(1, dose);
            st.setInt(2, pessoa);

            rs = st.executeQuery();

            if(rs.next()) {
                Movimento movimento = instanciarMovimento(rs);
                return new Vacinado
                    (rs.getInt(31), 
                        movimento.getPessoa(), movimento.getUnidade(), 
                        movimento.getLote(), movimento, movimento.getDataMovimento());
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vacinado> procurarPorPessoa(Integer pessoa) {
        PreparedStatement st = null;

        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                queryVacinado+="WHERE pessoa.pessoa = ? "+order);
            st.setInt(1, pessoa);
            rs = st.executeQuery();

            List<Vacinado> list = new ArrayList<>();
            while (rs.next()) {
                Movimento movimento = instanciarMovimento(rs);
                list.add(new Vacinado
                    (rs.getInt(31), 
                        movimento.getPessoa(), movimento.getUnidade(), 
                        movimento.getLote(), movimento, movimento.getDataMovimento()));
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vacinado> listar() {
        PreparedStatement st = null;

        ResultSet rs = null;

        try {

            st = conn.prepareStatement(queryVacinado+="ORDER BY pessoa,dose");

            rs = st.executeQuery();

            List<Vacinado> list = new ArrayList<>();
            while (rs.next()) {
                Movimento movimento = instanciarMovimento(rs);
                list.add(new Vacinado
                    (rs.getInt(31), 
                        movimento.getPessoa(), movimento.getUnidade(), 
                        movimento.getLote(), movimento, movimento.getDataMovimento()));
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Movimento instanciarMovimento(ResultSet rs) throws SQLException{
        Lote lote = LoteDaoJDBC.instanciarLote(rs);
        Endereco endUni = MovimentoDaoJDBC.instanciarUniEndereco(rs);
        Unidade uni = MovimentoDaoJDBC.instanciarUni(rs, endUni);
        Endereco endPessoa = MovimentoDaoJDBC.instanciarPesEndereco(rs);
        Pessoa pes = MovimentoDaoJDBC.instanciarPessoa(rs, endPessoa);
        return new Movimento(rs.getInt(1),uni, lote, pes, rs.getInt(27),
                TipoMovimento.valueOf(rs.getString(28)),
                TipoTransacao.valueOf(rs.getString(29)),
                rs.getDate(30));
    }
    
}
