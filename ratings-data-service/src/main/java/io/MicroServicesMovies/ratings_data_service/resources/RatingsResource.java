package io.MicroServicesMovies.ratings_data_service.resources;

import io.MicroServicesMovies.ratings_data_service.model.Rating;
import io.MicroServicesMovies.ratings_data_service.model.UserRating;
import io.MicroServicesMovies.ratings_data_service.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @Autowired
    public UserRepository userRepository;

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = userRepository.findById(userId)
                .map(UserRating::getRatings)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Return a new UserRating object with the fetched ratings
        return new UserRating(userId, ratings);
    }

}
