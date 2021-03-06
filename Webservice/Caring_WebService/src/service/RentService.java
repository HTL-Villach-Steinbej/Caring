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
import bll.Rent;
import bll.Schaden;
import bll.SchadenRent;
import bll.SchadenUser;
import dal.Database;

@Path("/rent")
public class RentService {
	
	@Context
	private UriInfo context;


	public RentService() {
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{rentId}")
	public Response getRent(@PathParam("rentId") String id) {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			Rent rent = db.getRent(Integer.parseInt(id));
			response.entity(new Gson().toJson(rent));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		System.out.println("======================webservice GET called");
		return response.build();
	}

	@DELETE
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Path("/{rentId}")
	public Response deleteRent(@PathParam("rentId") String id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.NO_CONTENT);
		Database db = Database.newInstance();

		try {
			db.deleteRent(Integer.valueOf(id));
			response.entity("rent deleted");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response newRent(String strRent) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.CREATED);
		Database db = Database.newInstance();
		System.out.println("======================NEW REnt: " + strRent);



		try {
			Rent rent = new Gson().fromJson(strRent, Rent.class);
			int id = db.setRent(rent);
			Rent rent2 = db.getRent(id);
			response.entity(new Gson().toJson(rent2));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage()+e.getLocalizedMessage());
		}

		return response.build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateRent(String strRent) throws IOException {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		try {
			Rent rent = new Gson().fromJson(strRent, Rent.class);
			db.updateRent(rent);
			response.entity("Rent updated");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{rentId}/dammages")
	public Response getDamagesFromRent(@PathParam("rentId") String id) {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
            response.entity(new Gson().toJson(db.getDamagesFromRent(Integer.parseInt(id))));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		System.out.println("======================webservice GET called");
		return response.build();
	}
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/dammage")
	public Response createDamageUser(String strSchadenRent) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.CREATED);
		Database db = Database.newInstance();
		System.out.println("======================NEW SchadenRent: " + strSchadenRent);

		try {
			SchadenUser schadenUser = new Gson().fromJson(strSchadenRent, SchadenUser.class);
			db.createDamageFromUser(schadenUser);
			response.entity("SchadenRent added");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}
	@DELETE
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Path("/{rentId}/damage/{damageId}")
	public Response removeDamageFromRent(@PathParam("rentId") String r_id,@PathParam("damageId") String d_id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.NO_CONTENT);
		Database db = Database.newInstance();

		try {
			db.removeDamageFromRent(Integer.valueOf(r_id),Integer.valueOf(d_id));
			response.entity("damage deleted");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

}
