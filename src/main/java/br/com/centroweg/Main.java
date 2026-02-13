package br.com.centroweg;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Scanner scanner = new Scanner(System.in);

    private static String URL = "jdbc:mysql://localhost:3306/fiscalflow?useSSL=false&serverTimezone=UTC";
    private static String USER = "root";
    private static String PASSWORD = "mysqlPW";
    public static void main(String[] args) {

        System.out.println("=== ERP FISCAL LEGADO ===");

        while (true){
            System.out.println("\n --- MENU PRINCIPAL ---");
            System.out.println("[1] Emitir Nota Fiscal");
            System.out.println("[2] Sair");
            System.out.println("Opcão: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")){
                processarEmissao();
            }else if (opcao.equals("2")){
                System.out.println("Saindo do Sistema");
                break;
            }
        }
    }

    private static void processarEmissao() {
    }
    }

}