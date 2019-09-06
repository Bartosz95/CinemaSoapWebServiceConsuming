package myclass;

import movie.wsdl.GetMovieResponse;
import movie.wsdl.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CinemaController {

    @Autowired
    CinemaClient client;

    public void getMovie(CinemaClient quoteClient) {
        GetMovieResponse response = quoteClient.getMovieResponse("Joker");
        String string = String.format("Title: %s\nDirector: %s\nDuration: %s\nPremiere: %s",
                response.getMovie().getTitle(),
                response.getMovie().getDirector(),
                response.getMovie().getDuration(),
                response.getMovie().getPremiere());
        System.out.println(string);
    }

    @RequestMapping(value = "/")
    public String index(){
        GetMovieResponse response = client.getMovieResponse("Joker");
        String movieString = String.format("Title: %s\nDirector: %s\nDuration: %s\nPremiere: %s",
                response.getMovie().getTitle(),
                response.getMovie().getDirector(),
                response.getMovie().getDuration(),
                response.getMovie().getPremiere());
        return String.format("Welcome in Cinema Services\n\n We recommend today\n%s", movieString);
    }

}
