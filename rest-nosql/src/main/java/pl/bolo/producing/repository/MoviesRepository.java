package pl.bolo.producing.repository;

import pl.bolo.producing.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface MoviesRepository extends MongoRepository<Movie, String> {

    public Movie findBySlug(String primaryTitle);
}
