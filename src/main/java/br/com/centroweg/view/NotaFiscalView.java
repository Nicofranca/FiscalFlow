package br.com.centroweg.view;

import java.util.Scanner;

public class NotaFiscalView {

    Scanner scINT = new Scanner(System.in);
    Scanner scSTR = new Scanner(System.in);

    public int menu(){
        System.out.println("\n\n");
        System.out.println("\n+---------------------------------------+");
        System.out.println("|       FISCAL FLOW ENTERPRISE          |");
        System.out.println("|       Sistema de Gestão v1.0          |");
        System.out.println("+---------------------------------------+");

        System.out.println("\n+---------------------------------------+");
        System.out.println("|            MENU PRINCIPAL             |");
        System.out.println("+---------------------------------------+");
        System.out.println("|  [1] Emitir Nova Nota Fiscal          |");
        System.out.println("|  [2] Sair do Sistema                  |");
        System.out.println("+---------------------------------------+");
        System.out.print(">> Digite a opção desejada: ");

        return scINT.nextInt();

        }

        public String descricao(){
            System.out.println("\n=========================================");
            System.out.println("         NOVA EMISSÃO DE NOTA           ");
            System.out.println("=========================================");

            System.out.print(">> Descrição do Item: ");

            return scSTR.nextLine();

        }

        public int tipoImposto(){
            System.out.println("\nSelecione a Categoria Tributária:");
            System.out.println("   [1] Serviço");
            System.out.println("   [2] Produto");
            System.out.println("   [3] Exportação");
            System.out.print(">> Opção: ");

            return scINT.nextInt();
        }
    }



