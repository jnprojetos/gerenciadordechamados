package br.com.gestordechamados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.gestordechamados"))
                .paths(PathSelectors.regex("/api/v1.*"))
                .build()
                .produces(new HashSet<>(
                        Arrays.asList(
                                "application/json"
                        )
                ))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessagesForGET())
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "Swagger API Gerenciador de Chamados",
                "API Para Geneciamento de Chamados",
                "1.0",
                "Apache license Version 2.0",
                new Contact("Josimar Jose Nepomuceno", "http://exemplo.com", "josimar.n2007@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",
                new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }

    private List<ResponseMessage> responseMessagesForGET(){
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(400)
                .message("Bad Request")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder()
                .code(404)
                .message("Not Found")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder()
                .code(500)
                .message("Service Unavailable")
                .responseModel(new ModelRef("Error"))
                .build());
        }};
    }

}
