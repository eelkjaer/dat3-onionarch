package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.dto.customer.CustomerDTOException;
import domain.entity.customer.CustomerException;
import infrastructure.DBCustomer;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

  public final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  public final EntityManagerFactory emf;

  private final infrastructure.DBCustomer DBCustomer;

  public ApplicationConfig(EntityManagerFactory emf) {
    this.emf = emf;
    this.DBCustomer = new DBCustomer(emf);
  }

  public String getAllCustomers() throws CustomerException {
    return gson.toJson(DBCustomer.getAllCustomers());
  }

  public String getCustomerById(int id) throws CustomerDTOException, CustomerException {
    return gson.toJson(DBCustomer.getCustomerById(id));
  }

  public boolean createCustomer() {
    // TODO: Implement code
    return false;
  }

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  /**
   * Do not modify addRestResourceClasses() method. It is automatically populated with all resources
   * defined in the project. If required, comment out calling this method in getClasses().
   */
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
    resources.add(web.rest.BaseResource.class);
    resources.add(web.rest.CustomerResource.class);
  }
}
