package br.com.centroweg;

import br.com.centroweg.controller.NotaFiscalController;
import br.com.centroweg.infra.repository.NotaFiscalRepository;
import br.com.centroweg.service.NotaFiscalService;
import br.com.centroweg.view.NotaFiscalView;

public class Main {
    public static void main(String[] args) {

        NotaFiscalRepository notaFiscalRepository = new NotaFiscalRepository();

        NotaFiscalService notaFiscalService = new NotaFiscalService(notaFiscalRepository);

        NotaFiscalView notaFiscalView = new NotaFiscalView();

        NotaFiscalController notaFiscalController = new NotaFiscalController(notaFiscalService, notaFiscalView);

        notaFiscalController.iniciar();

    }
}