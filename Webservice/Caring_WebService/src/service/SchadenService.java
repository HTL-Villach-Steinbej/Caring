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

import bll.Schaden;
import dal.Database;

@Path("/damage")
public class SchadenService {
	
	@Context
	private UriInfo context;


	public SchadenService() {
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/get/{schadenId}")
	public Response getArtikel(@PathParam("schadenId") String id) {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			Schaden schaden = db.getSchaden(Integer.parseInt(id));
			response.entity(new Gson().toJson(schaden));
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}
		System.out.println("======================webservice GET called");
		return response.build();
	}

	/*@DELETE
	@Consumes({ MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Path("/{artikelId}")
	public Response deleteArtikel(@PathParam("artikelId") String id) throws IOException {
		Response.ResponseBuilder response = Response.status(Response.Status.NO_CONTENT);
		Database db = Database.newInstance();

		try {
			db.deleteArtikel(Integer.valueOf(id));
			response.entity("artikel deleted");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response newArtikel(String strArtikel) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.CREATED);
		Database db = Database.newInstance();
		System.out.println("======================NEW Artikel: " + strArtikel);

		try {
			Artikel artikel = new Gson().fromJson(strArtikel, Artikel.class);
			db.setArtikel(artikel);
			response.entity("artikel added");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateArtikel(String strArtikel) throws IOException {
		Database db = Database.newInstance();
		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		try {
			Artikel artikel = new Gson().fromJson(strArtikel, Artikel.class);
			db.updateArtikel(artikel);
			response.entity("artikel updated");
		} catch (Exception e) {
			response.status(Response.Status.BAD_REQUEST);
			response.entity("[ERROR] " + e.getMessage());
		}

		return response.build();
	}
*/
}
