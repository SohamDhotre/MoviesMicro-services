package io.MicroServicesMovies.movies_catalog_services.services;

import io.MicroServicesMovies.movies_catalog_services.models.CatalogItem;
import io.MicroServicesMovies.movies_catalog_services.models.Movie;
import io.MicroServicesMovies.movies_catalog_services.models.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    private RestTemplate restTemplate;

    public MovieInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retry(name = "ServiceRetry")
    @CircuitBreaker(name = "ServiceCircuitBreaker", fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {

        //for testing circuit breaking functionality
        simulateFailureForTesting(rating);

        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(rating.getRating(), movie.getName(), movie.getDescription(), movie.getTagline());
    }

    public  CatalogItem getFallbackCatalogItem(Rating rating, Exception e) {
        return new CatalogItem(rating.getRating(), "movie name not found", "Services are not accessible", "try again in some time");
    }

    private void simulateFailureForTesting(Rating rating) {
        if (rating.getRating() == -1) {
            throw new RuntimeException("Simulated failure");
        }
    }
}
