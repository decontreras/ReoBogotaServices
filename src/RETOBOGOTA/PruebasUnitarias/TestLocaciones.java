package RETOBOGOTA.PruebasUnitarias;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import RETOBOGOTA.LocationRest;
import RETOBOGOTA.Models.Location;

public class TestLocaciones {

	private LocationRest locationRest;

    @Before
    public void setUp() {
    	locationRest = new LocationRest();
    }
	
	@Test
	public void findAllLocations() {
		Response httpResponse = locationRest.findAllLocations();
		assertEquals(httpResponse.getStatusInfo().getStatusCode(), Status.OK.getStatusCode());
	}
	
	@Test
	public void createLocation() throws ParseException {
		Location location = new Location("default2", 0, "default");
		Response httpResponse = null;
		try {
			locationRest.deleteLocation(location.toString());
			httpResponse = locationRest.createLocation(location.toString());
		}catch(Exception e) {
			httpResponse = locationRest.createLocation(location.toString());
		}
		assertEquals(httpResponse.getStatus(), Status.OK.getStatusCode());
	}
	
	@Test
	public void updateLocation() throws ParseException{
		Location location = new Location("default2", 0, "default");
		Response httpResponse = null;
		try {
			locationRest.createLocation(location.toString());
			httpResponse = locationRest.updateLocation(location.toString());
		}catch(Exception e) {
			httpResponse = locationRest.updateLocation(location.toString());
		}
		assertEquals(httpResponse.getStatus(), Status.OK.getStatusCode());
	}
	
	@Test
	public void deleteLocation() throws ParseException {
		Location location = new Location("default2", 0, "default");
		Response httpResponse = null;
		try {
			locationRest.createLocation(location.toString());
			httpResponse = locationRest.deleteLocation(location.toString());
		}catch(Exception e) {
			httpResponse = locationRest.deleteLocation(location.toString());
		}
		assertEquals(httpResponse.getStatus(), Status.OK.getStatusCode());
	}
}
