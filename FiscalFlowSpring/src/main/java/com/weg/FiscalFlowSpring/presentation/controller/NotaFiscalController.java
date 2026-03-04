package com.weg.FiscalFlowSpring.presentation.controller;

import com.weg.FiscalFlowSpring.application.service.NotaFiscalService;
import com.weg.FiscalFlowSpring.domain.model.NotaFiscal;
import com.weg.FiscalFlowSpring.presentation.dto.NotaFiscalRequest;
import com.weg.FiscalFlowSpring.presentation.dto.NotaFiscalResponse;
import com.weg.FiscalFlowSpring.presentation.mapper.NotaFiscalMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notas")
public class NotaFiscalController {

    private final NotaFiscalService notaFiscalService;
    private final NotaFiscalMapper notaFiscalMapper;

    public NotaFiscalController(NotaFiscalService notaFiscalService, NotaFiscalMapper notaFiscalMapper){
        this.notaFiscalService = notaFiscalService;
        this.notaFiscalMapper=notaFiscalMapper;
    }

    @PostMapping
    public NotaFiscalResponse save(@RequestBody NotaFiscalRequest notaFiscalDTO){
         NotaFiscal notaFiscal = notaFiscalService.emitirNota(
                notaFiscalDTO.descricao(),
                notaFiscalDTO.valorBase(),
                notaFiscalDTO.opcao()
        );

         return notaFiscalMapper.responseToEntity(notaFiscal);
    }
}
