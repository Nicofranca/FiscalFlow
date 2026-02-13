package br.com.centroweg.infra.strategy;

import br.com.centroweg.infra.CalculadoraImpostosStrategy;

public class ImpostoServicoStrategy implements CalculadoraImpostosStrategy {
    @Override
    public Double calcularImposto(Double valorBase) {
        return 0.0;
    }
}
