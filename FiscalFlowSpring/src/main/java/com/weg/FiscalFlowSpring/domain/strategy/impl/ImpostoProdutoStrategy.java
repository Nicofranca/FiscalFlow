package com.weg.FiscalFlowSpring.domain.strategy.impl;

import com.weg.FiscalFlowSpring.domain.strategy.CalcularImpostoStrategyInt;

public class ImpostoProdutoStrategy  implements CalcularImpostoStrategyInt {
    @Override
    public Double calcularImposto(Double valorBase) {
        return 0.0;
    }

    @Override
    public boolean suporta(int opcao) {
        return false;
    }
}
