package io.MicroServicesMovies.ratings_data_service.model;

import io.MicroServicesMovies.ratings_data_service.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_rating")
public class UserRating {

    @Id
    private String userId;

    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

}
