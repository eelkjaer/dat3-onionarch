package domain.customer.entity;

import api.dto.CustomerDTO;
import domain.customer.exceptions.CustomerException;

public interface CustomerFactory {
  Customer createCustomer(CustomerDTO customer) throws CustomerException;
}
