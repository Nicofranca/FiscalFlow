package br.com.centroweg.infra.repository;

import br.com.centroweg.domain.NotaFiscal;

import java.util.List;

public interface NotaFiscalRepositoryInt {
    NotaFiscal saveNota(NotaFiscal notaFiscal);
    List<NotaFiscal> listNotas();
    NotaFiscal findByIdNota(int id);
    void updateByIdNota(NotaFiscal notaFiscal);
    void deleteByIdNota();
}
