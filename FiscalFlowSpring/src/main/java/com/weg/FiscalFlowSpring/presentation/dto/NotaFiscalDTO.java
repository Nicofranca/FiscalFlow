package com.weg.FiscalFlowSpring.presentation.dto;

public record NotaFiscalDTO(
        String descricao,
        Double valorBase,
        int opcao
) {
}
