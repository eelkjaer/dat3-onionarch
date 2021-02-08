package infrastructure;

import domain.entity.customer.Customer;
import domain.dto.customer.CustomerDTO;
import domain.entity.customer.CustomerException;
import domain.entity.customer.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CustomerFacade implements CustomerRepository {

  private final EntityManager em;

  public CustomerFacade(EntityManagerFactory emf) {
    this.em = emf.createEntityManager();
  }


  @Override
  public List<CustomerDTO> getAllCustomers() {
    boolean authorized = true;
    if (authorized) {
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
  public void updateCustomer(CustomerDTO customer) throws CustomerException {}

  @Override
  public void deleteCustomer(CustomerDTO customer) throws CustomerException {}
}
