package main.java.myclass.configuration;

import main.java.myclass.repository.LoadDatabase;
import main.java.myclass.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class MovieConfig {
    @Value("${database.create}")
    boolean create;
    @Value("${database.records}")
    int countOfRecord;

    @Bean
    public void createDatabase() {
        System.out.println(countOfRecord);
        if (create) {
            new MovieRepository("persistenceCreate").saveAll(LoadDatabase.LoadMovie(null, countOfRecord));
        }
    }


    @Bean
    public MovieRepository movieRepository(){
        return new MovieRepository("persistenceUpdate");
    }
}
