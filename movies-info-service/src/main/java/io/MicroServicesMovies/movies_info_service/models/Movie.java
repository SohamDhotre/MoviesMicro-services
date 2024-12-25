package io.MicroServicesMovies.movies_info_service.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String movieId;
    private String name;
    private String description;
    private String tagline;

}
