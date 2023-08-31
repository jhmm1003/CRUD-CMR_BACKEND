package com.prueba.CRM.config.configs.cors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Value("${spring.application.cors}")
    private String[] ArrayOfCors = new String[]{};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        ArrayOfCors = new String[]{"*"}; //Temporal
        registry
                .addMapping("/**")
                .allowedOrigins(ArrayOfCors)
                .allowedHeaders("*")
                .allowedMethods("*");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}