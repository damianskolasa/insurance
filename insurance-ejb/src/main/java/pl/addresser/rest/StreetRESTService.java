package pl.addresser.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pl.addresser.model.Street;

@Path("/streets")
public interface StreetRESTService {

	@GET
	@Produces("text/xml")
	public List<Street> listAllStreets();

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("text/xml")
	public Street lookupById(@PathParam("id") long id);

	@GET
	@Path("/desc/{desc}")
	@Produces("text/xml")
	public Street lookupByDescription(@PathParam("desc") String desc);

	@GET
	@Path("/suggest/{suggest}")
	@Produces("text/xml")
	public List<Street> suggestStreets(@PathParam("suggest") String pattern);

}
