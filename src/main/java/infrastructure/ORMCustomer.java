package infrastructure;

import onionarch.dto.CustomerDTO;
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
  public CustomerDTO createCustomer(CustomerDTO customer) throws CustomerException {
      try {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Customer(customer));
        em.getTransaction().commit();
      } finally {
        em.close();
      }
      return customer;
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
  public CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerException {
      try {
        em = emf.createEntityManager();

        Customer cust = em.find(Customer.class, customer.getCustomerId());

        em.getTransaction().begin();
        cust.setBalance(customer.getBalance());
        em.getTransaction().commit();

        return new CustomerDTO(cust);

      } finally {
        em.close();
      }
  }

  @Override
  public boolean deleteCustomer(Customer customer) throws CustomerException {
    try {
      em = emf.createEntityManager();
      em.getTransaction().begin();
      em.remove(customer);
      em.getTransaction().commit();
      return true;
    } finally {
      em.close();
    }
  }

  @Override
  public Customer getCustomerByAccountNumber(String accNum) throws CustomerException {
    try {
      em = emf.createEntityManager();
      TypedQuery<Customer> query =
          em.createQuery(
              "SELECT BANKCUSTOMER FROM Customer bankCustomer WHERE BANKCUSTOMER.accountNumber = :accNum",
              Customer.class)
              .setParameter("accNum", accNum);
      return query.getSingleResult();
    } finally {
      em.close();
    }
  }
}
