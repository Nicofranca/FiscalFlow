package br.com.centroweg.service;

import br.com.centroweg.infra.CalculadoraImpostosStrategyInt;

public class NotaFiscalService {

    CalculadoraImpostosStrategyInt calculadoraImpostosStrategyInt;

    public NotaFiscalService(CalculadoraImpostosStrategyInt calculadoraImpostosStrategyInt){
        this.calculadoraImpostosStrategyInt = calculadoraImpostosStrategyInt;
    }



}
