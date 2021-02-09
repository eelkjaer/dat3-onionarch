package web.rest;

import domain.dto.customer.CustomerDTO;
import domain.dto.customer.CustomerDTOException;
import domain.entity.customer.Customer;
import domain.entity.customer.CustomerException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("customer")
public class CustomerResource extends BaseResource {



  @Context private UriInfo context;

  public CustomerResource() {
  super();
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String demo() {
    return "{\"msg\": \"Hello World\"}";
  }

  @Path("count")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getCustomerCount() {
    int count = 1337;
    return "{\"count\": " + count + " }";
  }

  @Path("{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getCustomerById(@PathParam("id") int id)
      throws CustomerException, CustomerDTOException {
    return API.getCustomerById(id);
  }

  @Path("all")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getAllCustomers() throws CustomerException {
    return API.getAllCustomers();
  }

  @Path("create")
  //@RolesAllowed("ADMIN")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public void createNewCustomer(String customer) throws CustomerException {
    API.createCustomer(customer);
    //return Response.status(200).entity(null).build();
  }
}
