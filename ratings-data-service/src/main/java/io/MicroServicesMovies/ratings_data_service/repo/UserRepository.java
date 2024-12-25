package io.MicroServicesMovies.ratings_data_service.repo;

import io.MicroServicesMovies.ratings_data_service.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRating,String> {

}
