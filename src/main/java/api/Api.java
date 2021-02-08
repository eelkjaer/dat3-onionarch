package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.dto.customer.CustomerDTO;
import domain.entity.customer.CustomerException;
import infrastructure.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public class Api {
  public final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  public final EntityManagerFactory emf;

  private final CustomerFacade customerFacade;

  public Api(EntityManagerFactory emf) {
    this.emf = emf;
    this.customerFacade = new CustomerFacade(emf);
  }

  public List<CustomerDTO> getAllCustomers() {
    return customerFacade.getAllCustomers();
  }

  public CustomerDTO getCustomerById(int id) throws CustomerException {
    return customerFacade.getCustomerById(id);
  }
}
