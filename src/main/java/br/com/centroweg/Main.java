package br.com.centroweg;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    private static String URL = "jdbc:mysql://localhost:3306/fiscalflow?useSSL=false&serverTimezone=UTC";
    private static String USER = "root";
    private static String PASSWORD = "mysqlPW";

    public static void main(String[] args) {
        System.out.println("\n\n");
        System.out.println("\n+---------------------------------------+");
        System.out.println("|       FISCAL FLOW ENTERPRISE          |");
        System.out.println("|       Sistema de Gestão v1.0          |");
        System.out.println("+---------------------------------------+");

        while (true) {
            System.out.println("\n+---------------------------------------+");
            System.out.println("|            MENU PRINCIPAL             |");
            System.out.println("+---------------------------------------+");
            System.out.println("|[1] Emitir Nova Nota Fiscal            |");
            System.out.println("|[2] Listar Todas as Notas              |");
            System.out.println("|[3] Sair do Sistema                    |");
            System.out.println("+---------------------------------------+");
            System.out.print("Digite a opção desejada: ");

            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                processarEmissao();
            } else if (opcao.equals("2")) {
                listarTodasNotas();
            } else if (opcao.equals("3")) {
                System.out.println("\n Encerrando sistema");
                break;
            } else {
                System.out.println("\n Opção Inválida.");
            }
        }
    }

    private static void processarEmissao() {
        System.out.println("\n=========================================");
        System.out.println("         NOVA EMISSÃO DE NOTA           ");
        System.out.println("=========================================");

        System.out.print("Descrição do Item: ");
        String descricao = scanner.nextLine();

        if (descricao.trim().isEmpty()) {
            System.out.println("A descrição não pode ser vazia");
            return;
        }

        System.out.print("Valor Base (R$): ");
        try {
            double valorBase = Double.parseDouble(scanner.nextLine());

            if (valorBase <= 0) {
                System.out.println("O valor deve ser positivo");
                return;
            }

            System.out.println("\nSelecione a Categoria Tributária:");
            System.out.println("[1] Serviço");
            System.out.println("[2] Produto");
            System.out.println("[3] Exportação");
            System.out.print("Opção: ");
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
                System.out.println("Opção de categoria inválida");
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

            salvarNoBancoDeDados(descricao, valorBase, imposto, total, tipoEnum);

        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido");
        }
    }

    private static double calcularImposto(double valor, String tipo) {
        if (tipo.equals("SERVICO")) return valor * 0.05;
        if (tipo.equals("PRODUTO")) return valor * 0.17;
        if (tipo.equals("EXPORTACAO")) return 0.0;
        return 0.0;
    }

    private static void salvarNoBancoDeDados(String descricao, double valorBase, double imposto, double total, String tipoEnum) {
        String sql = "INSERT INTO notas_fiscais (descricao, valor_base, valor_imposto, valor_total, tipo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descricao);
            stmt.setDouble(2, valorBase);
            stmt.setDouble(3, imposto);
            stmt.setDouble(4, total);

            int tipoNumerico = switch (tipoEnum) {
                case "SERVICO" -> 1;
                case "PRODUTO" -> 2;
                case "EXPORTACAO" -> 3;
                default -> 0;
            };
            stmt.setInt(5, tipoNumerico);

            stmt.executeUpdate();
            System.out.println("[OK] SUCESSO! Nota Fiscal gravada no sistema");

        } catch (SQLException e) {
            System.out.println("[X] ERRO ao salvar: " + e.getMessage());
        }
    }

    private static void listarTodasNotas() {
        System.out.println("\n=================================================================================");
        System.out.println("                         RELATÓRIO DE NOTAS FISCAIS                              ");
        System.out.println("=================================================================================");
        System.out.printf("%-4s | %-18s | %-12s | %-10s | %-10s | %-10s%n",
                "ID", "DESCRIÇÃO", "TIPO", "BASE", "IMPOSTO", "TOTAL");
        System.out.println("---------------------------------------------------------------------------------");

        String sql = "SELECT * FROM notas_fiscais";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean registroEncontrado = false;

            while (rs.next()) {
                registroEncontrado = true;
                int id = rs.getInt("id");
                String desc = rs.getString("descricao");
                double base = rs.getDouble("valor_base");
                double imposto = rs.getDouble("valor_imposto");
                double total = rs.getDouble("valor_total");
                String tipoTexto = rs.getString("tipo");


                System.out.printf("%-4d | %-18.18s | %-12s | R$%8.2f | R$%8.2f | R$%8.2f%n",
                        id, desc, tipoTexto, base, imposto, total);
            }

            if (!registroEncontrado) {
                System.out.println("Nenhum registro encontrado no banco de dados.");
            }

        } catch (SQLException e) {
            System.out.println("erro ao listar dados: " + e.getMessage());
        }
    }
}