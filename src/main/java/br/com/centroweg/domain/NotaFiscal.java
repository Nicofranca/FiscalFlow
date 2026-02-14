package br.com.centroweg.domain;

import java.sql.Timestamp;

public class NotaFiscal {
    private int id;
    private String descricao;
    private Double valor_base;
    private Double valor_imposto;
    private Double valor_total;
    private int tipo;
    private Timestamp data_emissao;

    public NotaFiscal(int id, String descricao, Double valor_base, Double valor_imposto, Double valor_total, int tipo, Timestamp dat_emissao) {
        this.id = id;
        this.descricao = descricao;
        this.valor_base = valor_base;
        this.valor_imposto = valor_imposto;
        this.valor_total = valor_total;
        this.tipo = tipo;
        this.data_emissao = dat_emissao;
    }

    public NotaFiscal(String descricao, Double valor_base, Double valor_imposto, Double valor_total, int tipo, Timestamp dat_emissao) {
        this.descricao = descricao;
        this.valor_base = valor_base;
        this.valor_imposto = valor_imposto;
        this.valor_total = valor_total;
        this.tipo = tipo;
        this.data_emissao = dat_emissao;
    }

    public NotaFiscal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor_base() {
        return valor_base;
    }

    public void setValor_base(Double valor_base) {
        this.valor_base = valor_base;
    }

    public Double getValor_imposto() {
        return valor_imposto;
    }

    public void setValor_imposto(Double valor_imposto) {
        this.valor_imposto = valor_imposto;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Timestamp getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(Timestamp data_emissao) {
        this.data_emissao = data_emissao;
    }
}
