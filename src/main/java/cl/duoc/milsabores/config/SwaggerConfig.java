package cl.duoc.milsabores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Pastelería Mil Sabores")
                        .version("1.0.0")
                        .description("Documentación de los endpoints para la gestión de catálogo de tortas, usuarios, roles y órdenes de compra.")
                        .contact(new Contact()
                                .name("Equipo Desarrollo Mil Sabores")
                                .email("contacto@milsabores.cl")));
    }
}