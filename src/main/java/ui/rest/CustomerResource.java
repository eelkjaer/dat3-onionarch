package ui.rest;

import static onionarch.Utils.GSON;

import java.util.Random;
import onionarch.dto.CustomerDTO;
import domain.customer.exceptions.CustomerException;
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
  private Random generator = new Random();

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
  public String getCustomerById(@PathParam("id") int id) throws CustomerException {
    return GSON.toJson(API.getCustomerById(id));
  }

  @Path("all")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getAllCustomers() throws CustomerException {
    return GSON.toJson(API.getAllCustomers());
  }

  @Path("{id}/delete")
  // @RolesAllowed("ADMIN")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public void deleteCustomer(@PathParam("id") int id) throws CustomerException {
    API.deleteCustomerById(id);
  }

  @Path("{id}/update")
  // @RolesAllowed("ADMIN")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public void updateCustomer(@PathParam("id") int id, String customer) throws CustomerException {
    CustomerDTO dto = GSON.fromJson(customer, CustomerDTO.class);
    API.updateCustomerById(id, dto);
  }

  @Path("create")
  // @RolesAllowed("ADMIN")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public Response createNewCustomer(String customer) throws CustomerException {
    CustomerDTO dto = GSON.fromJson(customer, CustomerDTO.class);

    int regNo = generator.nextInt(1000) + 999;
    int accNo = generator.nextInt(1000000) + 999999;

    String accNum = String.format("%d-%d", regNo, accNo);
    dto.setAccountNumber(accNum);
    API.createCustomer(dto);

    return Response.status(200).entity(API.getCustomerByNumber(accNum)).build();
  }
}
