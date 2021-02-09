package infrastructure;

import domain.dto.customer.CustomerDTOException;
import domain.dto.customer.CustomerDTORepository;
import domain.entity.customer.Customer;
import domain.dto.customer.CustomerDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CustomerFacade implements CustomerDTORepository {

  private final EntityManager em;

  private final boolean authorized = true;

  public CustomerFacade(EntityManagerFactory emf) {
    this.em = emf.createEntityManager();
  }


  @Override
  public List<CustomerDTO> getAllCustomers() {
    if (this.authorized) {
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
  public CustomerDTO getCustomerById(int id) throws CustomerDTOException {
    try {
      return new CustomerDTO(em.find(Customer.class, id));
    } finally {
      em.close();
    }
  }

  @Override
  public CustomerDTO createCustomer(CustomerDTO customerdto) throws CustomerDTOException {
    if (this.authorized) {
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
  public void updateCustomer(CustomerDTO customer) throws CustomerDTOException {
    // TODO: Make update work
  }

  @Override
  public void deleteCustomer(CustomerDTO customer) throws CustomerDTOException {
    // TODO: Make delete work
  }
}
