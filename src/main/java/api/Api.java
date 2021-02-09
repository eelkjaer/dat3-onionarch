package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.dto.customer.CustomerDTOException;
import infrastructure.CustomerFacade;
import javax.persistence.EntityManagerFactory;

public class Api {
  public final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  public final EntityManagerFactory emf;

  private final CustomerFacade customerFacade;

  public Api(EntityManagerFactory emf) {
    this.emf = emf;
    this.customerFacade = new CustomerFacade(emf);
  }

  public String getAllCustomers() {
    return gson.toJson(customerFacade.getAllCustomers());
  }

  public String getCustomerById(int id) throws CustomerDTOException {
    return gson.toJson(customerFacade.getCustomerById(id));
  }

  public boolean createCustomer() {
    // TODO: Implement code
    return false;
  }
}
