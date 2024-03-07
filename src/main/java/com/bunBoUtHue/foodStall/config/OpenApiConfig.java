package com.bunBoUtHue.foodStall.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info (
                contact = @Contact(
                        name = "Just Bitalo",
                        email = "machao86960@gmail.com"
                ),
                description = "OpenAPI document for UT HUE"
                , title = "BUN BO UT HUE API"
                , version = "1.0"
        ),
        servers ={
                @Server(
                        description = "Local ENV"
                        ,url = "http://localhost:8080"
                )
        }

)
public class OpenApiConfig {

}
