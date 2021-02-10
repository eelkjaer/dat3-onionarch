package domain.customer.entity;

import domain.customer.exceptions.CustomerException;
import onionarch.dto.CustomerDTO;

public interface CustomerFactory {
  CustomerDTO createCustomer(CustomerDTO customer) throws CustomerException;
}
