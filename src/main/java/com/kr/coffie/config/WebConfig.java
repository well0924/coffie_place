package com.kr.coffie.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Value("/istatic/images/")
	private String imgStatic;
	
	@Value("${server.file.upload}")
	private String imgPath;
	
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
		return encodingFilter;
	}
	
	//multipart설정
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        
    	MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.of(30, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(30, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();

    }
    
    @Bean
    public MultipartResolver multipartResolver(){
            return new StandardServletMultipartResolver();
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
        .addResourceHandler("/img/**")
        .addResourceLocations("classpath:/static/img/")
        .setCachePeriod(60 * 60 * 24 * 365); 
        
		registry
        .addResourceHandler("/font/**")
        .addResourceLocations("classpath:/static/font/")
        .setCachePeriod(60 * 60 * 24 * 365); 
        
		registry
        .addResourceHandler(imgStatic + "**")
        .addResourceLocations("file:///" + imgPath)
        .setCachePeriod(0)
        .resourceChain(true)
        .addResolver(new PathResourceResolver());
		
		registry
        .addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
        registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/"); 
        
        String resourceLocation = "classpath:/static/";
        
        registry
        .addResourceHandler("/static/**")
        .addResourceLocations(resourceLocation);	
        
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
    
    
}
