package com.calculo_frete.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracao do Swagger / OpenAPI
 * Gera documentacao automatica da sua API em /swagger-ui.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API CALCULO_FRETE - Calculo de Frete")
                        .version("1.0.0")
                        .description("API REST para calculo de frete.\n\n"
                                + "Regra de negocio: preco_final = preco_base + taxa_fixa (R$ 10,00)\n\n"
                                + "Arquitetura: Controller -> Service -> Repository -> H2 Database\n\n"
                                + "Desenvolvido a partir do algoritmo em Portugol CALCULO_FRETE")
                        .contact(new Contact()
                                .name("Guilherme")
                                .url("https://github.com/seu-usuario/calculo-frete-api")));
    }
}
