package io.MicroServicesMovies.movies_catalog_services.models;

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
