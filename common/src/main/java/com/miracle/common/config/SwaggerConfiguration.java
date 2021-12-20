package com.miracle.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author sqq
 * @Description
 * @Date 2021/12/20 13:39
 **/
@Configuration
@ConfigurationProperties(prefix = "springdoc.swagger")
public class SwaggerConfiguration {

  private String title;

  private String description;

  private String version;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Bean
  public OpenAPI openAPI() {
    Components components = new Components();
    components.addSecuritySchemes("Authorization", new SecurityScheme()
        .name("Authorization")
        .type(SecurityScheme.Type.APIKEY)
        .scheme("bearer").in(SecurityScheme.In.HEADER).bearerFormat("JWT"));
    return new OpenAPI()
        .info(new Info().title(getTitle())
            .description(getDescription())
            .version(getVersion())
        )
        .addSecurityItem(new SecurityRequirement().addList("Authorization"))
        .components(components);
  }
}
