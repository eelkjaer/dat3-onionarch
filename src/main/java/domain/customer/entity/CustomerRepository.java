package domain.customer.entity;

import onionarch.dto.CustomerDTO;
import domain.customer.exceptions.CustomerException;
import java.util.List;

public interface CustomerRepository extends CustomerFactory {

  List<Customer> getAllCustomers() throws CustomerException;

  Customer getCustomerById(int id) throws CustomerException;

  List<Customer> getCustomersByName(String name) throws CustomerException;

  CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerException;

  boolean deleteCustomer(Customer customer) throws CustomerException;

  Customer getCustomerByAccountNumber(String accNum) throws CustomerException;
}
