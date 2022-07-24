package pl.bolo.producing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "movie")
public class Movie {
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

    public void copy(Movie movie){
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
