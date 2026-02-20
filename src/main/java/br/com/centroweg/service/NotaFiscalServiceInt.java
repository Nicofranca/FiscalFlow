package br.com.centroweg.service;

import br.com.centroweg.domain.NotaFiscal;

public interface NotaFiscalServiceInt {
    NotaFiscal calcularImposto(String descricao, Double valorBase, int opcao);
    NotaFiscal findByIdTipo(NotaFiscal notaFiscal);
}
