package main.java.myclass.endpoints;

import com.cinemasevice.soapwebservicesproducing.*;
import main.java.myclass.api.Repository;
import main.java.myclass.domain.MovieWrapper;
import main.java.myclass.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import static main.java.myclass.templates.nameSpace;

@Endpoint
public class MovieEndpoint {

    @Autowired
    @Qualifier("movieRepository")
    private MovieRepository movieRepository;

    @PayloadRoot(namespace = nameSpace, localPart = "getMovieByIdRequest")
    @ResponsePayload
    public GetMovieResponse findOne(@RequestPayload GetMovieByIdRequest request){
        GetMovieResponse response = new GetMovieResponse();
        MovieWrapper movieWrapper = movieRepository.findOne(request.getId());
        response.setMovie(movieWrapper.getMovie());
        return response;
    }

    @PayloadRoot(namespace = nameSpace, localPart = "getMovieByTitleRequest")
    @ResponsePayload
    public GetMovieResponse findOne(@RequestPayload GetMovieByTitleRequest request){
        GetMovieResponse response = new GetMovieResponse();
        MovieWrapper movieWrapper = movieRepository.findOne(request.getTitle());
        response.setMovie(movieWrapper.getMovie());
        return response;
    }

    @PayloadRoot(namespace = nameSpace, localPart = "getAllMovieRequest")
    @ResponsePayload
    public GetAllMovieResponse findAll(){
        GetAllMovieResponse response = new GetAllMovieResponse();
        for(MovieWrapper movieWrapper : movieRepository.findAll()){
            response.getMovies().add(movieWrapper.getMovie());
        }
        return response;
    }

    @PayloadRoot(namespace = nameSpace, localPart = "addMovieRequest")
    @ResponsePayload
    public StatusResponse saveOne(@RequestPayload AddMovieRequest request){
        StatusResponse response = new StatusResponse();
        try {
            MovieWrapper movieWrapper = new MovieWrapper();
            movieWrapper.setTitleType(request.getTitleType());
            movieWrapper.setPrimaryTitle(request.getPrimaryTitle());
            movieWrapper.setOriginalTitle(request.getOriginalTitle());
            movieWrapper.setAdult(request.isIsAdult());
            movieWrapper.setStartYear(request.getStartYear());
            movieWrapper.setEndYear(request.getEndYear());
            movieWrapper.setRuntimeMinutes(request.getRuntimeMinutes());
            movieWrapper.setGenres(request.getGenres());
            movieRepository.saveOne(movieWrapper);
            response.setStatus(true);
            return response;
        } catch (Exception e){
            response.setStatus(false);
            return response;
        }
    }


    @PayloadRoot(namespace = nameSpace, localPart = "editMovieRequest")
    @ResponsePayload
    public StatusResponse editOne(@RequestPayload EditMovieRequest request){
        StatusResponse response = new StatusResponse();
        try {
            assert (request.getId() == 0);
            MovieWrapper movieWrapper = new MovieWrapper();
            movieWrapper.setId(request.getId());
            movieWrapper.setTitleType(request.getTitleType());
            movieWrapper.setPrimaryTitle(request.getPrimaryTitle());
            movieWrapper.setOriginalTitle(request.getOriginalTitle());
            movieWrapper.setAdult(request.isIsAdult());
            movieWrapper.setStartYear(request.getStartYear());
            movieWrapper.setEndYear(request.getEndYear());
            movieWrapper.setRuntimeMinutes(request.getRuntimeMinutes());
            movieWrapper.setGenres(request.getGenres());
            movieRepository.editOne(movieWrapper);
            response.setStatus(true);
            return response;
        } catch (Exception e){
            response.setStatus(false);
            return response;
        }
    }

    @PayloadRoot(namespace = nameSpace, localPart = "deleteMovieByIdRequest")
    @ResponsePayload
    public StatusResponse deleteOne(@RequestPayload DeleteMovieByIdRequest request){
        StatusResponse response = new StatusResponse();
        response.setStatus(movieRepository.deleteOne(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = nameSpace, localPart = "deleteMovieByTitleRequest")
    @ResponsePayload
    public StatusResponse deleteOne(@RequestPayload DeleteMovieByTitleRequest request){
        StatusResponse response = new StatusResponse();
        response.setStatus(movieRepository.deleteOne(request.getTitle()));
        return response;
    }

    @PayloadRoot(namespace = nameSpace, localPart = "deleteAllMoviesRequest")
    @ResponsePayload
    public StatusResponse deleteAll(){
        StatusResponse response = new StatusResponse();
        response.setStatus(movieRepository.deleteAll());
        return response;
    }
}
