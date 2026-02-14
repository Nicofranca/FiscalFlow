package br.com.centroweg.infra.repository;

import br.com.centroweg.domain.NotaFiscal;

import java.util.List;

public class NotaFiscalRepository implements NotaFiscalRepositoryInt{
    @Override
    public NotaFiscal saveNota(NotaFiscal notaFiscal) {
        return null;
    }

    @Override
    public List<NotaFiscal> listNotas() {
        return List.of();
    }

    @Override
    public NotaFiscal findByIdNota(int id) {
        return null;
    }

    @Override
    public void updateByIdNota(NotaFiscal notaFiscal) {

    }

    @Override
    public void deleteByIdNota() {

    }
}
