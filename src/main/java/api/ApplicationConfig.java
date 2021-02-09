package api;
import static api.Utils.GSON;

import domain.dto.customer.CustomerDTOException;
import domain.dto.customer.CustomerDTORepository;
import domain.entity.customer.CustomerException;
import domain.entity.customer.CustomerRepository;
import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {
  private final CustomerRepository customerRepository;
  private final CustomerDTORepository customerDTORepository;

  public ApplicationConfig(CustomerRepository customerRepository, CustomerDTORepository customerDTORepository) {
    this.customerRepository = customerRepository;
    this.customerDTORepository = customerDTORepository;
  }

  public String getAllCustomers() throws CustomerException {
    return GSON.toJson(customerRepository.getAllCustomers());
  }

  public String getCustomerById(int id) throws CustomerDTOException, CustomerException {
    return GSON.toJson(customerRepository.getCustomerById(id));
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
