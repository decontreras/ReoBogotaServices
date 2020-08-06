package RETOBOGOTA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import RETOBOGOTA.Models.Location;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationRest {

	public static final JSONParser JSON_PARSER = new JSONParser();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("RETOBOGOTA");
	EntityManager em = emf.createEntityManager();

	@GET
	public Response findAllLocations() {
		try {
			em.getTransaction().begin();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(em.createNamedQuery("Location.findAll").getResultList()).build();
	}

	@POST
	public Response createLocation(String locationRequest) throws ParseException {
		Object obj = JSON_PARSER.parse(locationRequest);
		JSONObject jsonObject = (JSONObject) obj;
		Location location = new Location(jsonObject.get("name").toString(),
				Integer.parseInt(jsonObject.get("area_m2").toString()), jsonObject.get("parent_location").toString());
		em.getTransaction().begin();
		em.persist(location);
		em.getTransaction().commit();
		em.close();
		return Response.ok(locationRequest).build();
	}

	@PUT
	public Response updateLocation(String locationRequest) throws ParseException {
		Object obj = JSON_PARSER.parse(locationRequest);
		JSONObject jsonObject = (JSONObject) obj;
		Location location = new Location(jsonObject.get("name").toString(),
				Integer.parseInt(jsonObject.get("area_m2").toString()), jsonObject.get("parent_location").toString());
		
		em.getTransaction().begin();
		Location found  = new Location();
		try {
			found  = em.find(Location.class, location.getName());
		}catch(Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Location not found").build();
		}
		Location updateLocation = found;
		updateLocation.setName(location.getName());
		updateLocation.setArea_m2(location.getArea_m2());
		updateLocation.setParent_location(location.getParent_location());
		em.merge(updateLocation);
		em.getTransaction().commit();
		em.close();
		return Response.ok(updateLocation).build();
	}

	@DELETE
	public Response deleteLocation(String locationRequest) throws ParseException {
		Object obj = JSON_PARSER.parse(locationRequest);
		JSONObject jsonObject = (JSONObject) obj;

		em.getTransaction().begin();
		Location found  = new Location();
		try {
			found  = em.find(Location.class, jsonObject.get("name").toString());
		}catch(Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Location not found").build();
		}

		em.remove(found);
		em.getTransaction().commit();
		em.close();
		return Response.ok("Eliminado con exito").build();
	}
}