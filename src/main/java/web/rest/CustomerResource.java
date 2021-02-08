package web.rest;

import static org.slf4j.LoggerFactory.getLogger;

import domain.entity.customer.CustomerException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;

@Path("customer")
public class CustomerResource extends BaseResource{

  private static final Logger log = getLogger(CustomerResource.class);

  @Context private UriInfo context;

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String demo() {
    return API.gson.toJson("Hello World!");
  }

  @Path("count")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getCustomerCount() {

    long count = API.getAllCustomers().size();
    return API.gson.toJson(count);
  }


  @Path("{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getCustomerById(@PathParam("id") int id) throws CustomerException {
    return API.gson.toJson(API.getCustomerById(id));
  }

  @Path("all")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getAllCustomers() {
    return API.gson.toJson(API.getAllCustomers());
  }

//  @Path("init")
//  @GET
//  @Produces({MediaType.APPLICATION_JSON})
//  public String initData(){
//    return API.gson.toJson(MakeTestData.createTestData());
//  }

}
