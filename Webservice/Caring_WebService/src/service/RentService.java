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
import dal.Database;

@Path("/car")
public class RentService {
	
	@Context
	private UriInfo context;


	public RentService() {
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{rentId}")
	public Response getCar(@PathParam("rentId") String id) {
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
	public Response deleteCar(@PathParam("rentId") String id) throws IOException {
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
	public Response newCar(String strCar) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.CREATED);
		Database db = Database.newInstance();
		System.out.println("======================NEW Car: " + strCar);

		try {
			Fahrzeug car = new Gson().fromJson(strCar, Fahrzeug.class);
			db.setCar(car);
			response.entity("car added");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateCar(String strCar) throws IOException {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		try {
			Fahrzeug car = new Gson().fromJson(strCar, Fahrzeug.class);
			db.updateCar(car);
			response.entity("car updated");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

}
