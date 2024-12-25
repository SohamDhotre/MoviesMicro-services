package io.MicroServicesMovies.ratings_data_service.resources;

import io.MicroServicesMovies.ratings_data_service.model.Rating;
import io.MicroServicesMovies.ratings_data_service.model.UserRating;
import io.MicroServicesMovies.ratings_data_service.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initData() {
        userRepository.saveAll(List.of(
                new UserRating("1", List.of(
                        new Rating(null,"101", 5),
                        new Rating(null,"103", 4)
                )),
                new UserRating("2", List.of(
                        new Rating(null,"103", 3),
                        new Rating(null,"104", 5),
                        new Rating(null,"105", 2)
                ))
        ));
    }
}
