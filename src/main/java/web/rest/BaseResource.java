package web.rest;

import static org.slf4j.LoggerFactory.getLogger;

import api.Api;
import api.utils.EmfCreator;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;

public class BaseResource {
  private static final Logger log = getLogger(BaseResource.class);
  protected static Api API;

  static {
    try {
      API = createBaseApi(EmfCreator.createEntityManagerFactory());
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  protected BaseResource() {}

  private static Api createBaseApi(EntityManagerFactory emf) {
    return new Api(emf);
  }
}
