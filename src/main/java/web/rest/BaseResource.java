package web.rest;

import static org.slf4j.LoggerFactory.getLogger;

import api.Api;
import infrastructure.entity.DBCustomer;
import infrastructure.dto.DBCustomerDTO;
import infrastructure.EmfCreator;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;

public class BaseResource {
  private static final Logger log = getLogger(BaseResource.class);
  protected static Api API;
  protected static EntityManagerFactory emf;

  public BaseResource() {
  }

  static {
    try {
    API = createApi();
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  public static Api createApi() {
    emf = EmfCreator.createEntityManagerFactory();

    return new Api(
        new DBCustomer(emf),
        new DBCustomerDTO(emf)
    );
  }

}
