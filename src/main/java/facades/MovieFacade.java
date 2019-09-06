package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO getMovieById(Long id) {
        return new MovieDTO(getEntityManager().find(Movie.class, id));
    }

    public List<MovieDTO> getMovieByName(String name) {
            return getEntityManager().createQuery("SELECT new dto.MovieDTO(mov) FROM Movie mov WHERE mov.name = :name", MovieDTO.class).setParameter("name", name).getResultList();
        }
        
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = getEntityManager().createQuery("SELECT mov FROM Movie mov").getResultList();
        List<MovieDTO> moviesDTO = new ArrayList();
        for (Movie m : movies) {
            moviesDTO.add(new MovieDTO(m));
        }
        return moviesDTO;
    }

    public Long getMovieCount(){
        return (Long)getEntityManager().createQuery("SELECT COUNT(mov) FROM Movie mov").getSingleResult();
    }

}
