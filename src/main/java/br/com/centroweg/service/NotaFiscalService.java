package br.com.centroweg.service;

import br.com.centroweg.infra.CalculadoraImpostosStrategy;

public class NotaFiscalService {

    CalculadoraImpostosStrategy calculadoraImpostosStrategy;

    public NotaFiscalService(CalculadoraImpostosStrategy calculadoraImpostosStrategy){
        this.calculadoraImpostosStrategy = calculadoraImpostosStrategy;
    }
}
