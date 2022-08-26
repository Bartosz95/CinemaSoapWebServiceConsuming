package pl.bolo.producing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesRestServiceApplication {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10_000);
        SpringApplication.run(MoviesRestServiceApplication.class, args);
    }


}
