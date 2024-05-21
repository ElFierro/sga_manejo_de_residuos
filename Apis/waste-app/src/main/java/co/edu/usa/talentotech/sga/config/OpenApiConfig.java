package co.edu.usa.talentotech.sga.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
	info = @Info(
            title = "Microservicio del manejo de residuos",
            description = "Este microservicio se encarga de administrar y registrar los datos de la recoleccion de residuos",
            version = "1.0"
           
    )
)
public class OpenApiConfig {

}
