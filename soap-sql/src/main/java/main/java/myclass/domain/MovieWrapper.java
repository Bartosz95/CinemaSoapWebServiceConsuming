package main.java.myclass.domain;

import com.cinemasevice.soapwebservicesproducing.Movie;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "movies")
public class MovieWrapper {
    @Id
    @GeneratedValue
    protected long id;
    protected String titleType;
    protected String primaryTitle;
    protected String originalTitle;
    protected boolean isAdult;
    protected short startYear;
    protected short endYear;
    protected int runtimeMinutes;
    protected String genres;

    public Movie getMovie(){
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitleType(titleType);
        movie.setPrimaryTitle(primaryTitle);
        movie.setOriginalTitle(originalTitle);
        movie.setIsAdult(isAdult);
        movie.setStartYear(startYear);
        movie.setEndYear(endYear);
        movie.setRuntimeMinutes(runtimeMinutes);
        movie.setGenres(genres);
        return movie;
    }

    public void copy(Movie movie){
        setTitleType(movie.getTitleType());
        setPrimaryTitle(movie.getPrimaryTitle());
        setOriginalTitle(movie.getOriginalTitle());
        setAdult(movie.isIsAdult());
        setStartYear(movie.getStartYear());
        setEndYear(movie.getEndYear());
        setRuntimeMinutes(movie.getRuntimeMinutes());
        setGenres(movie.getGenres());
    }

    public void copy(MovieWrapper movie){
        setTitleType(movie.getTitleType());
        setPrimaryTitle(movie.getPrimaryTitle());
        setOriginalTitle(movie.getOriginalTitle());
        setAdult(movie.isAdult());
        setStartYear(movie.getStartYear());
        setEndYear(movie.getEndYear());
        setRuntimeMinutes(movie.getRuntimeMinutes());
        setGenres(movie.getGenres());
    }
}
