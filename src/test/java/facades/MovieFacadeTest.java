package facades;

import dto.MovieDTO;
import utils.EMF_Creator;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public MovieFacadeTest() {
    }

    static List<Movie> movies = new ArrayList();

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/Movie_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getMovieFacade(emf);

        movies.add(new Movie(1992, "My Little Whale", new String[]{"John", "Johnny"}));
        movies.add(new Movie(2018, "My Little Whale 2", new String[]{"John", "Johnie"}));
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.createNativeQuery("ALTER TABLE MOVIE AUTO_INCREMENT = 1").executeUpdate();
            em.getTransaction().commit();

            for (Movie mov : movies) {
                em.getTransaction().begin();
                em.persist(mov);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getAllMovies().size(), "Expects two rows in the database");
    }

    @Test
    public void testGetMovieById() {
        MovieDTO result = facade.getMovieById(1L);
        assertNotNull(result);
        assertEquals("My Little Whale", result.getName());
    }
    
    @Test
    public void testGetMovieByName() {
        List<MovieDTO> result = facade.getMovieByName("MY Little Whale 2");
        assertNotNull(result);
        assertEquals(new MovieDTO(movies.get(1)), result.get(0));
    }

    @Test
    public void testGetAllMovies() {
        List<MovieDTO> result = facade.getAllMovies();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

}
