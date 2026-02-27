package com.weg.FiscalFlowSpring.application.service;

import com.weg.FiscalFlowSpring.domain.model.NotaFiscal;
import com.weg.FiscalFlowSpring.domain.strategy.CalcularImpostoStrategyInt;
import com.weg.FiscalFlowSpring.infra.persistence.NotaFiscalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotaFiscalService {
    private final NotaFiscalRepository notaFiscalRespository;
    private final List<CalcularImpostoStrategyInt> stretegies;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRespository, List<CalcularImpostoStrategyInt> stretegies){
        this.notaFiscalRespository=notaFiscalRespository;
        this.stretegies=stretegies;
    }

    public NotaFiscal emitirNota(String descricao, Double valorBase, int opcao){
        CalcularImpostoStrategyInt estrategia = stretegies.stream()
                .filter(s -> s.suporta(opcao))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Opcao de imposto inválida!"));


        Double valorImposto = estrategia.calcularImposto(valorBase);
        Double valorTotal = valorBase + valorImposto;

        NotaFiscal notaFiscal = new NotaFiscal();

        notaFiscal.setDescricao(descricao);
        notaFiscal.setValorBase(valorBase);
        notaFiscal.setValorImposto(valorImposto);
        notaFiscal.setValorTotal(valorTotal);
        notaFiscal.setTipo(opcao);
        notaFiscal.setDataEmissao(LocalDateTime.now());

        return notaFiscalRespository.save(notaFiscal);
    }
}
