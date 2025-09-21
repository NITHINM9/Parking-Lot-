package com.example.parking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI parkingLotOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Parking Lot Reservation API")
                        .description("API documentation for Parking Lot Reservation System")
                        .version("1.0.0"));
    }
}
