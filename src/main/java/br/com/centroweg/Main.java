package br.com.centroweg;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    private static String URL = "jdbc:mysql://localhost:3306/fiscalflow?useSSL=false&serverTimezone=UTC";
    private static String USER = "root";
    private static String PASSWORD = "mysqlPW";
    public static void main(String[] args) {

        System.out.println("=== ERP FISCAL ===");

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
            }else {
                System.out.println("Opcão Invalida");
            }
        }
    }

    private static void processarEmissao() {
        System.out.println("\n=== NOVA EMISSÃO ===");

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        if (descricao.trim().isEmpty()) {
            System.out.println("ERRO: Descrição vazia");
            return;
        }

        System.out.print("Valor Base (R$): ");
        try {
            double valorBase = Double.parseDouble(scanner.nextLine());

            if (valorBase <= 0) {
                System.out.println("[ERRO]: Valor deve ser positivo");
                return;
            }

            System.out.println("Tipo: [1] Serviço | [2] Produto | [3] Exportação");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            String tipoEnum = "";
            if (opcao.equals("1")) tipoEnum = "SERVICO";
            else if (opcao.equals("2")) tipoEnum = "PRODUTO";
            else if (opcao.equals("3")) tipoEnum = "EXPORTACAO";
            else {
                System.out.println("Opção inválida");
                return;
            }

            double imposto = calcularImposto(valorBase, tipoEnum);
            double total = valorBase + imposto;

            System.out.println("===============================");
            System.out.println("CALCULADO COM SUCESSO:");
            System.out.println("Imposto: R$ " + imposto);
            System.out.println("Total:   R$ " + total);
            System.out.println("===============================");

            System.out.println("=== Iniciando persistência automática no Banco de Dados ===");
            salvarNoBancoDeDados(descricao, valorBase, imposto, total, tipoEnum);

        } catch (NumberFormatException e) {
            System.out.println("[ERRO]: Digite um número válido");
        }
    }

    private static double calcularImposto(double valor, String tipo) {
        if (tipo.equals("SERVICO")) return valor * 0.05;
        if (tipo.equals("PRODUTO")) return valor * 0.17;
        if (tipo.equals("EXPORTACAO")) return 0.0;
        return 0.0;
    }

    private static void salvarNoBancoDeDados(String descricao, double valorBase, double imposto, double total, String tipoEnum) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO notas_fiscais (" +
                    "descricao, " +
                    "valor_base, " +
                    "valor_imposto, " +
                    "valor_total, " +
                    "tipo) " +
                    "VALUES (?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, descricao);
            stmt.setDouble(2, valorBase);
            stmt.setDouble(3, imposto);
            stmt.setDouble(4, total);
            int tipoNumerico = 0;
            if (tipoEnum.equals("SERVICO")) tipoNumerico = 1;
            else if (tipoEnum.equals("PRODUTO")) tipoNumerico = 2;
            else if (tipoEnum.equals("EXPORTACAO")) tipoNumerico = 3;

            stmt.setInt(5, tipoNumerico);

            stmt.executeUpdate();
            System.out.println("SUCESSO: Dados salvos no MySQL");

        } catch (SQLException e) {
            System.out.println("ERRO CRÍTICO DE BANCO: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}