package com.calculo_frete.controller;

import com.calculo_frete.model.Produto;
import com.calculo_frete.repository.FreteRepository;
import com.calculo_frete.service.FreteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * CONTROLLER - API REST com Swagger
 * Documentacao disponivel em: http://localhost:8080/swagger-ui.html
 */
@RestController
@RequestMapping("/api/frete")
@Tag(name = "Calculo de Frete", description = "Endpoints para calculo de frete - Regra: preco_final = preco_base + taxa_fixa")
public class FreteController {

    @Autowired
    private FreteService service;

    @Autowired
    private FreteRepository repository;

    @Operation(summary = "Calcular frete via GET", 
               description = "Calcula preco final = preco_base + taxa_fixa (R$ 10). Salva no banco e retorna produto com ID.")
    @GetMapping("/calcular")
    public Produto calcularFrete(
            @Parameter(description = "Nome do cliente", example = "Joao") @RequestParam String nome,
            @Parameter(description = "Preco base do produto", example = "100.0") @RequestParam double precoBase) {
        System.out.println("[CONTROLLER] Requisicao API - Cliente: " + nome + " Base: " + precoBase);
        return service.calcularFrete(nome, precoBase);
    }

    @Operation(summary = "Calcular frete via POST JSON", 
               description = "Mesma regra, mas recebe JSON no body. Ideal para frontend.")
    @PostMapping("/calcular-json")
    public Produto calcularFreteJson(@RequestBody Produto request) {
        return service.calcularFrete(request.getNomeCliente(), request.getPrecoBase());
    }

    @Operation(summary = "Listar todos os calculos", 
               description = "Retorna todos os produtos/frete ja calculados e salvos no banco H2")
    @GetMapping("/listar")
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    @Operation(summary = "Buscar por nome do cliente")
    @GetMapping("/buscar")
    public List<Produto> buscarPorNome(
            @Parameter(description = "Nome para buscar", example = "Joao") @RequestParam String nome) {
        return repository.findByNomeCliente(nome);
    }

    @Operation(summary = "Calculo simples sem salvar", 
               description = "Retorna apenas o valor final (double), sem salvar no banco. Ideal para teste rapido.")
    @GetMapping("/simples")
    public double calcularSimples(
            @Parameter(description = "Preco base", example = "100.0") @RequestParam double precoBase) {
        return service.calcularFreteSimples(precoBase);
    }
}
