package pl.healthinformator.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import pl.healthinformator.model.HealthCareData;

@Path("/healthinformations")
public interface HealthInformationRESTService {

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("text/xml")
	public HealthCareData lookupById(@PathParam("id") long id);

	@GET
	@Path("/health")
	@Produces("text/xml")
	public List<HealthCareData> lookupByInfo(
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("pesel") String pesel);
	
	
	@PUT
	@Path("/healthCareData.xml")
	@Consumes("text/xml")
	@Produces("text/xml")
	public HealthCareData update(HealthCareData healthCareData);

}
