package infrastructure;

import domain.customer.CustomerDTO;
import domain.customer.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CustomerFacade {

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

  public CustomerDTO getCustomerById(long id) {
    EntityManager em = emf.createEntityManager();
    try {
      CustomerDTO customerDTO = new CustomerDTO(em.find(Customer.class, id));
      return customerDTO;
    } finally {
      em.close();
    }
  }

  public List<CustomerDTO> getCustomerByName(String name) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Customer> query =
          em.createQuery(
                  "SELECT BANKCUSTOMER FROM BankCustomer bankCustomer WHERE BANKCUSTOMER.firstName = :name",
                  Customer.class)
              .setParameter("name", name);
      List<Customer> bankCustomers = query.getResultList();
      return CustomerDTO.getAllBankCustomersDTO(bankCustomers);
    } finally {
      em.close();
    }
  }

  public Customer addCustomer(Customer customer) {
    boolean authorized = true;
    if (authorized) {
      EntityManager em = emf.createEntityManager();
      try {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
      } finally {
        em.close();
      }
    }
    return null;
  }

  public List<Customer> getAllCustomers() {
    boolean authorized = true;
    if (authorized) {
      EntityManager em = emf.createEntityManager();
      try {
        TypedQuery<Customer> query =
            em.createQuery("SELECT BANKCUSTOMER FROM BankCustomer bankCustomer", Customer.class);
        return query.getResultList();
      } finally {
        em.close();
      }
    }
    return new ArrayList<>();
  }
}
