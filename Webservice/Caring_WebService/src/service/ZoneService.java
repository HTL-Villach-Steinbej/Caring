package service;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import bll.Fahrzeug;
import bll.Schaden;
import bll.Zone;
import dal.Database;

@Path("/zone")
public class ZoneService {
	
	@Context
	private UriInfo context;


	public ZoneService() {
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{zoneId}")
	public Response getZone(@PathParam("zoneId") String id) {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			Zone zone = db.getZone(Integer.parseInt(id));
			response.entity(new Gson().toJson(zone));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		System.out.println("======================webservice GET called");
		return response.build();
	}

	@DELETE
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Path("/{zoneId}")
	public Response deleteZone(@PathParam("zoneId") String id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.NO_CONTENT);
		Database db = Database.newInstance();

		try {
			db.deleteZone(Integer.valueOf(id));
			response.entity("zone deleted");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response newZone(String strZone) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.CREATED);
		Database db = Database.newInstance();
		System.out.println("======================NEW Zone: " + strZone);

		try {
			Zone zone = new Gson().fromJson(strZone, Zone.class);
			db.setZone(zone);
			response.entity("Zone added");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateZone(String strZone) throws IOException {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		try {
			Zone zone = new Gson().fromJson(strZone, Zone.class);
			db.updateZone(zone);
			response.entity("Zone updated");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

}

