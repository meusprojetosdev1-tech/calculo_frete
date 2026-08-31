package com.calculo_frete.repository;

import com.calculo_frete.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * REPOSITORY - Acesso ao banco de dados
 * 
 * Antes (Java puro): voce escreveu 50 linhas com HashMap, salvar(), buscarPorId()
 * Agora (Spring Boot): 1 linha e ganha tudo de graca!
 * 
 * JpaRepository ja tem:
 * - save() - salvar
 * - findById() - buscar por ID
 * - findAll() - listar todos
 * - deleteById() - deletar
 * - count() - contar
 * 
 * Voce nao precisa escrever SQL!
 */
@Repository
public interface FreteRepository extends JpaRepository<Produto, Long> {

    // Query customizada - Spring cria o SQL sozinho pelo nome do metodo
    List<Produto> findByNomeCliente(String nomeCliente);
    
    // Outra query: buscar por preco maior que X
    List<Produto> findByPrecoBaseGreaterThan(double preco);
}
