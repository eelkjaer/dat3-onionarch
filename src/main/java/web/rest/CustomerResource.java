package web.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("customer")
public class CustomerResource {
  @Context private UriInfo context;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getJson() {
    // TODO return proper representation object
    throw new UnsupportedOperationException();
  }

  @Path("{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getCustomerById(@PathParam("id") int id) {
    // return GSON.toJson(FACADE.getCustomerById(id));
    return null;
  }
}
