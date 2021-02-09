package domain.entity.customer;

import domain.dto.customer.CustomerDTO;

public interface CustomerFactory {
  Customer createCustomerFromDTO(CustomerDTO customerDTO) throws CustomerException;

  Customer createCustomer(Customer customer) throws CustomerException;
}
