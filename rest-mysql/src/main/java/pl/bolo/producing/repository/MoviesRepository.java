package pl.bolo.producing.repository;

import pl.bolo.producing.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movie, Long> {
}
