package br.com.centroweg.controller;

import br.com.centroweg.domain.NotaFiscal;
import br.com.centroweg.service.NotaFiscalService;
import br.com.centroweg.view.NotaFiscalView;

public class NotaFiscalController {

    NotaFiscalService service;
    NotaFiscalView view;

    public NotaFiscalController(NotaFiscalService service, NotaFiscalView view){
        this.service = service;
        this.view = view;
    }

    public void iniciar(){

        int opcao;

        do {

            opcao = view.menu();

            switch (opcao){
                case 1 :
                    String descricao = view.descricao();

                    double valorBase = view.valorBase();

                    int tipoImposto = view.tipoImposto();

                    NotaFiscal notaFiscal = service.calcularImposto(descricao, valorBase, opcao);

                    System.out.println(view.mostrarResultado(notaFiscal));
                    System.out.println("Descrição do pagamento: "+ notaFiscal.getDescricao());
                    System.out.println("Tipo de Imposto: "+service.findByIdTipo(notaFiscal));
                    System.out.println("Data: "+notaFiscal.getData_emissao());

                    break;
                case 2:
                        return;
            }
        }while (true);
    }

}
