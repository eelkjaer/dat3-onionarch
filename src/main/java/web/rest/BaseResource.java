package web.rest;

import static org.slf4j.LoggerFactory.getLogger;

import api.ApplicationConfig;
import infrastructure.DBCustomer;
import infrastructure.DBCustomerDTO;
import infrastructure.EmfCreator;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;

public class BaseResource {
  private static final Logger log = getLogger(BaseResource.class);
  protected static ApplicationConfig API;

  protected BaseResource() {}

  static {
    try {
    API = createApi();
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  private static ApplicationConfig createApi() {

    EntityManagerFactory emf = EmfCreator.createEntityManagerFactory();

    return new ApplicationConfig(
        new DBCustomer(emf),
        new DBCustomerDTO(emf)
    );
  }

}
