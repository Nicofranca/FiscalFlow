package br.com.centroweg.infra.repository;

import br.com.centroweg.domain.NotaFiscal;

import java.sql.SQLException;
import java.util.List;

public interface NotaFiscalRepositoryInt {
    NotaFiscal saveNota(NotaFiscal notaFiscal) throws SQLException;
    List<NotaFiscal> listNotas() throws SQLException;
    NotaFiscal findByIdNota(int id) throws SQLException;
    void updateByIdNota(NotaFiscal notaFiscal) throws SQLException;
    void deleteByIdNota() throws SQLException;
}
