package pl.bolo.producing.controllers;

import pl.bolo.producing.api.Logger;
import pl.bolo.producing.domain.Movie;
import pl.bolo.producing.loggers.RequestLogger;
import pl.bolo.producing.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@PropertySource("classpath:application.properties")
@RequestMapping("${server.path}")
public class MovieController {

    @Autowired
    private MoviesRepository repository;
    @Autowired
    private List<Movie> moviesForRefresh;

    private Logger log;

    public MovieController() {
        this.log = new RequestLogger();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Movie> findAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Movie saveOne(@RequestBody Movie item) {
       log.write("add new Movie");
       return repository.save(item);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deleteAll() {
        log.write("delete all movie");
        repository.deleteAll(repository.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Movie findOne(@PathVariable long id) {
        return repository.findById(id).get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Movie editOne(@PathVariable long id ,@RequestBody Movie item) {
        Optional<Movie> movie = repository.findById(id);
        movie.get().copy(item);
        log.write(String.format("Edit movie %d",id));
        return repository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deleteOne(@PathVariable long id) {
        log.write(String.format("Delete movie %d",id));
        repository.delete(repository.findById(id).get());
    }

    @PostMapping("/reset")
    public @ResponseBody Iterable<Movie> reset(){
        log.write("Delete all movie");
        repository.deleteAll(repository.findAll());
        log.write("Load all movie");
        return repository.saveAll(moviesForRefresh);
    }
}
