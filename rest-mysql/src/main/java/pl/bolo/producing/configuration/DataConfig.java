package pl.bolo.producing.configuration;

import pl.bolo.producing.api.Logger;
import pl.bolo.producing.domain.Movie;
import pl.bolo.producing.loggers.DataLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.properties")
@Configuration
public class DataConfig {
    @Value("${data.path}")
    String pathToFile;
    @Value("${data.record.min}")
    int recordMin;
    @Value("${data.record.max}")
    int recordMax;
    Logger log;

    @Bean
    public List<Movie> getMovieFromFile() {

        log = new DataLogger();
        int actualRecord = recordMin;
        String filesLine;
        List<Movie> moviesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {

            if (recordMin>recordMax)
                throw new Exception("Min record is bigger than max");
            reader.readLine(); // skip first line

            while ((reader.read()) != -1) {
                actualRecord++;
                filesLine = reader.readLine();
                if (actualRecord < recordMin) continue;
                if (actualRecord > recordMax) break;

                Movie movie = new Movie();

                String[] details = filesLine.split("\t"); // film details is separate by tabulator
                if (!details[1].equals("\\N")) {
                    if (details[1].length() > 100)
                        movie.setTitleType(details[1].substring(0, 99));
                    else
                        movie.setTitleType(details[1]);
                }
                if (!details[2].equals("\\N")) {
                    if (details[2].length() > 100)
                        movie.setPrimaryTitle(details[2].substring(0, 99));
                    else
                        movie.setPrimaryTitle(details[2]);
                }
                if (!details[3].equals("\\N")) {
                    if (details[3].length() > 100)
                        movie.setOriginalTitle(details[3].substring(0, 99));
                    else
                        movie.setOriginalTitle(details[3]);
                }
                movie.setAdult(details[4].equals("1"));
                if (!details[5].equals("\\N"))
                    movie.setStartYear(Short.parseShort(details[5]));

                if (!details[6].equals("\\N"))
                    movie.setEndYear(Short.parseShort(details[6]));

                if (!details[7].equals("\\N"))
                    movie.setRuntimeMinutes(Integer.parseInt(details[7]));

                if (!details[8].equals("\\N")) {
                    if (details[8].length() > 100)
                        movie.setPrimaryTitle(details[8].substring(0, 99));
                    else
                        movie.setGenres(details[8]);
                }
                moviesList.add(movie);
            }
            log.write("Loaded all");
        } catch (FileNotFoundException e) {
            log.write(String.format("Not found file %s", pathToFile));
        } catch (Exception e){
            log.write(String.format("Problem in record %d", actualRecord));
            log.write(e.getMessage());
        }
        finally {
            log.write("Finish loaded");
            return moviesList;
        }
    }
}
