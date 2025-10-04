package com.example.jobconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Job Portal API")
                        .version("1.0")
                        .description("API documentation for Job Portal project")
                        .contact(new Contact()
                                .name("Akshaya")
                                .email("akshayasenthil16@gmail.com")
                        )
                );
    }
}
