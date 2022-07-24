package pl.bolo.producing.mylib;

import pl.bolo.producing.domain.Movie;
import pl.bolo.producing.repository.MoviesRepository;


public class Slug {
    /*
     * Slug is function with covert string for ex. "Some title 2!" to
     * string "some-title-2-"
     */
    public static String convertToSlug(String name){

        for(int i = 0; i<48; i++){
            if(i==45) continue;
            name = name.replace(String.format("%c",i),"-");
        }
        for(int i = 58; i<65; i++){
            name = name.replace(String.format("%c",i),"-");
        }
        for(int i = 91; i<97; i++){
            name = name.replace(String.format("%c",i),"-");
        }
        for(int i = 123; i<127; i++){
            name = name.replace(String.format("%c",i),"-");
        }
        return name.toLowerCase();

    }
    /*
     * Function get Movie object and set slug in thus object. This function check
     * in database if slag is unique.
     */
    public static String convertAndCheckIn(MoviesRepository repository, Movie movie){
        //if ((movie.getSlug().substring(0,-3).equals(convertToSlug(movie.getOriginalTitle()))))
         //       return movie.getSlug();

        String title = convertToSlug(movie.getOriginalTitle());
        int i = 2;
        while (repository.findBySlug(title) != null){
            if (title.substring(-3,-2) == "--"){
                title = title.substring(0,-1).concat(String.valueOf(i));
                i++;
            } else
                title = title.concat("--").concat(String.valueOf(i));
        }
        return title;
    }
}
