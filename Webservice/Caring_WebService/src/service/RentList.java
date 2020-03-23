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

import dal.Database;

@Path("/rents")
public class RentList {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GegenstandList
     */
    public RentList() {
    }
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRents() {
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        try {
            Database db = Database.newInstance();
            response.entity(new Gson().toJson(db.getRents()));
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
        }
        System.out.println("======================webservice GET called");
        return response.build();
    }
    

}
