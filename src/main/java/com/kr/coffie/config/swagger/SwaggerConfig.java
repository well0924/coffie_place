package com.kr.coffie.config.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(basePackages = {"com.kr.coffie.*"})
public class SwaggerConfig {
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("CoffiePlace")
				.version("version 0.2")
				.description("Coffieplace Api")
				.license("license")
				.build();
	}
	
    //swagger설정
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kr.coffie"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo());
    }
}
