package br.com.centroweg.infra.strategy;

import br.com.centroweg.infra.CalculadoraImpostosStrategyInt;

public class ImpostoServicoStrategyInt implements CalculadoraImpostosStrategyInt {

    private Double taxa = 0.2;

    @Override
    public Double calcularImposto(Double valorBase) {

        Double totalImposto = valorBase*taxa;

        return totalImposto;
    }
}
