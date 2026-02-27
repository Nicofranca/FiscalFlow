package com.weg.FiscalFlowSpring.domain.strategy.impl;

import com.weg.FiscalFlowSpring.domain.strategy.CalcularImpostoStrategyInt;

public class ImpostoImportacaoStrategy implements CalcularImpostoStrategyInt {
    @Override
    public Double calcularImposto(Double valorBase) {
        return 0.8;
    }

    @Override
    public boolean suporta(int opcao) {
        return opcao == 4;
    }
}
