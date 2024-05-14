package co.edu.usa.talentotech.sga.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
	info = @Info(
            title = "The User API",
            description = "get and manage all users",
            version = "1.0"
           
    )
)
public class OpenApiConfig {

}
