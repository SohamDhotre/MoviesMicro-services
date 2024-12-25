package io.MicroServicesMovies.movies_catalog_services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MoviesCatalogServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesCatalogServicesApplication.class, args);
	}


	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(3000);
		return new RestTemplate(requestFactory);
	}

}
