package br.alverad.swagger_demo_bug.application.config.swagger;

import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.alverad.swagger_demo_bug.commons.annotations.RotaPublica;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "asdasd", version = "1.0",
                license = @License(name = "asdasdasdasd", url = "http:/asdasdasdao.aspx"),
                description = "API sadasdasdasd.", contact = @Contact(name = "SasdasdPA")),
        externalDocs = @ExternalDocumentation(description = "Wiki do Projeto no Gitlab",
                url = "https://gasdsadasdwikis/home"))

@SecurityScheme(name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer",
        bearerFormat = "JWT")
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("public").pathsToMatch("/**")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(RotaPublica.class))
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder().group("admin").pathsToMatch("/**")
                .addOpenApiCustomizer(externalGroupOpenApiCustomizer()).build();
    }

    OpenApiCustomizer externalGroupOpenApiCustomizer() {
        SecurityRequirement a = new SecurityRequirement();
        a.addList("Bearer");
        return openApi -> openApi.addSecurityItem(a);
    }

    @Bean
    public OperationCustomizer operationIdCustomizer() {
        return (operation, handlerMethod) -> {
            Class<?> superClazz = handlerMethod.getBeanType().getSuperclass();
            try {
                if (Objects.nonNull(superClazz)) {
                    Operation annontation =
                            handlerMethod.getMethod().getAnnotation(Operation.class);
                    if (operation != null) {
                        String beanName = handlerMethod.getBeanType().getSimpleName();
                        operation.setOperationId(Optional.of(annontation.operationId())
                                .orElse(handlerMethod.getMethod().getName()));
                    } else {
                        operation.setOperationId(handlerMethod.getMethod().getName());
                    }
                }
                return operation;

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return null;
            }
        };
    }
}
