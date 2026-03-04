package com.weg.FiscalFlowSpring.presentation.dto;

import java.time.LocalDateTime;

public record NotaFiscalResponse(
        Long id,
        String descricao,
        Double valorBase,
        Double valorImposto,
        Double valorTotal,
        int tipo,
        LocalDateTime dataEmissao
) {
}
