package infrastructure;

import domain.customer.exceptions.CustomerNotFound;
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
  public List<Customer> getAllCustomers() throws CustomerNotFound {
      try {
        em = emf.createEntityManager();
        TypedQuery<Customer> query =
            em.createQuery("SELECT BANKCUSTOMER FROM Customer bankCustomer", Customer.class);
        List<Customer> custList = query.getResultList();
        if(!custList.isEmpty()) return custList;
        throw new CustomerNotFound("No customers found");
      } finally {
        em.close();
      }
  }

  @Override
  public Customer getCustomerById(int id) throws CustomerNotFound {
    try {
      em = emf.createEntityManager();
      Customer cust = em.find(Customer.class, id);

      if(cust != null) return cust;
      throw new CustomerNotFound();
    } finally {
      em.close();
    }
  }

  @Override
  public List<Customer> getCustomersByName(String name) throws CustomerNotFound {
    try {
      em = emf.createEntityManager();
      TypedQuery<Customer> query =
          em.createQuery(
                  "SELECT BANKCUSTOMER FROM Customer bankCustomer WHERE BANKCUSTOMER.firstName = :name",
                  Customer.class)
              .setParameter("name", name);
      List<Customer> custList = query.getResultList();
      if(!custList.isEmpty()) return custList;
      throw new CustomerNotFound("No customers found");
    } finally {
      em.close();
    }
  }

  @Override
  public CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerNotFound {
      try {
        em = emf.createEntityManager();

        Customer cust = em.find(Customer.class, customer.getCustomerId());

      if (cust != null) {
        em.getTransaction().begin();
        cust.setBalance(customer.getBalance());
        em.getTransaction().commit();
        return new CustomerDTO(cust);
        }
        throw new CustomerNotFound();
      } finally {
        em.close();
      }
  }

  @Override
  public boolean deleteCustomer(CustomerDTO customer) throws CustomerNotFound {
    try {
      em = emf.createEntityManager();

      Customer cust = em.find(Customer.class, customer.getCustomerId());
      if (cust != null) {
        em.getTransaction().begin();
        em.remove(cust);
        em.getTransaction().commit();
        return true;
      }
      throw new CustomerNotFound();
    } finally {
      em.close();
    }
  }

  @Override
  public Customer getCustomerByAccountNumber(String accNum) throws CustomerNotFound {
    try {
      em = emf.createEntityManager();
      TypedQuery<Customer> query =
          em.createQuery(
              "SELECT BANKCUSTOMER FROM Customer bankCustomer WHERE BANKCUSTOMER.accountNumber = :accNum",
              Customer.class)
              .setParameter("accNum", accNum);

      Customer cust = query.getSingleResult();

      if(cust != null){
        return cust;
      }
      throw new CustomerNotFound();
    } finally {
      em.close();
    }
  }
}
