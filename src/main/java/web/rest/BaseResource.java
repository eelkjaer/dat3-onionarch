package web.rest;

import static org.slf4j.LoggerFactory.getLogger;

import api.ApplicationConfig;
import infrastructure.EmfCreator;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;

public class BaseResource {
  private static final Logger log = getLogger(BaseResource.class);
  protected static ApplicationConfig API;

  static {
    try {
      API = createBaseApi(EmfCreator.createEntityManagerFactory());
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  protected BaseResource() {}

  private static ApplicationConfig createBaseApi(EntityManagerFactory emf) {
    return new ApplicationConfig(emf);
  }
}
