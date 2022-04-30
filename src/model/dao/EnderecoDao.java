package model.dao;

import java.util.List;
import model.entities.Endereco;

public interface EnderecoDao {
    void inserir(Endereco endereco);
    void atualizar(Endereco endereco);
    void deletar(Integer id);
    Endereco procurarPorId(Integer id);
    List<Endereco> listar();
}
