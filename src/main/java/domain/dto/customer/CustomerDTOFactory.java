package domain.dto.customer;

import domain.entity.customer.Customer;

public interface CustomerDTOFactory {

  CustomerDTO createCustomer(CustomerDTO customerdto) throws CustomerDTOException;
}
