package com.binarybeasts.voyalsuper;

import com.binarybeasts.voyalsuper.preciosclarosapi.PreciosClarosResponse;
import com.binarybeasts.voyalsuper.vtexapi.VtexResponse;
import com.google.common.collect.Lists;
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
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class VoyalsuperApplication {
    private static final String DIA_API_URL = "https://diaonline.supermercadosdia.com.ar/api/catalog_system/pub/products/search";
    private static final String DIA_QUERYPARAM_LEFT = "fq";
    private static final String DIA_QUERYPARAM_RIGHT = "alternateIds_Ean";

    private static final String JUMBO_API_URL = "https://www.jumbo.com.ar/api/catalog_system/pub/products/search";
    private static final String JUMBO_QUERYPARAM_LEFT = "ft";

    private static final String PRECIOSCLAROS_API_URL = "https://d3e6htiiul5ek9.cloudfront.net/prod/productos";
    private static final String PRECIOS_CLAROS_IDCAT = "id_categoria";
    private static final String PRECIOS_CLARON_SUC = "array_sucursales";


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

        WebClient preciosClarosWebClient = WebClient.builder().baseUrl(PRECIOSCLAROS_API_URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

        String catId = "01";
        String arrSuc = "15-1-317,10-3-574,15-1-573,65-1-318,10-3-532,15-1-277,15-1-1100,19-1-03340,10-2-387,19-1-00007,10-3-525,12-1-108,15-1-522,15-1-486,10-2-198,15-1-576,15-1-542,15-1-375,15-1-401,15-1-109,15-1-393,15-1-337,10-3-613,15-1-111,10-3-368,3-1-29,10-3-556,10-3-391,10-3-632,15-1-498";
        Flux<PreciosClarosResponse> res3 = preciosClarosWebClient.get()
                .uri(uriBuilder -> {
                    return uriBuilder.queryParam(PRECIOS_CLAROS_IDCAT,catId)
                            .queryParam(PRECIOS_CLARON_SUC,arrSuc)
                            .queryParam("offset","0")
                            .queryParam("limit","100")
                            .build();
                })
                .retrieve()
                .bodyToFlux(PreciosClarosResponse.class)
                .defaultIfEmpty(new PreciosClarosResponse());
        res3.subscribe(response->{
            System.out.println("Cantidad de productos:"+response.getProductos().size());
            response.getProductos().forEach(System.out::println);
            System.out.println("After Precios Claros Fetch");
        });
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.binarybeasts.voyalsuper.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Server API").description("Documentation Server API v1.0").build())
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Lists.newArrayList(securityContext()));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    @Bean
    SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

}
