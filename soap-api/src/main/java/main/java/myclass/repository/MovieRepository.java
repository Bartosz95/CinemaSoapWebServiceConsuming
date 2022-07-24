package main.java.myclass.repository;

import com.cinemasevice.soapwebservicesproducing.Movie;
import lombok.AllArgsConstructor;
import main.java.myclass.api.Repository;
import main.java.myclass.domain.MovieWrapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

;import static main.java.myclass.loggers.Logger.Log;


public class MovieRepository implements Repository<MovieWrapper> {

    private String persistenceName;

    public MovieRepository(String persistenceName){
        this.persistenceName = persistenceName;
    }

    @Override
    public MovieWrapper findOne(long id) {
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceName).createEntityManager();
        entityManager.getTransaction().begin();
        MovieWrapper movie = entityManager.find(MovieWrapper.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }

    public MovieWrapper findOne(String title){
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceName).createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovieWrapper> criteriaQuery = builder.createQuery(MovieWrapper.class);
        Root<MovieWrapper> movieWrapper = criteriaQuery.from(MovieWrapper.class);
        Path<String> originalTitle = movieWrapper.get("originalTitle");
        criteriaQuery.select(movieWrapper).where(builder.equal(originalTitle, title));
        TypedQuery<MovieWrapper> query = entityManager.createQuery(criteriaQuery);
        MovieWrapper movie = query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }

    @Override
    public List<MovieWrapper> findAll() {
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceName).createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovieWrapper> criteriaQuery = builder.createQuery(MovieWrapper.class);
        Root<MovieWrapper> movieWrapper = criteriaQuery.from(MovieWrapper.class);
        criteriaQuery.select(movieWrapper);
        TypedQuery<MovieWrapper> query = entityManager.createQuery(criteriaQuery);
        List<MovieWrapper> moviesWrapper = query.getResultList();
        for(MovieWrapper m: moviesWrapper){
            Log(m.toString());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return moviesWrapper;
    }

    @Override
    public boolean saveOne(MovieWrapper item) {
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceName).createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(item);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (Exception e){
            Log(e.getMessage());
            entityManager.close();
            return false;
        }
    }

    @Override
    public boolean saveAll(List<MovieWrapper> items) {
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceName).createEntityManager();
        try{
            entityManager.getTransaction().begin();
            for(MovieWrapper movieWrapper : items){
                entityManager.persist(movieWrapper);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (Exception e){
            Log(e.getMessage());
            entityManager.close();
            return false;
        }
    }

    @Override
    public boolean editOne(MovieWrapper item) {
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceName).createEntityManager();
        try {
            entityManager.getTransaction().begin();
            MovieWrapper movieWrapper = entityManager.find(MovieWrapper.class, item.getId());
            movieWrapper.copy(item);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (Exception e){
            entityManager.getTransaction().commit();
            entityManager.close();
            return false;
        }
    }

    @Override
    public boolean deleteOne(long id) {
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceName).createEntityManager();
        try {
            entityManager.getTransaction().begin();
            MovieWrapper movieWrapper = entityManager.find(MovieWrapper.class, id);
            entityManager.remove(movieWrapper);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (Exception e){
            entityManager.getTransaction().commit();
            entityManager.close();
            return false;
        }
    }

    public boolean deleteOne(String title){
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceName).createEntityManager();
        try {
            entityManager.getTransaction().begin();
            // to do
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (Exception e){
            entityManager.getTransaction().commit();
            entityManager.close();
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
