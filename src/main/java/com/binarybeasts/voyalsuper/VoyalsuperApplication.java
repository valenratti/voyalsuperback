package com.binarybeasts.voyalsuper;

import com.binarybeasts.voyalsuper.vtexapi.VtexResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class VoyalsuperApplication {
    private static final String DIA_API_URL = "https://diaonline.supermercadosdia.com.ar/api/catalog_system/pub/products/search";
    private static final String DIA_QUERYPARAM_LEFT = "fq";
    private static final String DIA_QUERYPARAM_RIGHT = "alternateIds_Ean";

    private static final String JUMBO_API_URL = "https://www.jumbo.com.ar/api/catalog_system/pub/products/search";
    private static final String JUMBO_QUERYPARAM_LEFT = "ft";

    public static void main(String[] args) {
        SpringApplication.run(VoyalsuperApplication.class, args);

        WebClient diaWebClient = WebClient.builder()
                                .baseUrl(DIA_API_URL)
                                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .build();

        String query = String.format("%s:%d",DIA_QUERYPARAM_RIGHT,7791813555049L);//7794984222024 (null) - 7791813555049 (pepsi)
        Flux<VtexResponse> res = diaWebClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam(DIA_QUERYPARAM_LEFT,query).build())
                .retrieve()
                .bodyToFlux(VtexResponse.class)
                .defaultIfEmpty(new VtexResponse());

        res.subscribe(response -> {
            System.out.println(response);
            System.out.println("AfterDiaFetchSync");
        });

        System.out.println("AfterDiaFetchAsync");

        WebClient jumboWebClient =  WebClient.builder()
                                    .baseUrl(JUMBO_API_URL)
                                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                    .build();

        String query2 = String.format("%d",7794984222024L);//7794984222024 (null) - 7791813555049 (pepsi)
        Flux<VtexResponse> res2 = jumboWebClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam(JUMBO_QUERYPARAM_LEFT,query2).build())
                .retrieve()
                .bodyToFlux(VtexResponse.class)
                .defaultIfEmpty(new VtexResponse());

        res2.subscribe(response -> {
            System.out.println(response);
            System.out.println("AfterJumboFetchSync");
        });
        System.out.println("AfterJumboFetchAsync");

    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.binarybeasts.voyalsuper.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Server API").description("Documentation Server API v1.0").build());
    }

}
