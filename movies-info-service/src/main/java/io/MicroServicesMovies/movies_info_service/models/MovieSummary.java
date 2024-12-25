package io.MicroServicesMovies.movies_info_service.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class MovieSummary {

    private String id;
    private String title;
    private String overview;
    private String tagline;

}
