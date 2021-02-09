package infrastructure;

import domain.dto.customer.CustomerDTO;
import domain.dto.customer.CustomerDTOException;
import domain.dto.customer.CustomerDTORepository;
import domain.entity.customer.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class DBCustomerDTO implements CustomerDTORepository {

  private static final boolean authorized = true;
  private final EntityManager em;

  public DBCustomerDTO(EntityManagerFactory emf) {
    this.em = emf.createEntityManager();
  }

  @Override
  public CustomerDTO createCustomerDTOFromCustomer(Customer customer) throws CustomerDTOException {
    return new CustomerDTO(customer);
  }

  @Override
  public CustomerDTO createCustomer(CustomerDTO customerdto) throws CustomerDTOException {
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
  public List<CustomerDTO> getAllCustomerDTOs() throws CustomerDTOException {
    if (authorized) {
      try {
        TypedQuery<CustomerDTO> query =
            em.createQuery("SELECT BANKCUSTOMER FROM Customer bankCustomer", CustomerDTO.class);
        return query.getResultList();
      } finally {
        em.close();
      }
    }
    return new ArrayList<>();
  }

  @Override
  public CustomerDTO getCustomerById(int id) throws CustomerDTOException {
    try {
      return em.find(CustomerDTO.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public List<CustomerDTO> getCustomersByName(String name) throws CustomerDTOException {
    try {
      TypedQuery<CustomerDTO> query =
          em.createQuery(
                  "SELECT BANKCUSTOMER FROM Customer bankCustomer WHERE BANKCUSTOMER.firstName = :name",
                  CustomerDTO.class)
              .setParameter("name", name);
      return query.getResultList();
    } finally {
      em.close();
    }
  }

  @Override
  public void updateCustomer(CustomerDTO customer) throws CustomerDTOException {
    // TODO: Implement code
  }

  @Override
  public void deleteCustomer(CustomerDTO customer) throws CustomerDTOException {
    // TODO: Implement code
  }
}
