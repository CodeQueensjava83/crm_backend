package com.generation.crm.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI crmOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRMfy API")
                        .description("API do projeto CRMfy - Sistema de Gestão de Relacionamento com Clientes")
                        .version("v0.0.1")
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact()
                                .name("CodeQueens")
                                .url("https://github.com/CodeQueensjava83")
                                .email("grupob.turmajava83@gmail.com")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do Projeto")
                        .url("https://github.com/CodeQueensjava83/crm_backend")
                );
    }

    @Bean
    public OpenApiCustomizer globalResponsesCustomizer() {
        return openApi -> openApi.getPaths().values().forEach(pathItem ->
                pathItem.readOperations().forEach(operation -> {

                    ApiResponses apiResponses = operation.getResponses();

                    apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                    apiResponses.addApiResponse("201", createApiResponse("Objeto persistido!"));
                    apiResponses.addApiResponse("204", createApiResponse("Objeto excluído!"));
                    apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
                    apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
                    apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!"));
                })
        );
    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }
}
