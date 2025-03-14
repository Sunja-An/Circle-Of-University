package co.jp.circleofuniversity.cou.shared.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI documentAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Circle Of University API")
                        .description("API Documentation")
                        .version("0.1.0")
                );
    }
}
