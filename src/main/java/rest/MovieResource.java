package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("Movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/Movie",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MovieFacade facade =  MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("/databaseData")
    public String databaseData() {
        EntityManager em = EMF.createEntityManager();

        List<Movie> movies = new ArrayList();
        movies.add(new Movie(1992, "My Little Whale", new String[]{"John","Johnny"}));
        movies.add(new Movie(1993, "My Little Whale 2", new String[]{"John","Johnny"}));
        movies.add(new Movie(1994, "My Little Whale 3", new String[]{"John","Johnny"}));
        movies.add(new Movie(1995, "My Little Whale 4", new String[]{"John","Johnny"}));

        for (Movie mv : movies) {
            em.getTransaction().begin();
            em.persist(mv);
            em.getTransaction().commit();
        }

        em.close();

        return "Data added";
    }
    
    
    
    
    
    @Path("/getAllMovies")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        return GSON.toJson(facade.getAllMovies());
    }
    
    /*
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Movie entity) {
        throw new UnsupportedOperationException();
    }
    */
    
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getById(Movie entity, @PathParam("id") Long id) {
        return GSON.toJson(facade.getMovieById(id));
    }
    
    /*
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Movie entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }*/
}
