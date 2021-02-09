package infrastructure;

import domain.dto.customer.CustomerDTO;
import domain.entity.customer.Customer;
import domain.entity.customer.CustomerException;
import domain.entity.customer.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class DBCustomer implements CustomerRepository {

  private static final boolean authorized = true;
  private final EntityManager em;

  public DBCustomer(EntityManagerFactory emf) {
    this.em = emf.createEntityManager();
  }

  @Override
  public Customer createCustomerFromDTO(CustomerDTO customerDTO) throws CustomerException {
    return new Customer(customerDTO, null);
  }

  @Override
  public Customer createCustomer(Customer customer) throws CustomerException {
    if (authorized) {
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

  @Override
  public List<Customer> getAllCustomers() throws CustomerException {
    if (authorized) {
      try {
        TypedQuery<Customer> query =
            em.createQuery("SELECT BANKCUSTOMER FROM Customer bankCustomer", Customer.class);
        return query.getResultList();
      } finally {
        em.close();
      }
    }
    return new ArrayList<>();
  }

  @Override
  public Customer getCustomerById(int id) throws CustomerException {
    try {
      return em.find(Customer.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Customer> getCustomersByName(String name) throws CustomerException {
    try {
      TypedQuery<Customer> query =
          em.createQuery(
                  "SELECT BANKCUSTOMER FROM Customer bankCustomer WHERE BANKCUSTOMER.firstName = :name",
                  Customer.class)
              .setParameter("name", name);
      return query.getResultList();
    } finally {
      em.close();
    }
  }

  @Override
  public void updateCustomer(Customer customer) throws CustomerException {
    // TODO: Implement code
  }

  @Override
  public void deleteCustomer(Customer customer) throws CustomerException {
    // TODO: Implement code
  }
}
