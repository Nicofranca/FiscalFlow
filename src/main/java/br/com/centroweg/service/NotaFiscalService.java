package br.com.centroweg.service;

import br.com.centroweg.domain.NotaFiscal;
import br.com.centroweg.infra.CalculadoraImpostosStrategyInt;
import br.com.centroweg.infra.repository.NotaFiscalRepository;
import br.com.centroweg.infra.strategy.ImpostoExportacaoStrategy;
import br.com.centroweg.infra.strategy.ImpostoImportacaoStrategy;
import br.com.centroweg.infra.strategy.ImpostoProdutoStrategy;
import br.com.centroweg.infra.strategy.ImpostoServicoStrategy;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public class NotaFiscalService {

    CalculadoraImpostosStrategyInt calculadoraImpostosStrategyInt;

    private final NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository){
        this.notaFiscalRepository = notaFiscalRepository;
    }

    public NotaFiscal calcularImposto(String descricao, Double valorBase, int opcao){

        CalculadoraImpostosStrategyInt impostosStrategyInt;

        switch (opcao){
            case 1 -> impostosStrategyInt = new ImpostoServicoStrategy();
            case 2 -> impostosStrategyInt = new ImpostoProdutoStrategy();
            case 3 -> impostosStrategyInt = new ImpostoExportacaoStrategy();
            case 4 -> impostosStrategyInt = new ImpostoImportacaoStrategy();
            default -> throw new IllegalArgumentException("Opcao Inválida");
        }

        Double valorImposto = impostosStrategyInt.calcularImposto(valorBase);
        Double valorTotal = valorImposto+valorBase;

        NotaFiscal notaFiscal = new NotaFiscal();

        notaFiscal.setDescricao(descricao);
        notaFiscal.setValor_base(valorBase);
        notaFiscal.setValor_imposto(valorImposto);
        notaFiscal.setValor_total(valorTotal);
        notaFiscal.setTipo(opcao);
        notaFiscal.setData_emissao(Timestamp.from(Instant.now()));

        try {
            notaFiscalRepository.saveNota(notaFiscal);

            return notaFiscal;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String findByIdTipo(NotaFiscal notaFiscal){
        try {
            return notaFiscalRepository.findByIdTipo(notaFiscal.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
