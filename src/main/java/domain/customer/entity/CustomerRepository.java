package domain.customer.entity;

import domain.customer.exceptions.CustomerNotFound;
import java.util.List;
import onionarch.dto.CustomerDTO;

public interface CustomerRepository extends CustomerFactory {

  List<Customer> getAllCustomers() throws CustomerNotFound;

  Customer getCustomerById(int id) throws CustomerNotFound;

  List<Customer> getCustomersByName(String name) throws CustomerNotFound;

  CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerNotFound;

  boolean deleteCustomer(CustomerDTO customer) throws CustomerNotFound;

  Customer getCustomerByAccountNumber(String accNum) throws CustomerNotFound;
}
