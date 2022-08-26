package pl.bolo.producing.controllers;

import pl.bolo.producing.api.Logger;
import pl.bolo.producing.domain.Movie;
import pl.bolo.producing.loggers.RequestLogger;
import pl.bolo.producing.mylib.Slug;
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
       if(repository.findBySlug(item.getSlug()) != null)
           item.setSlug(item.getSlug() + "--");
       return repository.save(item);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deleteAll() {
        log.write("delete all movie");
        repository.deleteAll();
    }

    @GetMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Movie findOne(@PathVariable String slug) {
        return repository.findBySlug(slug);
    }

    @PutMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Movie editOne(@PathVariable String slug , @RequestBody Movie item) {
        Optional<Movie> movie = repository.findById(slug);
        movie.get().copy(item);
        movie.get().setSlug(Slug.convertAndCheckIn(repository,item));
        log.write(String.format("Edit movie %s",slug));
        return repository.findBySlug(slug);
    }

    @DeleteMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deleteOne(@PathVariable String slug) {
        log.write(String.format("Delete movie %s",slug));
        repository.delete(repository.findBySlug(slug));
    }

    @PostMapping("/reset")
    public @ResponseBody List<Movie> reset(){
        log.write("Delete all movie");
        repository.deleteAll();
        log.write("Load all movie");
        return repository.saveAll(moviesForRefresh);
    }
}
