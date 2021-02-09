package domain.dto.customer;

import domain.entity.customer.Customer;

public interface CustomerDTOFactory {
  CustomerDTO createCustomerDTOFromCustomer(Customer customer) throws CustomerDTOException;

  CustomerDTO createCustomer(CustomerDTO customerdto) throws CustomerDTOException;
}
