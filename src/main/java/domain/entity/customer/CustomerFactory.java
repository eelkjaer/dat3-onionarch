package domain.entity.customer;

import domain.dto.customer.CustomerDTO;

public interface CustomerFactory {

  Customer createCustomer(CustomerDTO customerdto) throws CustomerException;
}
