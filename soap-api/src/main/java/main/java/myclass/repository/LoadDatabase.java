package main.java.myclass.repository;

import com.cinemasevice.soapwebservicesproducing.Movie;
import main.java.myclass.domain.MovieWrapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static main.java.myclass.loggers.Logger.Log;

public class LoadDatabase {

    public static List<MovieWrapper> LoadMovie() {
        return getMovieFromFile(null, 0);
    }

    public static List<MovieWrapper> LoadMovie(String pathToFile) {
        return getMovieFromFile(pathToFile, 0);
    }

    public static List<MovieWrapper> LoadMovie(String pathToFile, int countOfRecord) {
        return getMovieFromFile(pathToFile, countOfRecord);
    }

    private static List<MovieWrapper> getMovieFromFile(String pathToFile, int countOfRecord) {
        if (pathToFile == null)
            pathToFile = "C:\\Users\\Home\\IdeaProjects\\CinemaSoapWebServiceProducing\\src\\main\\resources\\data.tsv";

        if (countOfRecord == 0)
            countOfRecord = 100;

        int actualRecord = 0;
        String filesLine;
        List<MovieWrapper> moviesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            reader.readLine();
            while (((reader.read()) != -1) && (actualRecord < countOfRecord)) {
                actualRecord++;

                filesLine = reader.readLine();
                String[] details = filesLine.split("\t");

                MovieWrapper movie = new MovieWrapper();

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
                Log(actualRecord * 100 / countOfRecord);
            }
            Log("Good load record form file");
        } catch (FileNotFoundException e) {
            Log("Not found file: " + pathToFile );
        } finally {
            return moviesList;
        }
    }
}
