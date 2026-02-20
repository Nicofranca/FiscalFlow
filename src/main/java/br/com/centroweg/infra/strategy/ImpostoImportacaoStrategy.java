package br.com.centroweg.infra.strategy;

import br.com.centroweg.infra.CalculadoraImpostosStrategyInt;

public class ImpostoImportacaoStrategy implements CalculadoraImpostosStrategyInt {

    Double taxa = 0.8;

    @Override
    public Double calcularImposto(Double valorBase) {

        Double totalImposto = valorBase*taxa;

        return totalImposto;
    }
}
