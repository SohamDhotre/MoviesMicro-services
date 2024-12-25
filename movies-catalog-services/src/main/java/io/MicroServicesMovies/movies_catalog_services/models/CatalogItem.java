package io.MicroServicesMovies.movies_catalog_services.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItem {
    private int rating;
    private String catalogName;
    private String desc;
    private String tagline;

}
