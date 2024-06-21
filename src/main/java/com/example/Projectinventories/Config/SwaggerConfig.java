package com.example.Projectinventories.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("API de Inventário de Produtos")
                .description("API para gerenciar produtos e categorias.")
                .version("1.0.0")
                .contact(new Contact().name("Ana Caroline, Matheus Fernandes e Nikoly Pereira"))
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
