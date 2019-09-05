package myclass;

import movie.wsdl.GetMovieResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args){
        SpringApplication.run(StartApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CinemaClient quoteClient) {
        return args -> {
            GetMovieResponse response = quoteClient.getMovieResponse("Joker");
            String string = String.format("Title: %s\nDirector: %s\nDuration: %s\nPremiere: %s",
                    response.getMovie().getTitle(),
                    response.getMovie().getDirector(),
                    response.getMovie().getDuration(),
                    response.getMovie().getPremiere());
            System.out.println(string);
        };
    }

}
