package br.com.centroweg.infra.strategy;

import br.com.centroweg.infra.CalculadoraImpostosStrategyInt;

public class ImpostoServicoStrategyInt implements CalculadoraImpostosStrategyInt {
    @Override
    public Double calcularImposto(Double valorBase) {
        return 0.0;
    }
}
