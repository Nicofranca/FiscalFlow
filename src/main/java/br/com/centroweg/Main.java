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
        // Cabeçalho do Sistema
        System.out.println("\n\n");
        System.out.println("\n+---------------------------------------+");
        System.out.println("|       FISCAL FLOW ENTERPRISE          |");
        System.out.println("|       Sistema de Gestão v1.0          |");
        System.out.println("+---------------------------------------+");

        while (true) {
            System.out.println("\n+---------------------------------------+");
            System.out.println("|            MENU PRINCIPAL             |");
            System.out.println("+---------------------------------------+");
            System.out.println("|  [1] Emitir Nova Nota Fiscal          |");
            System.out.println("|  [2] Sair do Sistema                  |");
            System.out.println("+---------------------------------------+");
            System.out.print(">> Digite a opção desejada: ");

            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                processarEmissao();
            } else if (opcao.equals("2")) {
                System.out.println("\n[!] Encerrando conexão");
                break;
            } else {
                System.out.println("\n[!] Opção Inválida Tente novamente");
            }
        }
    }

    private static void processarEmissao() {
        System.out.println("\n=========================================");
        System.out.println("         NOVA EMISSÃO DE NOTA           ");
        System.out.println("=========================================");

        System.out.print(">> Descrição do Item: ");
        String descricao = scanner.nextLine();

        if (descricao.trim().isEmpty()) {
            System.out.println("[X] ERRO: A descrição não pode ser vazia");
            return;
        }

        System.out.print(">> Valor Base (R$): ");
        try {
            double valorBase = Double.parseDouble(scanner.nextLine());

            if (valorBase <= 0) {
                System.out.println("[X] ERRO: O valor deve ser positivo");
                return;
            }

            System.out.println("\nSelecione a Categoria Tributária:");
            System.out.println("   [1] Serviço");
            System.out.println("   [2] Produto");
            System.out.println("   [3] Exportação");
            System.out.print(">> Opção: ");
            String opcao = scanner.nextLine();

            String tipoEnum = "";
            String labelImposto = "";

            if (opcao.equals("1")) {
                tipoEnum = "SERVICO";
                labelImposto = "5%";
            } else if (opcao.equals("2")) {
                tipoEnum = "PRODUTO";
                labelImposto = "17%";
            } else if (opcao.equals("3")) {
                tipoEnum = "EXPORTACAO";
                labelImposto = "ISENTO";
            } else {
                System.out.println("[X] Opção de categoria inválida");
                return;
            }

            double imposto = calcularImposto(valorBase, tipoEnum);
            double total = valorBase + imposto;

            System.out.println("\n");
            System.out.println("-----------------------------------------");
            System.out.println("        COMPROVANTE DE CÁLCULO           ");
            System.out.println("-----------------------------------------");
            System.out.printf(" ITEM:      %-25s %n", descricao.toUpperCase());
            System.out.printf(" TIPO:      %-25s %n", tipoEnum);
            System.out.println("-----------------------------------------");
            System.out.printf(" VALOR BASE:        R$ %10.2f %n", valorBase);
            System.out.printf(" TRIBUTO %-10s R$ %10.2f %n", "(" + labelImposto + "):", imposto);
            System.out.println("-----------------------------------------");
            System.out.printf(" TOTAL A PAGAR:     R$ %10.2f %n", total);
            System.out.println("-----------------------------------------");

            System.out.println("\n[INFO] Conectando ao Banco de Dados");
            salvarNoBancoDeDados(descricao, valorBase, imposto, total, tipoEnum);

        } catch (NumberFormatException e) {
            System.out.println("[X] ERRO: Por favor, digite um número válido (Ex: 1500.50)");
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

            System.out.println("[OK] SUCESSO! Nota Fiscal gravada no sistema");

        } catch (SQLException e) {
            System.out.println("\n[!!!] FALHA CRÍTICA DE SISTEMA [!!!]");
            System.out.println("Erro ao persistir dados: " + e.getMessage());
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