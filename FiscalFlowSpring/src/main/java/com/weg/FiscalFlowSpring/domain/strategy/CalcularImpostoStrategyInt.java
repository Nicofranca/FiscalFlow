package com.weg.FiscalFlowSpring.domain.strategy;

public interface CalcularImpostoStrategyInt {
    Double calcularImposto(Double valorBase);

    boolean suporta(int opcao);
}
