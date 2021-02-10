package domain.customer.entity;

import api.dto.CustomerDTO;
import domain.customer.exceptions.CustomerException;
import java.util.List;

public interface CustomerRepository extends CustomerFactory {

  List<Customer> getAllCustomers() throws CustomerException;

  Customer getCustomerById(int id) throws CustomerException;

  List<Customer> getCustomersByName(String name) throws CustomerException;

  void updateCustomer(CustomerDTO customer) throws CustomerException;

  void deleteCustomer(Customer customer) throws CustomerException;
}
