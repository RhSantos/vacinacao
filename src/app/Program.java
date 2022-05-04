package app;

import java.io.IOException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.dao.UnidadeDao;
import model.entities.Endereco;
import model.entities.Unidade;
import model.services.UnidadeSDao;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
        // UnidadeDao uniDao = DaoFactory.createUnidadeDao();
        // System.out.println(uniDao.listaCD());
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Endereco endereco = new Endereco
            (4,"Rua Palácio Central", "Canoas", "RS", 
                23431, "Planalto", "93434-53");
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Cadastro Unidade");
        System.out.println("       1 - Cadastrar");
        System.out.println("       2 - Listar");
        int n = sc.nextInt();
        sc.nextLine();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        if(n == 1){
            System.out.println("===CADASTRO DE UNIDADES===");
            System.out.print("Digite o nome da Unidade: ");
            String nome = sc.nextLine();
            System.out.print("É um Centro de Distribuição? (s/n): ");
            Character centro = sc.next().charAt(0);
            centro = Character.toLowerCase(centro);
            Unidade unidade = new Unidade(nome,centro == 's');
            EnderecoDao endDao = DaoFactory.createEnderecoDao();
            endDao.inserir(unidade.getEndereco());
            UnidadeSDao.cadastrar(unidade);
        }
        sc.close();


    }
}
