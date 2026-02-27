package com.weg.FiscalFlowSpring.domain.strategy.impl;

import com.weg.FiscalFlowSpring.domain.strategy.CalcularImpostoStrategyInt;
import org.springframework.stereotype.Component;

@Component
public class ImpostoExportacaoStrategy implements CalcularImpostoStrategyInt {
    @Override
    public Double calcularImposto(Double valorBase) {
        return 0.3;
    }

    @Override
    public boolean suporta(int opcao) {
        return opcao == 3;
    }
}
