package com.marko.shop.api;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.marko.shop.api.controller.ControllerPackage;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = {
		ControllerPackage.class
})
public class DefaultApiConfig {

	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
           .select()                                  
           .apis(RequestHandlerSelectors.basePackage("com.marko.shop.api.controller"))              
           .paths(PathSelectors.regex("/.*"))                          
           .build()
           .apiInfo(apiEndPointsInfo())
           .pathProvider(new ApiPathProvider(contextPath))
           .tags(tags()[0]);                                           
    }
	
	private ApiInfo apiEndPointsInfo() {
         return new ApiInfoBuilder().title("Simple shop REST API")
           .description("Simple shop REST API for ordering and bying stuff.")
           .contact(new Contact("Marko Vuckovic", null, "marko.vuckovic@primeholding.com"))
           .license("Apache 2.0")
           .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
           .version("1.0.0")
           .build();
    }
	
	private Tag[] tags() {
	    return new Tag[] {
	        new Tag("User Authentication Controller", "Performs user login and registration.")
	    };
	}
	
}
