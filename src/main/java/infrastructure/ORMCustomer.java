package infrastructure;

import api.dto.CustomerDTO;
import domain.customer.entity.Customer;
import domain.customer.entity.CustomerRepository;
import domain.customer.exceptions.CustomerException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class ORMCustomer implements CustomerRepository {

  private final EntityManagerFactory emf;
  private EntityManager em;

  public ORMCustomer(EntityManagerFactory emf) {
    this.emf = emf;
  }

  @Override
  public Customer createCustomer(CustomerDTO customer) throws CustomerException {
      try {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        return new Customer(customer);
      } finally {
        em.close();
      }
  }

  @Override
  public List<Customer> getAllCustomers() throws CustomerException {
      try {
        em = emf.createEntityManager();
        TypedQuery<Customer> query =
            em.createQuery("SELECT BANKCUSTOMER FROM Customer bankCustomer", Customer.class);
        return query.getResultList();
      } finally {
        em.close();
      }
  }

  @Override
  public Customer getCustomerById(int id) throws CustomerException {
    try {
      em = emf.createEntityManager();
      return em.find(Customer.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<Customer> getCustomersByName(String name) throws CustomerException {
    try {
      em = emf.createEntityManager();
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
  public void updateCustomer(CustomerDTO customer) throws CustomerException {
      try {
        em = emf.createEntityManager();
        TypedQuery<Customer> query =
            em.createQuery("UPDATE Customer SET firstName= :firstname , lastName = :lastname, balance = :balance", Customer.class);
        query.setParameter("firstname", customer.getFullName().split(" ")[0]);
        query.setParameter("lastname", customer.getFullName().split(" ")[1]);
        query.setParameter("balance", customer.getBalance());
        query.executeUpdate();
      } finally {
        em.close();
      }
  }

  @Override
  public void deleteCustomer(Customer customer) throws CustomerException {
    try {
      em = emf.createEntityManager();
      em.getTransaction().begin();
      em.remove(customer);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }
}
