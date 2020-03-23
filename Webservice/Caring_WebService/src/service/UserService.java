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
import bll.User;
import bll.Zone;
import dal.Database;

@Path("/user")
public class UserService {
	
	@Context
	private UriInfo context;


	public UserService() {
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{userId}")
	public Response getUser(@PathParam("userId") String id) {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			User user = db.getUser(id);
			response.entity(new Gson().toJson(user));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		System.out.println("======================webservice GET called");
		return response.build();
	}

	@DELETE
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Path("/{userId}")
	public Response deleteUser(@PathParam("userId") String id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.NO_CONTENT);
		Database db = Database.newInstance();

		try {
			db.deleteUser(id);
			response.entity("user deleted");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response newUser(String strUser) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.CREATED);
		Database db = Database.newInstance();
		System.out.println("======================NEW User: " + strUser);

		try {
			User user = new Gson().fromJson(strUser, User.class);
			db.setUser(user);
			response.entity("User added");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}
	/* Vlt noch benötigt 
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateUser(String strZone) throws IOException {
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
	}*/ 

}

