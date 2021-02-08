package infrastructure;

import domain.customer.CustomerDTO;
import domain.customer.Customer;
import domain.customer.CustomerException;
import domain.customer.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CustomerFacade implements CustomerRepository {

  private static CustomerFacade instance;
  private static EntityManagerFactory emf;

  // Private Constructor to ensure Singleton
  private CustomerFacade() {}

  /**
   * @param _emf
   * @return an instance of this facade class.
   */
  public static CustomerFacade getBankFacade(EntityManagerFactory _emf) {
    if (instance == null) {
      emf = _emf;
      instance = new CustomerFacade();
    }
    return instance;
  }

  private EntityManager getEntityManager() {
    return emf.createEntityManager();
  }


  @Override
  public List<CustomerDTO> getAllCustomers() {
    boolean authorized = true;
    if (authorized) {
      EntityManager em = emf.createEntityManager();
      try {
        TypedQuery<Customer> query =
            em.createQuery("SELECT BANKCUSTOMER FROM Customer bankCustomer", Customer.class);
        return CustomerDTO.getAllBankCustomersDTO(query.getResultList());
      } finally {
        em.close();
      }
    }
    return new ArrayList<>();
  }


  @Override
  public List<CustomerDTO> getCustomersByName(String name) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Customer> query =
          em.createQuery(
              "SELECT BANKCUSTOMER FROM Customer bankCustomer WHERE BANKCUSTOMER.firstName = :name",
              Customer.class)
              .setParameter("name", name);
      return CustomerDTO.getAllBankCustomersDTO(query.getResultList());
    } finally {
      em.close();
    }
  }

  @Override
  public CustomerDTO getCustomerById(int id) throws CustomerException {
    EntityManager em = emf.createEntityManager();
    try {
      CustomerDTO customerDTO = new CustomerDTO(em.find(Customer.class, id));
      return customerDTO;
    } finally {
      em.close();
    }
  }

  @Override
  public Customer createCustomer(CustomerDTO customerdto) throws CustomerException {
    boolean authorized = true;
    if (authorized) {
      EntityManager em = emf.createEntityManager();
      try {
        em.getTransaction().begin();
        em.persist(customerdto);
        em.getTransaction().commit();
      } finally {
        em.close();
      }
    }
    return null;
  }

  @Override
  public void updateCustomer(CustomerDTO customer) throws CustomerException {

  }

  @Override
  public void deleteCustomer(CustomerDTO customer) throws CustomerException {

  }
}
