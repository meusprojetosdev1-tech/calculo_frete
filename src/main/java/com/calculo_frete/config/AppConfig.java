package com.calculo_frete.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * CONFIG - Agora com Spring Boot
 * 
 * Antes (Java puro): public static final double TAXA_FIXA = 10.00
 * Agora (Spring Boot): @Value pega do application.properties
 * 
 * Vantagem: muda a taxa sem recompilar, so muda o arquivo properties
 */
@Configuration
public class AppConfig {

    // Pega do application.properties: frete.taxa.fixa=10.00
    @Value("${frete.taxa.fixa}")
    private double taxaFixaFrete;

    @Value("${frete.moeda}")
    private String moeda;

    @Value("${frete.preco.minimo}")
    private double precoMinimo;

    @Value("${frete.preco.maximo}")
    private double precoMaximo;

    public double getTaxaFixaFrete() { return taxaFixaFrete; }
    public String getMoeda() { return moeda; }
    public double getPrecoMinimo() { return precoMinimo; }
    public double getPrecoMaximo() { return precoMaximo; }
}
