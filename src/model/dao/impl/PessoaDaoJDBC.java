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
import model.dao.PessoaDao;
import model.entities.Endereco;
import model.entities.Pessoa;

public class PessoaDaoJDBC implements PessoaDao {

    private Connection conn;

    public PessoaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Pessoa pessoa) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO cadastro_pessoa " +
                            "(nome,cpf,endereco) " +
                            "VALUES " +
                            "(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, pessoa.getNome());
            st.setString(2, pessoa.getCpf());
            st.setInt(3, pessoa.getEndereco().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    pessoa.setId(id);
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
    public void atualizar(Pessoa pessoa) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE cadastro_pessoa " +
                            "SET " +
                            "nome = ?,cpf = ?,endereco = ? " +
                            "WHERE pessoa = ?");

            st.setString(1, pessoa.getNome());
            st.setString(2, pessoa.getCpf());
            st.setInt(3, pessoa.getEndereco().getId());
            st.setInt(4, pessoa.getId());

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
                    "DELETE FROM cadastro_pessoa " +
                            "WHERE pessoa = ?");

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Pessoa procurarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * " +
                            "FROM cadastro_pessoa INNER JOIN endereco " +
                            "ON cadastro_pessoa.endereco = endereco.endereco " +
                            "WHERE cadastro_pessoa.pessoa  = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Endereco endereco = instanciarEndereco(rs);
                Pessoa pessoa = instanciarPessoa(rs, endereco);
                return pessoa;
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
    public Pessoa procurarPorCpf(String cpf) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * " +
                            "FROM cadastro_pessoa INNER JOIN endereco " +
                            "ON cadastro_pessoa.endereco = endereco.endereco " +
                            "WHERE cadastro_pessoa.cpf  = ?");
            st.setString(1, cpf);
            rs = st.executeQuery();
            if (rs.next()) {
                Endereco endereco = instanciarEndereco(rs);
                Pessoa pessoa = instanciarPessoa(rs, endereco);
                return pessoa;
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
    public List<Pessoa> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT cadastro_pessoa.pessoa,cadastro_pessoa.nome,cadastro_pessoa.cpf," +
                            "endereco.* FROM cadastro_pessoa " +
                            "INNER JOIN endereco ON cadastro_pessoa.endereco = endereco.endereco");

            rs = st.executeQuery();
            List<Pessoa> list = new ArrayList<>();
            while (rs.next()) {
                Endereco endereco = instanciarEndereco(rs);
                Pessoa pessoa = instanciarPessoa(rs, endereco);
                list.add(pessoa);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Pessoa instanciarPessoa(ResultSet rs, Endereco endereco) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getInt("endereco"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setCpf(rs.getString("cpf"));
        pessoa.setEndereco(endereco);
        return pessoa;
    }

    private Endereco instanciarEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("endereco"));
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
