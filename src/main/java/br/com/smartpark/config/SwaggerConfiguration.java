package br.com.smartpark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        Predicate<RequestHandler> rootPackage = RequestHandlerSelectors.basePackage("br.com.smartpark")::apply;
        Predicate<String> apiUrls = PathSelectors.ant("/api/**")::apply;
        return new Docket(DocumentationType.SWAGGER_2).select().apis(rootPackage::test)
                .paths(apiUrls::test).build();
    }

}
