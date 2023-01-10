package com.pipecoding.ImJaeWook_QNI_Server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pipecoding.ImJaeWook_QNI_Server.controller"))
                .paths(PathSelectors.any()) // 모든 url 에 대해 명세서 작성
                .build()
                .useDefaultResponseMessages(true);
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("PipeCoding-Club Assignment")
                .description("SpringBoot 기반으로 백엔드를 구현합니다.")
                .version("1.0")
                .build();
    }
}