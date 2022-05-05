package app;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.dao.UnidadeDao;
import model.dao.VacinadoDao;
import model.entities.Endereco;
import model.entities.Unidade;
import model.entities.Vacinado;
import model.services.UnidadeSDao;
import model.services.VacinadoSDao;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        // UI.menu();
        // VacinadoDao vacinadoDao = DaoFactory.createVacinadoDao();
        // System.out.println();
        // List<Vacinado> vacinados = vacinadoDao.procurarPorUnidade(2);
        // for (Vacinado vacinado : vacinados) {
        //     System.out.println(vacinado);
        // }
        VacinadoSDao.listarPrintContagem("2");

    }
}
