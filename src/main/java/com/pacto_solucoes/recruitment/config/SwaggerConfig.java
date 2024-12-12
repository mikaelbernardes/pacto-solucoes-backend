package com.pacto_solucoes.recruitment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {

    public OpenAPI getOpenAPI() {

        Contact contact = new Contact();

        contact.name("Mikael Bernardes");
        contact.email("mikaelbernardes2022@gmail.com");

        Info info = new Info();
        info.title("Recruitment API Pacto Soluções");
        info.version("V1");
        info.description("Aplicação para teste técnico na empresa Pacto Soluções");
        info.contact(contact);

        return new OpenAPI().info(info);
    }

}
