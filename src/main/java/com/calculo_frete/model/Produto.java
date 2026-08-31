package com.calculo_frete.model;

import jakarta.persistence.*;

/**
 * MODEL / ENTITY - Tabela do banco de dados
 * 
 * Antes (Java puro): classe simples com atributos
 * Agora (Spring Boot): @Entity diz que e tabela no banco
 * 
 * O Spring Data JPA cria a tabela tb_produto automaticamente
 */
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;
    private double precoBase;
    private double taxaFixa;
    private double precoFinal;
    private String status;

    public Produto() {}

    public Produto(String nomeCliente, double precoBase, double taxaFixa, double precoFinal) {
        this.nomeCliente = nomeCliente;
        this.precoBase = precoBase;
        this.taxaFixa = taxaFixa;
        this.precoFinal = precoFinal;
        this.status = "CALCULADO";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }
    public double getTaxaFixa() { return taxaFixa; }
    public void setTaxaFixa(double taxaFixa) { this.taxaFixa = taxaFixa; }
    public double getPrecoFinal() { return precoFinal; }
    public void setPrecoFinal(double precoFinal) { this.precoFinal = precoFinal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
