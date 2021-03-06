package ui.rest;

import static onionarch.Utils.GSON;

import domain.customer.exceptions.CustomerException;
import domain.customer.exceptions.CustomerNotFound;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import onionarch.dto.CustomerDTO;

@Path("customer")
public class CustomerResource extends BaseResource {
  private final Random generator = new Random();

  @Context private UriInfo context;

  public CustomerResource() {
    super();
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String helloWorld() {
    return "{\"msg\": \"Hello World\"}";
  }

  @Path("{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getCustomerById(@PathParam("id") int id) throws CustomerNotFound {
    return Response.status(200).entity(GSON.toJson(API.getCustomerById(id))).build();
  }

  @Path("all")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getAllCustomers() throws CustomerException {
    return Response.status(200).entity(GSON.toJson(API.getAllCustomers())).build();
  }

  @Path("{id}")
  // @RolesAllowed("ADMIN")
  @DELETE
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public String deleteCustomer(@PathParam("id") int id) throws CustomerNotFound {
    boolean isdeleted = API.deleteCustomerById(id);
    if(isdeleted){
      return "{\"status\": \"ID " + id + " was removed\"}";
    } else {
      return "{\"status\": \"Error occured\"}";
    }
  }

  @Path("{id}")
  // @RolesAllowed("ADMIN")
  @PUT
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response updateCustomer(@PathParam("id") int id, String customer) throws CustomerNotFound {
    CustomerDTO dto = GSON.fromJson(customer, CustomerDTO.class);
    dto = API.updateCustomerById(id, dto);
    return Response.ok(dto).build();
  }

  // @RolesAllowed("ADMIN")
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response createNewCustomer(String customer) throws CustomerNotFound, CustomerException {
    CustomerDTO dto = GSON.fromJson(customer, CustomerDTO.class);

    int regNo = generator.nextInt(1000) + 999;
    int accNo = generator.nextInt(1000000) + 999999;

    String accNum = String.format("%d-%d", regNo, accNo);
    dto.setAccountNumber(accNum);
    API.createCustomer(dto);
    dto = API.getCustomerByNumber(accNum);

    return Response.ok(dto).build();
  }
}
