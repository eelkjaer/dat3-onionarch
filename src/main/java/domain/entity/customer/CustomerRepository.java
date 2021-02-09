package domain.entity.customer;

import domain.dto.customer.CustomerDTO;
import java.util.List;

public interface CustomerRepository extends CustomerFactory {

  List<Customer> getAllCustomers() throws CustomerException;

  Customer getCustomerById(int id) throws CustomerException;

  List<Customer> getCustomersByName(String name) throws CustomerException;

  void updateCustomer(Customer customer) throws CustomerException;

  void deleteCustomer(Customer customer) throws CustomerException;
}
