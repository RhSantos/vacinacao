package model.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.dao.DaoFactory;
import model.dao.VacinadoDao;
import model.entities.Pessoa;
import model.entities.Vacinado;

public class VacinadoSDao {

    public static void cadastrar(Vacinado vacinado) {
        VacinadoDao vacinadoDao = DaoFactory.createVacinadoDao();
        vacinadoDao.inserir(vacinado);
    }

    private static List<Vacinado> listar(String filtro){
        VacinadoDao vacinadoDao = DaoFactory.createVacinadoDao();
        if(filtro.matches("^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$") == true) {
            return vacinadoDao.procurarPorUnidade(Integer.parseInt(filtro));
        }
        return null;
    }

    public static void listarPrint(String filtro){
        List<Vacinado> vacinados = listar(filtro);
        if(vacinados == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();
        
        for (Vacinado vacinado : vacinados) {
            System.out.println(vacinado);
        }
    }

    private static List<Vacinado> listarContagem(String filtro){
        VacinadoDao vacinadoDao = DaoFactory.createVacinadoDao();
        if(filtro.matches("^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$") == true) {
            return vacinadoDao.procurarPorUnidade(Integer.parseInt(filtro));
        }
        return null;
    }

    public static void listarPrintContagem(String filtro){
        List<Vacinado> vacinados = listarContagem(filtro);
        if(vacinados == null){
            System.out.println("Filtro Inválido!");
            return;
        } 
        System.out.println();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(System.currentTimeMillis());
        String dataStr = sdf.format(data);
        
        
        String[] campos = dataStr.split("/");

        String dia = campos[0];
        String mes = campos[1];
        String ano = campos[2];

        Date semana = new Date(data.getTime()-604800000L);



        Integer diaCount = 0;
        Integer semanaCount = 0;
        Integer mesCount = 0;
        Integer totalCount = 0;

        for (int i = 1; i <= 3; i++) {
            diaCount = 0;
            semanaCount = 0;
            mesCount = 0;
            totalCount = 0;
            for (Vacinado vacinado : vacinados) {
                String vacData = sdf.format(vacinado.getDataVacinacao());
                String[] vacDataCampos = vacData.split("/");
                if(vacinado.getDose() == i && dia.equals(vacDataCampos[0]) && mes.equals(vacDataCampos[1]) && ano.equals(vacDataCampos[2])){
                    diaCount++;
                }
                if(vacinado.getDose() == i && mes.equals(vacDataCampos[1]) && ano.equals(vacDataCampos[2])){
                    mesCount++;
                }
                
                if(vacinado.getDose() == i && vacinado.getDataVacinacao().getTime() <= data.getTime() && vacinado.getDataVacinacao().getTime() >= semana.getTime()){
                    semanaCount++;
                }
                if(vacinado.getDose() == i) totalCount++;
                
            }
            System.out.println("Dose Nº "+i);
            System.out.println();
            System.out.println("###############################");
            System.out.println("\nQuantidade de Vacinas Aplicadas no Dia: "+diaCount);
            System.out.println("Quantidade de Vacinas Aplicadas na Semana: "+semanaCount);
            System.out.println("Quantidade de Vacinas Aplicadas no Mes: "+mesCount);
            System.out.println("Quantidade de Vacinas Aplicadas Totais: "+totalCount);
            System.out.println("\n###############################");
            System.out.println();
        }

    }

    private static List<Vacinado> listarVacinalIncompleto(String filtro){
        VacinadoDao vacinadoDao = DaoFactory.createVacinadoDao();
        if(filtro == "") return vacinadoDao.listar();
        if(filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$") == true) {
            return vacinadoDao.listar()
                .stream()
                .filter
                    (e -> e.getPessoa().getEndereco().getBairro().toLowerCase().startsWith(filtro.toLowerCase())).toList();
        }
        return null;
    }

    public static void listarVacinalIncompletoPrint(String filtro){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Vacinado> vacinados = listarVacinalIncompleto(filtro);
        if(vacinados == null){
            return;
        }

        Set<Pessoa> pessoaVacinada = new HashSet<>();

        List<Vacinado> vacinadosCompleto = new ArrayList<>();

        for (int i = 0; i < vacinados.size(); i++) {
            if(vacinados.get(i).getDose() >= 3){
                pessoaVacinada.add(vacinados.get(i).getPessoa());
            }
        }
             
        for (int i = 0; i < vacinados.size(); i++) {
            if(pessoaVacinada.contains(vacinados.get(i).getPessoa())){
                vacinadosCompleto.add(vacinados.get(i));
            }
        }

        vacinados.removeAll(vacinadosCompleto);

        System.out.println();
        Set<Pessoa> pessoasImcompletas = new HashSet<>();
        System.out.println("Lista de Pessoas com o Esquema Vacinal Incompleto:");
        System.out.println();
        for (Vacinado vacinado : vacinados) {
            long dateTime = 0L;
            for (int i = 0; i < vacinados.size(); i++) {
                if(vacinado.getDataVacinacao().getTime() > dateTime){
                    dateTime = vacinado.getDataVacinacao().getTime();
                }
            }
            if(!pessoasImcompletas.contains(vacinado.getPessoa())){
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println();
                System.out.println("Nome: "+vacinado.getPessoa().getNome());
                System.out.println("CPF: "+vacinado.getPessoa().getCpf());
                System.out.println("Data da Última Vacinação: "+sdf.format(new java.util.Date(dateTime)));
                System.out.println();
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
                pessoasImcompletas.add(vacinado.getPessoa());
            }
        }
    }

    public static List<Vacinado> procurarPorPessoa(Integer pessoa){
        VacinadoDao vacinadoDao = DaoFactory.createVacinadoDao();
        return vacinadoDao.procurarPorPessoa(pessoa);
    }
}
