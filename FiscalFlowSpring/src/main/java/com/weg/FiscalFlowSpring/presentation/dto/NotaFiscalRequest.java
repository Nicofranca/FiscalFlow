package com.weg.FiscalFlowSpring.presentation.dto;

public record NotaFiscalRequest(
        String descricao,
        Double valorBase,
        int opcao
) {
}
