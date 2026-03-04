package com.weg.FiscalFlowSpring.presentation.controller;

import com.weg.FiscalFlowSpring.application.service.NotaFiscalService;
import com.weg.FiscalFlowSpring.domain.model.NotaFiscal;
import com.weg.FiscalFlowSpring.presentation.dto.NotaFiscalDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notas")
public class NotaFiscalController {

    private final NotaFiscalService notaFiscalService;

    public NotaFiscalController(NotaFiscalService notaFiscalService){
        this.notaFiscalService = notaFiscalService;
    }

    @PostMapping
    public NotaFiscal save(@RequestBody NotaFiscalDTO notaFiscalDTO){
        return notaFiscalService.emitirNota(
                notaFiscalDTO.descricao(),
                notaFiscalDTO.valorBase(),
                notaFiscalDTO.opcao()
        );
    }
}
