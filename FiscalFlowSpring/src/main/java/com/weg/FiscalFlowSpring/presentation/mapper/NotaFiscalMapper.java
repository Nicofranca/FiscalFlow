package com.weg.FiscalFlowSpring.presentation.mapper;

import com.weg.FiscalFlowSpring.domain.model.NotaFiscal;
import com.weg.FiscalFlowSpring.presentation.dto.NotaFiscalResponse;
import org.springframework.stereotype.Component;

@Component
public class NotaFiscalMapper {

    public NotaFiscalResponse responseToEntity(NotaFiscal notaFiscal){
        return new NotaFiscalResponse(
                notaFiscal.getId(),
                notaFiscal.getDescricao(),
                notaFiscal.getValorBase(),
                notaFiscal.getValorImposto(),
                notaFiscal.getValorTotal(),
                notaFiscal.getTipo(),
                notaFiscal.getDataEmissao()
        );
    }
}
