package br.com.imagemfilmes.desafio.dto;

import java.math.BigDecimal;

public class RelatorioDTO {

    private BigDecimal saldoTotal;
    private String nome;

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RelatorioDTO(BigDecimal saldoTotal, String nome) {
        this.saldoTotal = saldoTotal;
        this.nome = nome;
    }
}
