package pl.bolo.producing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    protected String id;
    protected String slug;
    protected String titleType;
    protected String primaryTitle;
    protected String originalTitle;
    protected boolean isAdult;
    protected short startYear;
    protected short endYear;
    protected int runtimeMinutes;
    protected String genres;

    public void copy(Movie movie){
        setSlug(movie.getSlug());
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
