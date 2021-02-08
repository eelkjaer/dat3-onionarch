package domain.customer;

import java.util.List;

public interface CustomerRepository extends CustomerFactory {

  List<CustomerDTO> getAllCustomers() throws CustomerException;

  CustomerDTO getCustomerById(int id) throws CustomerException;

  List<CustomerDTO> getCustomersByName(String name) throws CustomerException;

  void updateCustomer(CustomerDTO customer) throws CustomerException;

  void deleteCustomer(CustomerDTO customer) throws CustomerException;
}
