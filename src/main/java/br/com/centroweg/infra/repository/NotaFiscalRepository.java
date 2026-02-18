package br.com.centroweg.infra.repository;

import br.com.centroweg.domain.NotaFiscal;
import br.com.centroweg.infra.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalRepository implements NotaFiscalRepositoryInt{
    @Override
    public NotaFiscal saveNota(NotaFiscal notaFiscal) throws SQLException {
        String query = """
                INSERT INTO NotaFiscal (descricao, valor_base, valor_imposto, valor_total, tipo, data_emissao) VALUES (?, ?, ?, ?, ?, ?)
                """;


        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, notaFiscal.getDescricao());
            stmt.setDouble(2, notaFiscal.getValor_base());
            stmt.setDouble(3, notaFiscal.getValor_imposto());
            stmt.setDouble(4, notaFiscal.getValor_total());
            stmt.setInt(5, notaFiscal.getTipo());
            stmt.setTimestamp(6, notaFiscal.getData_emissao());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()){
                    notaFiscal.setId(rs.getInt(1));
                }
            }

        }

        return notaFiscal;
    }

    @Override
    public List<NotaFiscal> listNotas() throws SQLException{
        String query = """
                SELECT descricao, valor_base, valor_imposto, valor_total, tipo, data_emissao FROM NotaFiscal
                """;

        List<NotaFiscal> listNotaFiscal = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()){
                    NotaFiscal notaFiscal = new NotaFiscal();
                    notaFiscal.setDescricao(rs.getString("descricao"));
                    notaFiscal.setValor_base(rs.getDouble("valor_base"));
                    notaFiscal.setValor_imposto(rs.getDouble("valor_imposto"));
                    notaFiscal.setValor_total(rs.getDouble("valor_total"));
                    notaFiscal.setTipo(rs.getInt("tipo"));
                    notaFiscal.setData_emissao(rs.getTimestamp("data_emissao"));

                    listNotaFiscal.add(notaFiscal);
                }
            }
        }
        return listNotaFiscal;
    }

    @Override
    public NotaFiscal findByIdNota(int id) throws SQLException{
        String query = """
                SELECT descricao, valor_base, valor_imposto, valor_total, tipo, data_emissao FROM NotaFiscal WHERE id = ?
                """;


        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()){
                    NotaFiscal notaFiscal = new NotaFiscal();
                    notaFiscal.setDescricao(rs.getString("descricao"));
                    notaFiscal.setValor_base(rs.getDouble("valor_base"));
                    notaFiscal.setValor_imposto(rs.getDouble("valor_imposto"));
                    notaFiscal.setValor_total(rs.getDouble("valor_total"));
                    notaFiscal.setTipo(rs.getInt("tipo"));
                    notaFiscal.setData_emissao(rs.getTimestamp("data_emissao"));

                    return notaFiscal;
                }
            }
        }
        return null;
    }

    @Override
    public void updateByIdNota(NotaFiscal notaFiscal) throws SQLException{

    }

    @Override
    public void deleteByIdNota() throws SQLException{

    }
}
