package com.calculo_frete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculoFreteApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculoFreteApplication.class, args);
        System.out.println("=================================");
        System.out.println("API CALCULO_FRETE no ar!");
        System.out.println("Regra: preco_final = preco_base + 10");
        System.out.println("Swagger: /swagger-ui.html");
        System.out.println("App Mobile: /");
        System.out.println("=================================");
    }
}
