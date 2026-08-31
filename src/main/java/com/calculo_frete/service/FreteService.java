package com.calculo_frete.service;

import com.calculo_frete.config.AppConfig;
import com.calculo_frete.model.Produto;
import com.calculo_frete.repository.FreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SERVICE - Regra de negocio
 * 
 * Antes (Java puro): private FreteRepository repository = new FreteRepository();
 * Agora (Spring Boot): @Autowired - Spring injeta sozinho
 * 
 * Aqui fica sua logica: preco_final = preco_base + taxa_fixa
 */
@Service
public class FreteService {

    // Injeção de dependência - Spring cria o repository automaticamente
    @Autowired
    private FreteRepository repository;

    @Autowired
    private AppConfig appConfig;

    /**
     * Calcula frete - mesma logica do seu Portugol!
     */
    public Produto calcularFrete(String nomeCliente, double precoBase) {

        System.out.println("[SERVICE] Iniciando calculo para: " + nomeCliente);

        // Validacoes usando config
        if (precoBase < appConfig.getPrecoMinimo()) {
            throw new IllegalArgumentException("Preco base deve ser maior que " + appConfig.getPrecoMinimo());
        }
        if (precoBase > appConfig.getPrecoMaximo()) {
            throw new IllegalArgumentException("Preco base excede maximo de " + appConfig.getPrecoMaximo());
        }

        // PROCESSAMENTO - SUA LOGICA DO PORTUGOL
        double precoFinal = precoBase + appConfig.getTaxaFixaFrete();

        System.out.println("[SERVICE] " + precoBase + " + " + appConfig.getTaxaFixaFrete() + " = " + precoFinal);

        // Cria e salva no banco real (H2)
        Produto produto = new Produto(nomeCliente, precoBase, appConfig.getTaxaFixaFrete(), precoFinal);
        return repository.save(produto);
    }

    public double calcularFreteSimples(double precoBase) {
        if (precoBase < 0) throw new IllegalArgumentException("Valor negativo");
        return precoBase + appConfig.getTaxaFixaFrete();
    }
}
