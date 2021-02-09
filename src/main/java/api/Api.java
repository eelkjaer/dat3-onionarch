package api;

import static api.Utils.GSON;

import domain.dto.customer.CustomerDTO;
import domain.dto.customer.CustomerDTOException;
import domain.dto.customer.CustomerDTORepository;
import domain.entity.customer.Customer;
import domain.entity.customer.CustomerException;
import domain.entity.customer.CustomerRepository;

public class Api {
  private final CustomerRepository customerRepository;
  private final CustomerDTORepository customerDTORepository;

  public Api(CustomerRepository customerRepository, CustomerDTORepository customerDTORepository) {
    this.customerRepository = customerRepository;
    this.customerDTORepository = customerDTORepository;
  }

  public String getAllCustomers() throws CustomerException {
    return GSON.toJson(customerRepository.getAllCustomers());
  }

  public String getCustomerById(int id) throws CustomerException {
    return GSON.toJson(customerRepository.getCustomerById(id));
  }

  public void createCustomer(String customerJson) throws CustomerException {
    customerRepository.createCustomer(
        GSON.fromJson(customerJson, Customer.class)
    );
  }

}
