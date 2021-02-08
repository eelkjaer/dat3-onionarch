package web.rest;

import api.Api;
import api.utils.EmfCreator;
import javax.persistence.EntityManagerFactory;

public class BaseResource {

  protected static final Api API;


  static {
    API = createBaseApi(EmfCreator.createEntityManagerFactory());
  }

  private static Api createBaseApi(EntityManagerFactory emf) {
    return new Api(emf);
  }

  protected void init(){
    //TODO: Do stuff
  }

}
