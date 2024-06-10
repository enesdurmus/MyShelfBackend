package com.enesd.myshelfbackend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setEmail("enesdurmus3738@gmail.com");
        contact.setName("EnesD");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Shelf API")
                .version("1.0")
                .contact(contact)
                .description("Shelf Api.")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}
