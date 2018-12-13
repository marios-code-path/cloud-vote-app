package com.sportsnet.sportsnetgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.result.method.annotation.PrincipalArgumentResolver;

@SpringBootApplication
@Configuration
@Profile("gateway")
public class SportsnetGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsnetGatewayApplication.class, args);
    }

    @Value("${polls.baseuri:http://localhost:8180}")
    String baseUri;

    @Bean
    public RouteLocator openRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec.method(HttpMethod.GET)
                        .and()
                        .path("/login")
                        .uri(baseUri + "/login")
                    )
                .route(predicateSpec -> predicateSpec.method(HttpMethod.GET)
                        .and()
                        .path("/logout")
                        .uri(baseUri + "/logout")
                )
                .route(predicateSpec -> predicateSpec.method(HttpMethod.GET)
                        .and()
                        .path("/bye")
                        .uri(baseUri + "/bye")
                )

                .build();
    }

    @Bean
    public RouteLocator secureRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicate -> predicate
                        .method(HttpMethod.GET)
                        .and()
                        .path("/list")
                        .uri(baseUri + "/list"))
                .route(predicate -> predicate
                        .method(HttpMethod.GET)
                        .and()
                        .path("/results")
                        .uri(baseUri + "/results"))
                .route(predicate -> predicate
                        .method(HttpMethod.POST)
                        .and()
                        .method(HttpMethod.GET)
                        .and()
                        .path("/login")
                        .uri(baseUri + "/login"))
                .route(predicate -> predicate
                        .method(HttpMethod.PUT)
                        .and()
                        .path("/poll")
                        .and()
                        .uri(baseUri + "/poll"))         // partial path ( matches on poll as second param)
                .route(predicate -> predicate
                        .method(HttpMethod.POST)
                        .and()
                        .path("/vote")
                        .and()
                        .uri(baseUri + "/vote"))
                .build();
    }
}

/**
 * gateway spec: must support security
 * - user authentication ( if supported )
 * + comm\ to service for create poll hash through special clientID
 * - forward to poll URI ( /poll/398f3ef3829fe ) to push to poll
 * *(everything in URL between user and poll service has random value )
 * expose public URI for poll
 * randomize URI for audit results
 * <p>
 * service spec:
 * vending of poll hashes (e.g. 398f3ef3829fe)
 * user create poll
 * user(s) push to poll
 * public get poll results
 * list polls
 * audit results
 **/
