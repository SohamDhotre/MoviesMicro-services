package io.MicroServicesMovies.movies_catalog_services.resources;

import io.MicroServicesMovies.movies_catalog_services.models.CatalogItem;
import io.MicroServicesMovies.movies_catalog_services.models.UserRating;
import io.MicroServicesMovies.movies_catalog_services.services.MovieInfo;
import io.MicroServicesMovies.movies_catalog_services.services.UserRatingInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    MovieInfo movieInfo;

    UserRatingInfo userRatingInfo;

    public CatalogResource(MovieInfo movieInfo, UserRatingInfo userRatingInfo) {
        this.movieInfo = movieInfo;
        this.userRatingInfo = userRatingInfo;
    }

    @RequestMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) throws Exception {

        UserRating userRating = userRatingInfo.getUserRating(userId);
        return userRating.getRatings().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());

    }

}