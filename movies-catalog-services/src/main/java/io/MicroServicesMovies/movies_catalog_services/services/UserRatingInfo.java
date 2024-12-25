package io.MicroServicesMovies.movies_catalog_services.services;

import io.MicroServicesMovies.movies_catalog_services.models.Rating;
import io.MicroServicesMovies.movies_catalog_services.models.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.logging.Logger;

@Service
public class UserRatingInfo {

    Logger logger = Logger.getLogger(UserRatingInfo.class.getName());
    private RestTemplate restTemplate;

    UserRatingInfo(RestTemplate restTemplate) {
         this.restTemplate=restTemplate;
    }
    private int retryCounter = 0;

    @Retry(name = "ServiceRetry")
    @CircuitBreaker(name = "ServiceCircuitBreaker", fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userId) throws Exception {
        retryCounter++;
        logger.info("Attempt number: "+ retryCounter);
        //for testing circuit breaking functionality
        simulateFailureForTesting(userId);

        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
        return userRating;
    }

    public UserRating getFallbackUserRating(String userId, Exception t) {
        Rating rating = new Rating("0",0);
        return new UserRating(userId, Arrays.asList(rating));
    }

    private void simulateFailureForTesting(String userId) throws Exception {
        if (userId.equals("test")) {
            throw new RuntimeException("Simulated failure");
        }
    }

}
