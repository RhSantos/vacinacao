package app;

import java.io.IOException;
import java.util.Scanner;

import model.services.EstoqueSDao;

public class Relatorios {
    public static void estoqueVacina() throws InterruptedException, IOException{
        System.out.println();
        System.out.println();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        try (Scanner sc = new Scanner(System.in)) {
            Integer id = 0;
            String idS = "";
            do{
                try{
                    System.out.print("Digite o ID da Unidade para Listar os Dados de Estoque Disponível: ");
                    idS = sc.nextLine();
                    id = Integer.parseInt(idS);
                }catch(NumberFormatException e){
                    if(idS.equals("0")){
                        System.out.println("Obrigado por usar nosso sistema!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        sc.close();
                        return;
                    }
                    else if(idS.equals("-")) UI.menuRelatorios();
                    else {
                        System.out.println("A Opção deve ser apenas Números inteiros!");
                        UI.sleep(2.5);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                }
            }while(id <= 0);
            EstoqueSDao.listarPrint(id.toString());
        }
        
    }
    public static void aplicacao(){}
    public static void resumoAplicacao(){}
    public static void esquemaVacinalImcompleto(){}
}
