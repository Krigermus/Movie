package facades;

import dto.MovieDTO;
import utils.EMF_Creator;
import entities.Movie;
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
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = MovieFacade.getMovieFacade(emf);
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
            em.persist(new Movie(1992, "My Little Whale", new String[]{"John","Johnny"}));
            em.persist(new Movie(2018, "My Little Whale 2", new String[]{"John","Johnie"}));

            em.getTransaction().commit();
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
    
    @Disabled
    @Test
    public void testAddMovie() {
        Movie result = facade.addMovie(new Movie(2020, "My Little Whale 3", new String[]{"John","Johnny"}));
        assertNotNull(result);
        assertEquals(3, result.getId().intValue());
        /*
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(Movie.class, 3L));
            em.getTransaction().commit();
        } finally {
            em.close();
        }*/
    }

    @Disabled
    @Test
    public void testGetMovieById() {
        MovieDTO result = facade.getMovieById(1L);
        assertNotNull(result);
        assertEquals("My Little Whale", result.getName());
    }

    @Test
    public void testGetAllMovies() {
        List<MovieDTO> result = facade.getAllMovies();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
/*
    @Test
    public void testRemoveMovie() {
        EntityManager em = emf.createEntityManager();
        Movie newMovie = new Movie(2020, "My Little Whale 3", new String[]{"John","Johnny"});
        assertNotNull(newMovie);
        
        List<Movie> movies;
        try {
            em.getTransaction().begin();
            em.persist(newMovie);
            movies = em.createQuery("SELECT mov FROM Movie mov").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        facade.removeMovie(newMovie);
        assertEquals("Hej", movies.get(0).getName());
        
        
    }
    */
    
}
