package ui.rest;

import static org.slf4j.LoggerFactory.getLogger;

import api.Onionarch;
import infrastructure.EmfCreator;
import infrastructure.ORMCustomer;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;

public class BaseResource {
  private static final Logger log = getLogger(BaseResource.class);
  protected static Onionarch API;
  protected static EntityManagerFactory emf;

  static {
    try {
      API = createApi();
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  public BaseResource() {
    // Ensure singleton
  }

  public static Onionarch createApi() {
    emf = EmfCreator.createEntityManagerFactory();

    return new Onionarch(new ORMCustomer(emf));
  }
}
